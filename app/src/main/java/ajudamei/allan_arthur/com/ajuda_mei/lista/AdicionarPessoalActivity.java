package ajudamei.allan_arthur.com.ajuda_mei.lista;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import ajudamei.allan_arthur.com.ajuda_mei.R;


public class AdicionarPessoalActivity extends Activity {
    private static final int CAMERA_REQUEST = 1;
    private static final int PICK_FROM_GALLERY = 2;

    private static final String[] FORMAS = {"Cliente" , "Fornecedor"};
    private Cursor cursor;
    private EditText nome;
    private EditText email;
    private EditText telefone;
    private ImageView img;
    private Button tirarFoto;
    private Button escolherFoto;
    private Button confirmarAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ContentResolver c = this.getContentResolver();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_pessoal);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, FORMAS);
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerTipoPessoa);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(stringArrayAdapter);

        confirmarAdd = (Button) findViewById(R.id.bt_add_empregado);
        tirarFoto = (Button) findViewById(R.id.bt_tirar_foto);
        escolherFoto = (Button) findViewById(R.id.bt_escolher_foto);
        nome = (EditText) findViewById(R.id.txt_nome);
        email = (EditText) findViewById(R.id.txt_email);
        telefone = (EditText) findViewById(R.id.txt_per);
        img = (ImageView) findViewById(R.id.img_view_foto);


        confirmarAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
                //Bitmap bitmap = drawable.getBitmap();
                if (nome.getText().toString() == "" || telefone.getText().toString() == "") {
                    Toast.makeText(AdicionarPessoalActivity.this, "Dados Incorretos!", Toast.LENGTH_SHORT).show();
                } else {
                    String grupo;
                    if(spinner.getSelectedItem().toString().equals(FORMAS[0])){
                        grupo = "Clientes";
                    } else {
                        grupo = "Fornecedores";
                    }
                    ContentResolver cr = getContentResolver();
                    cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
                    String[] GROUP_PROJECTION = new String[]{ContactsContract.Groups._ID, ContactsContract.Groups.TITLE};
                    String groupID;
                    Cursor getGroupID_Cursor;
                    getGroupID_Cursor = c.query(ContactsContract.Groups.CONTENT_URI, GROUP_PROJECTION, ContactsContract.Groups.TITLE + "=?", new String[]{grupo}, null);
                    getGroupID_Cursor.moveToFirst();
                    groupID = (getGroupID_Cursor.getString(getGroupID_Cursor.getColumnIndex("_id")));

                    getGroupID_Cursor.close();

                    ArrayList<ContentProviderOperation> ops =
                            new ArrayList<ContentProviderOperation>();

                    int rawContactInsertIndex = ops.size();
                    ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                            .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                            .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                            .build());

                    ops.add(ContentProviderOperation
                            .newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,
                                    rawContactInsertIndex)
                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, telefone.getText().toString())
                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, "1").build());

                    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, nome.getText().toString())
                            .build());

                    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                            .withValue(ContactsContract.CommonDataKinds.GroupMembership.MIMETYPE,
                                    ContactsContract.CommonDataKinds.GroupMembership.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.GroupMembership.GROUP_ROW_ID, groupID).build());

                    try {
                        getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    } catch (OperationApplicationException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        tirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callCamera();

            }
        });

        escolherFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callGallery();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_OK) {
            return;
        }

        if(requestCode == CAMERA_REQUEST){
            Bundle extras = data.getExtras();

            if (extras != null) {
                Bitmap yourImage = extras.getParcelable("data");
                ImageView img = (ImageView)findViewById(R.id.img_view_foto);
                img.setImageBitmap(yourImage);

            }


        } else if(requestCode == PICK_FROM_GALLERY){
            Bundle extras2 = data.getExtras();

            if (extras2 != null) {
                Bitmap yourImage = extras2.getParcelable("data");

                ImageView img = (ImageView)findViewById(R.id.img_view_foto);
                img.setImageBitmap(yourImage);

            }
        } else {
            return;
        }

    }



    /**
     * open camera method
     */
    public void callCamera() {
        Intent cameraIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        //cameraIntent.putExtra("crop", "true");
        //cameraIntent.putExtra("aspectX", 0);
        //cameraIntent.putExtra("aspectY", 0);
        //cameraIntent.putExtra("outputX", 200);
        //cameraIntent.putExtra("outputY", 150);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);

    }

    /**
     * open gallery method
     */

    public void callGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 0);
        intent.putExtra("aspectY", 0);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(
                Intent.createChooser(intent, "Complete action using"),
                PICK_FROM_GALLERY);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdicionarPessoalActivity.this, ListaPessoalActivity.class));
        finish();
    }
}

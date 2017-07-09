package ajudamei.allan_arthur.com.ajuda_mei.controle.estoque.produto_final;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.database.DatabaseMateriaPrima;
import ajudamei.allan_arthur.com.ajuda_mei.database.DatabaseProdutoFinal;
import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.UsoGeral;
import ajudamei.allan_arthur.com.ajuda_mei.domain.item.ItemMateriaPrima;
import ajudamei.allan_arthur.com.ajuda_mei.domain.item.ItemProdutoFinal;

public class AdicionarProdutoFinalActivity extends Activity {
    private DatabaseMateriaPrima db;
    private DatabaseProdutoFinal db2;
    private UsoGeral g;
    private static final int CAMERA_REQUEST = 1;
    private static final int PICK_FROM_GALLERY = 2;

    private Button goToMatPrima;
    private Button confirmarAdd;
    private Button tirarFoto;
    private Button escolherFoto;
    private ImageView img;
    private EditText nome;
    private EditText quantidade;
    private EditText preco;
    private EditText tamanho;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_produto_final);
        db = new DatabaseMateriaPrima(this);
        db2 = new DatabaseProdutoFinal(this);
        confirmarAdd = (Button) findViewById(R.id.bt_add_empregado);
        tirarFoto = (Button) findViewById(R.id.bt_tirar_foto);
        escolherFoto = (Button) findViewById(R.id.bt_escolher_foto);
        nome = (EditText) findViewById(R.id.txt_nome);
        quantidade = (EditText) findViewById(R.id.txt_per);
        preco = (EditText) findViewById(R.id.txt_margem_atual);
        tamanho = (EditText) findViewById(R.id.txt_email);
        img = (ImageView) findViewById(R.id.img_view_foto);
        goToMatPrima = (Button) findViewById(R.id.bt_escolher_mat_prima);

        g = (UsoGeral) getApplication();

        if(!g.getTemp().equals(null)){
            ItemProdutoFinal temp = g.getTemp();
            nome.setText(temp.getNome());
            tamanho.setText(temp.getTamanho());
            quantidade.setText(String.valueOf(temp.getQuantidade()));
            preco.setText(String.valueOf(temp.getPreco()));
        }


        goToMatPrima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                g = (UsoGeral) getApplication();
                String aux1 = nome.getText().toString();
                String aux3 = quantidade.getText().toString();
                String aux2 = tamanho.getText().toString();
                String aux4 = preco.getText().toString();

                if(!aux1.equals("") || !aux2.equals("")|| !aux3.equals("")||!aux4.equals("")){
                    String retNome = "";
                    if(!aux1.equals("")){
                        retNome = aux1;
                    }
                    String retTam = "";
                    if(!aux2.equals("")){
                        retTam = aux2;
                    }
                    double retQtd = 0;
                    if(!aux3.equals("")){
                        retQtd = Double.parseDouble(aux3);
                    }
                    double retPreco = 0;
                    if(!aux4.equals("")){
                        retPreco = Double.parseDouble(aux4);
                    }

                    g.setTemp(new ItemProdutoFinal(retNome, retTam, retQtd, retPreco, null, "", 0));

                }
                Intent intent = new Intent(AdicionarProdutoFinalActivity.this, SelecionarMatPrimaActivity.class);
                startActivity(intent);

            }
        });
        confirmarAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                g = (UsoGeral) getApplication();
                List<ItemMateriaPrima> t1 = g.getItensParaDecrementar();
                List<Integer> t2 = g.getQuantidadesParaDecrementar();
                double custoProducao = 0;
                for(int i = 0; i < t1.size(); i++){
                    custoProducao += t2.get(i) * t1.get(i).getPreco();
                    db.modify(t1.get(i), t2.get(i), true);
                }
                BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                ItemProdutoFinal aux = new ItemProdutoFinal(nome.getText().toString() ,tamanho.getText().toString(), Double.parseDouble(quantidade.getText().toString()), Double.parseDouble(preco.getText().toString()),bitmap, null, custoProducao);

                db2.insert(aux, custoProducao);
                g.setItensParaDecrementar(new ArrayList<ItemMateriaPrima>());
                g.setQuantidadesParaDecrementar(new ArrayList<Integer>());
                Intent intent = new Intent(AdicionarProdutoFinalActivity.this, EscolherProdutoFinalActivity.class);
                Toast.makeText(getApplicationContext(), "Item adicionado!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
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
        startActivity(new Intent(AdicionarProdutoFinalActivity.this, EscolherProdutoFinalActivity.class));
        finish();
    }

}

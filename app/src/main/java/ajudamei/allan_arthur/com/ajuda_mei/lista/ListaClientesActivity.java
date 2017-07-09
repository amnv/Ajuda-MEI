package ajudamei.allan_arthur.com.ajuda_mei.lista;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.adapter.pessoa.AdapterCliente;
import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.controle.estoque.materia_prima.ShowMatPrimaActivity;
import ajudamei.allan_arthur.com.ajuda_mei.UsoGeral;
import ajudamei.allan_arthur.com.ajuda_mei.domain.pessoa.Cliente;
import ajudamei.allan_arthur.com.ajuda_mei.domain.item.ItemMateriaPrima;

public class ListaClientesActivity extends Activity {
    private ListView itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        itens = (ListView) findViewById(R.id.lista_clientes);
        View empty = findViewById(R.id.empty_lista_clientes);
        itens.setEmptyView(empty);
        itens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemMateriaPrima item = (ItemMateriaPrima) parent.getItemAtPosition(position);
                UsoGeral g = (UsoGeral) getApplication();
                g.setItem(item);
                Intent intent = new Intent(ListaClientesActivity.this, ShowMatPrimaActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ContentResolver cr = getContentResolver();
        String[] GROUP_PROJECTION = new String[]{ContactsContract.Groups._ID, ContactsContract.Groups.TITLE};
        String groupID;
        Cursor getGroupID_Cursor;
        getGroupID_Cursor = this.getContentResolver().query(ContactsContract.Groups.CONTENT_URI, GROUP_PROJECTION, ContactsContract.Groups.TITLE + "=?", new String[]{"Clientes"}, null);
        getGroupID_Cursor.moveToFirst();
        groupID = (getGroupID_Cursor.getString(getGroupID_Cursor.getColumnIndex("_id")));

        getGroupID_Cursor.close();

        List<Cliente> temp = getContatos(Integer.parseInt(groupID));

        AdapterCliente adapter = new AdapterCliente(ListaClientesActivity.this, R.layout.itemlista, temp);

        if(temp != null) {
            itens.setAdapter(adapter);
            Toast.makeText(this,"Quantidade "+temp.size(),Toast.LENGTH_SHORT).show();
        }
    }

    public List<Cliente> getContatos(int groupID) {

        List<Cliente>aux = new ArrayList<>();
        Uri groupURI = ContactsContract.Data.CONTENT_URI;
        String[] projection = new String[] {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.GroupMembership.CONTACT_ID };

        Cursor c = getContentResolver().query(
                groupURI,
                projection,
                ContactsContract.CommonDataKinds.GroupMembership.GROUP_ROW_ID
                        + "=" + groupID, null, null);

        while (c.moveToNext()) {
            String id = c
                    .getString(c
                            .getColumnIndex(ContactsContract.CommonDataKinds.GroupMembership.CONTACT_ID));
            Cursor pCur = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    new String[] { id }, null);

            while (pCur.moveToNext()) {
                Cliente cliente = new Cliente(pCur
                        .getString(pCur
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)), "",
                        pCur
                                .getString(pCur
                                        .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        );
                aux.add(cliente);
            }

            pCur.close();

        }

        return aux;
    }


}

package ajudamei.allan_arthur.com.ajuda_mei;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;

import ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.CaixaDaEmpresaActivity;
import ajudamei.allan_arthur.com.ajuda_mei.calculo.preco.venda.CalcularPrecoVendaEscolherActivity;
import ajudamei.allan_arthur.com.ajuda_mei.controle.estoque.TipoEstoqueActivity;
import ajudamei.allan_arthur.com.ajuda_mei.empregados.EmpregadosActivity;
import ajudamei.allan_arthur.com.ajuda_mei.lista.ListaPessoalActivity;

public class MainActivity extends Activity{
    private static final String MY_PREFS_NAME = "grupo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences settings = getSharedPreferences(MY_PREFS_NAME, 0);
        boolean check = settings.getBoolean("criados", false);

        if(!check){
            checkContactsGroup("Clientes");
            checkContactsGroup("Funcionários");
            SharedPreferences setting = getSharedPreferences(MY_PREFS_NAME, 0);
            SharedPreferences.Editor editor = setting.edit();
            editor.putBoolean("criados", true);
            editor.commit();
        }

        final Button bt1 = (Button) findViewById(R.id.bt_controle_seu_estoque);
        bt1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TipoEstoqueActivity.class);
                startActivity(intent);
            }
        });

        final Button bt2 = (Button) findViewById(R.id.bt_caixa_da_empresa);
        bt2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CaixaDaEmpresaActivity.class);
                startActivity(intent);
            }
        });

        final Button bt3 = (Button) findViewById(R.id.bt_preco_venda);
        bt3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CalcularPrecoVendaEscolherActivity.class);
                startActivity(intent);
            }
        });

        final Button bt4 = (Button) findViewById(R.id.bt_ctr_pessoal);
        bt4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListaPessoalActivity.class);
                startActivity(intent);
            }
        });

        final Button bt5 = (Button) findViewById(R.id.bt_ctr_empregados);
        bt5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EmpregadosActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void checkContactsGroup(String nomeGrupo){
        Cursor groupCursor;
        String[] GROUP_PROJECTION = new String[] { ContactsContract.Groups._ID,     ContactsContract.Groups.TITLE };
        groupCursor = this.getContentResolver().query(ContactsContract.Groups.CONTENT_URI,    GROUP_PROJECTION, ContactsContract.Groups.TITLE+ "=?", new String[]{nomeGrupo}, ContactsContract.Groups.TITLE + " ASC");

        if(groupCursor.getCount() > 0){
            Toast.makeText(MainActivity.this, "Group " + nomeGrupo+ " is already available", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            Toast.makeText(MainActivity.this, "Not available", Toast.LENGTH_SHORT).show();

            //Here we create a new Group
            try {
                ContentValues groupValues;
                ContentResolver cr = this.getContentResolver();
                groupValues = new ContentValues();
                groupValues.put(ContactsContract.Groups.TITLE, nomeGrupo);
                groupValues.put(ContactsContract.Groups.SHOULD_SYNC,true);
                cr.insert(ContactsContract.Groups.CONTENT_URI, groupValues);
            }
            catch(Exception e){
            }
            Toast.makeText(MainActivity.this, "Created "+ nomeGrupo + " Successfully",Toast.LENGTH_SHORT).show();
        }

        groupCursor.close();

    }

}

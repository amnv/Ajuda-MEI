package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.pagamento.boleto;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.adapter.registro.BoletoAdapter;
import ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.CaixaDaEmpresaActivity;
import ajudamei.allan_arthur.com.ajuda_mei.database.archComponent.RegistroBoletoDatabase;
import ajudamei.allan_arthur.com.ajuda_mei.database.archComponent.RegistroBoletoRoom;
import ajudamei.allan_arthur.com.ajuda_mei.domain.registro.Boleto;

public class PagamentoBoletoActivity extends Activity {

    //private DatabaseRegistroBoleto db;
    private RegistroBoletoDatabase db;
    private ListView itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_boleto);

        db = Room.databaseBuilder(getApplicationContext(),
                RegistroBoletoDatabase.class, "RegistroBoletoRoom").build();
        //db = new DatabaseRegistroBoleto(this);

        itens = (ListView) findViewById(R.id.lista_registro_boleto);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem trocar = menu.add(0, 0, 0, "Adicionar");
        trocar.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(PagamentoBoletoActivity.this, AdicionarBoletoActivity.class);
        startActivity(intent);
        return (super.onOptionsItemSelected(item));
    }

    @Override
    protected void onStart() {
        super.onStart();
//      List<Boleto> temp = db.getAllItens();
        new CarregaBancoAsync().execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PagamentoBoletoActivity.this, CaixaDaEmpresaActivity.class));
        finish();
    }

    public class CarregaBancoAsync extends AsyncTask<Void, Void, List<Boleto>>
    {
        @Override
        protected List<Boleto> doInBackground(Void... voids) {
            List<RegistroBoletoRoom> temp1 = db.registroBoletoDao().getAll();
            List<Boleto> temp = new ArrayList<>();
            for (RegistroBoletoRoom t : temp1)
            {
                temp.add(RegistroBoletoRoom.toBoleto(t));
            }
            return temp;
        }

        @Override
        protected void onPostExecute(List<Boleto> boleto) {
            Toast.makeText(getApplicationContext(), "Qnt de itens: " + boleto.size(), Toast.LENGTH_SHORT).show();
            BoletoAdapter adapter = new BoletoAdapter(PagamentoBoletoActivity.this, R.layout.registrolista, boleto);
            if (boleto != null) {
                itens.setAdapter(adapter);
            }
        }
    }

}

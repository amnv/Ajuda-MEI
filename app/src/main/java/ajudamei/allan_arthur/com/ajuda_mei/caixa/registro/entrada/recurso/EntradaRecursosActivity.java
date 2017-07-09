package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.entrada.recurso;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.adapter.registro.RecursoAdapter;
import ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.CaixaDaEmpresaActivity;
import ajudamei.allan_arthur.com.ajuda_mei.database.DatabaseRegistroRecurso;
import ajudamei.allan_arthur.com.ajuda_mei.domain.registro.Recurso;

public class EntradaRecursosActivity extends Activity {

    DatabaseRegistroRecurso db;
    ListView recursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_entrada_recursos);

        db = new DatabaseRegistroRecurso(this);

        recursos = (ListView) findViewById(R.id.lista_registro_recurso);

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
        Intent intent = new Intent(EntradaRecursosActivity.this, AdicionarEntradaRecursoActivity.class);
        startActivity(intent);
        return (super.onOptionsItemSelected(item));
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<Recurso> temp = db.getAllItens();
        Toast.makeText(getApplicationContext(), "Qnt de itens: " + temp.size(), Toast.LENGTH_SHORT).show();
        RecursoAdapter adapter = new RecursoAdapter(EntradaRecursosActivity.this, R.layout.registrolista, temp);
        if (temp != null) {
            recursos.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EntradaRecursosActivity.this, CaixaDaEmpresaActivity.class));
        finish();
    }
}

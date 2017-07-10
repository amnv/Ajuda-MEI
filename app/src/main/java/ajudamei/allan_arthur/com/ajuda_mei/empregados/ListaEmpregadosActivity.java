package ajudamei.allan_arthur.com.ajuda_mei.empregados;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.adapter.pessoa.AdapterEmpregado;
import ajudamei.allan_arthur.com.ajuda_mei.controle.estoque.materia_prima.AdicionarMatPrimaActivity;
import ajudamei.allan_arthur.com.ajuda_mei.controle.estoque.materia_prima.EscolherMatPrimaActivity;
import ajudamei.allan_arthur.com.ajuda_mei.database.DatabaseEmpregado;
import ajudamei.allan_arthur.com.ajuda_mei.domain.pessoa.Empregado;

public class ListaEmpregadosActivity extends Activity {
    private ListView empregados;
    private DatabaseEmpregado db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empregados);
        db = new DatabaseEmpregado(this);

        empregados = (ListView) findViewById(R.id.lista_empregados);
        View empty = findViewById(R.id.empty_lista_empregados);
        empregados.setEmptyView(empty);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem trocar = menu.add(0,0,0,"Adicionar");
        trocar.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(ListaEmpregadosActivity.this,AdicionarEmpregadoActivity.class);
        startActivity(intent);
        return(super.onOptionsItemSelected(item));
    }

    @Override
    protected void onStart() {
        super.onStart();

        List<Empregado> temp = db.getAllEmpregados();
//        Toast.makeText(getApplicationContext(), "Qnt de itens: " + temp.size(), Toast.LENGTH_SHORT).show();
        AdapterEmpregado adapter = new AdapterEmpregado(ListaEmpregadosActivity.this, R.layout.empregado, temp);
        if (temp != null) {
            empregados.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ListaEmpregadosActivity.this, EmpregadosActivity.class));
        finish();
    }

}

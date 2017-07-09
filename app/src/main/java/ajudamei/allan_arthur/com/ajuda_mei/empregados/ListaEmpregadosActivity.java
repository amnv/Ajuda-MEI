package ajudamei.allan_arthur.com.ajuda_mei.empregados;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ListView;

import ajudamei.allan_arthur.com.ajuda_mei.R;

public class ListaEmpregadosActivity extends Activity {
    private ListView empregados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empregados);

        empregados = (ListView) findViewById(R.id.lista_clientes);
        View empty = findViewById(R.id.empty_lista_clientes);
        empregados.setEmptyView(empty);

    }
    @Override
    protected void onStart() {
        super.onStart();

//        List<Empregado> temp = db.getAllItens();
//        Toast.makeText(getApplicationContext(), "Qnt de itens: " + temp.size(), Toast.LENGTH_SHORT).show();
//        AdapterEmpregado adapter = new AdapterEmpregado(ListaEmpregadosActivity.this, R.layout.empregado, temp);
//        if (temp != null) {
//            empregados.setAdapter(adapter);
//        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ListaEmpregadosActivity.this, EmpregadosActivity.class));
        finish();
    }

}

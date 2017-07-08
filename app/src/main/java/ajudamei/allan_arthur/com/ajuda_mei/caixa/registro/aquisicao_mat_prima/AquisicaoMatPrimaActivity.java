package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.aquisicao_mat_prima;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.CaixaDaEmpresaActivity;
import ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.boleto.AdicionarBoletoActivity;
import ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.boleto.DatabaseRegistroBoleto;
import ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.boleto.PagamentoBoletoActivity;
import ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.venda_produto.DatabaseRegistroVenda;

public class AquisicaoMatPrimaActivity extends Activity {

    DatabaseRegistroAquisicao db;
    ListView aquisicoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_aquisicao_mat_prima);

        db = new DatabaseRegistroAquisicao(this);

        aquisicoes = (ListView) findViewById(R.id.lista_registro_aquisicao);

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
        Intent intent = new Intent(AquisicaoMatPrimaActivity.this, AdicionarAquisicaoActivity.class);
        startActivity(intent);
        return (super.onOptionsItemSelected(item));
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<Aquisicao> temp = db.getAllItens();
        Toast.makeText(getApplicationContext(), "Qnt de itens: " + temp.size(), Toast.LENGTH_SHORT).show();
        AquisicaoAdapter adapter = new AquisicaoAdapter(AquisicaoMatPrimaActivity.this, R.layout.registrolista, temp);
        if (temp != null) {
            aquisicoes.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AquisicaoMatPrimaActivity.this, CaixaDaEmpresaActivity.class));
        finish();
    }
}

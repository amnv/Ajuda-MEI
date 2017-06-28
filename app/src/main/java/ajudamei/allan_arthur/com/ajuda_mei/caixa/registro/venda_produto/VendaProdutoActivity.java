package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.venda_produto;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.AdapterProduto;
import ajudamei.allan_arthur.com.ajuda_mei.DatabaseProdutoFinal;
import ajudamei.allan_arthur.com.ajuda_mei.ItemProdutoFinal;
import ajudamei.allan_arthur.com.ajuda_mei.R;

public class VendaProdutoActivity extends Activity {

    DatabaseProdutoFinal db;
    ListView vendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_venda_produto);

        db = new DatabaseProdutoFinal(this);

        vendas = (ListView) findViewById(R.id.lista_registro_venda);

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
        Intent intent = new Intent(VendaProdutoActivity.this, AdicionarVendaActivity.class);
        startActivity(intent);
        return (super.onOptionsItemSelected(item));
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<ItemProdutoFinal> temp = db.getAllItens();
        Toast.makeText(getApplicationContext(), "Qnt de itens: " + temp.size(), Toast.LENGTH_SHORT).show();
        AdapterProduto adapter = new AdapterProduto(VendaProdutoActivity.this, R.layout.registrolista, temp);
        if (temp != null) {
            vendas.setAdapter(adapter);
        }
    }
}

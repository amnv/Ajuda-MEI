package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.venda.produto_final;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.adapter.item.AdapterProduto;
import ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.CaixaDaEmpresaActivity;
import ajudamei.allan_arthur.com.ajuda_mei.database.DatabaseProdutoFinal;
import ajudamei.allan_arthur.com.ajuda_mei.domain.item.ItemProdutoFinal;
import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.UsoGeral;

public class VendaProdutoActivity extends Activity {

    DatabaseProdutoFinal db;
    ListView vendas;
    UsoGeral ug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_venda_produto);

        db = new DatabaseProdutoFinal(this);

        vendas = (ListView) findViewById(R.id.lista_registro_venda);
        View empty = findViewById(R.id.empty_produto_final);
        vendas.setEmptyView(empty);
        vendas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemProdutoFinal item = (ItemProdutoFinal) parent.getItemAtPosition(position);
                ug = (UsoGeral) getApplication();
                ug.setProduto(item);
                Intent intent = new Intent(VendaProdutoActivity.this, ShowRegistroVendaProdutoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<ItemProdutoFinal> temp = db.getAllItens();
        Toast.makeText(getApplicationContext(), "Qnt de itens: " + temp.size(), Toast.LENGTH_SHORT).show();
        AdapterProduto adapter = new AdapterProduto(VendaProdutoActivity.this, R.layout.itemlista, temp);
        if (temp != null) {
            vendas.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(VendaProdutoActivity.this, CaixaDaEmpresaActivity.class));
        finish();
    }
}

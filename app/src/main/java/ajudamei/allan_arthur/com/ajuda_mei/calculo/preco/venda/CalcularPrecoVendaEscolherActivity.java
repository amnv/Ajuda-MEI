package ajudamei.allan_arthur.com.ajuda_mei.calculo.preco.venda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.adapter.item.AdapterProduto;
import ajudamei.allan_arthur.com.ajuda_mei.database.DatabaseProdutoFinal;
import ajudamei.allan_arthur.com.ajuda_mei.MainActivity;
import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.UsoGeral;
import ajudamei.allan_arthur.com.ajuda_mei.domain.item.ItemProdutoFinal;

public class CalcularPrecoVendaEscolherActivity extends Activity {

    private DatabaseProdutoFinal db;
    private ListView itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_preco_venda_escolher);

        db = new DatabaseProdutoFinal(this);

        itens = (ListView) findViewById(R.id.lista_mat_prima);
        View empty = findViewById(R.id.empty_calcular_preco_venda_escolher);
        itens.setEmptyView(empty);
        itens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemProdutoFinal item = (ItemProdutoFinal) parent.getItemAtPosition(position);
                UsoGeral g = (UsoGeral) getApplication();
                g.setTemp(item);
                Intent intent = new Intent(CalcularPrecoVendaEscolherActivity.this, CalcularPrecoVendaMainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<ItemProdutoFinal> temp = db.getAllItens();
        //Toast.makeText(getApplicationContext(), "Qnt de itens: " + temp.size(), Toast.LENGTH_SHORT).show();
        AdapterProduto adapter = new AdapterProduto(CalcularPrecoVendaEscolherActivity.this, R.layout.itemlista, temp);

        if (temp != null) {
            itens.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CalcularPrecoVendaEscolherActivity.this, MainActivity.class));
        finish();

    }
}

package ajudamei.allan_arthur.com.ajuda_mei.lista;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.UsoGeral;
import ajudamei.allan_arthur.com.ajuda_mei.controle.estoque.mat_prima.ShowMatPrimaActivity;
import ajudamei.allan_arthur.com.ajuda_mei.domain.ItemProdutoFinal;

public class ListaFuncionariosActivity extends Activity {
    private ListView itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_fornecedores);

        itens = (ListView) findViewById(R.id.lista_funcionarios);
        View empty = findViewById(R.id.empty_lista_funcionarios);
        itens.setEmptyView(empty);
        itens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemProdutoFinal item = (ItemProdutoFinal) parent.getItemAtPosition(position);
                UsoGeral ug = (UsoGeral) getApplication();
                ug.setProduto(item);
                Intent intent = new Intent(ListaFuncionariosActivity.this, ShowMatPrimaActivity.class);
                startActivity(intent);
            }
        });
    }
}

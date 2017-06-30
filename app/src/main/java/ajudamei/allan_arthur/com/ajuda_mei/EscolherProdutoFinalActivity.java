package ajudamei.allan_arthur.com.ajuda_mei;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class EscolherProdutoFinalActivity extends Activity {
    private DatabaseProdutoFinal db;
    private ListView itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_produto_final);

        db = new DatabaseProdutoFinal(this);

        itens = (ListView) findViewById(R.id.lista_produto_final);

        itens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemProdutoFinal item = (ItemProdutoFinal) parent.getItemAtPosition(position);
                UsoGeral g = (UsoGeral) getApplication();
                g.setProduto(item);
                Intent intent = new Intent(EscolherProdutoFinalActivity.this, ShowProdutoFinalActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem trocar = menu.add(0,0,0,"Adicionar Produto");
        trocar.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(EscolherProdutoFinalActivity.this,AdicionarProdutoFinalActivity.class);
        startActivity(intent);
        return(super.onOptionsItemSelected(item));
    }
    @Override
    protected void onStart() {
        super.onStart();
        List<ItemProdutoFinal> temp = db.getAllItens();
        Toast.makeText(getApplicationContext(), "Qnt de itens: " + temp.size(), Toast.LENGTH_SHORT).show();
        AdapterProduto adapter = new AdapterProduto(EscolherProdutoFinalActivity.this, R.layout.itemlista, temp);
        if(temp != null) {
           itens.setAdapter(adapter);
        }

    }
}

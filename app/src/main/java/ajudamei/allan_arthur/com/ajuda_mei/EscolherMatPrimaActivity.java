package ajudamei.allan_arthur.com.ajuda_mei;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class EscolherMatPrimaActivity extends Activity {
    private DatabaseMateriaPrima db;
    private ListView itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_mat_prima);

        db = new DatabaseMateriaPrima(this);

        itens = (ListView) findViewById(R.id.lista_mat_prima);
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
        Intent intent = new Intent(EscolherMatPrimaActivity.this,AdicionarMatPrimaActivity.class);
        startActivity(intent);
        return(super.onOptionsItemSelected(item));
    }
    @Override
    protected void onStart() {
        super.onStart();
        List<ItemMateriaPrima> temp = db.getAllItens();
        Toast.makeText(getApplicationContext(), "Qnt de itens: " + temp.size(), Toast.LENGTH_SHORT).show();
        Adapter adapter = new Adapter(EscolherMatPrimaActivity.this, R.layout.itemlista, temp);
        if(temp != null) {
            itens.setAdapter(adapter);
        }
    }
}

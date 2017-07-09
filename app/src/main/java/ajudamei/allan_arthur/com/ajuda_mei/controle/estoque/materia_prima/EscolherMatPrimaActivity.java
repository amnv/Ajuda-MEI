package ajudamei.allan_arthur.com.ajuda_mei.controle.estoque.materia_prima;

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

import ajudamei.allan_arthur.com.ajuda_mei.adapter.item.AdapterMateria;
import ajudamei.allan_arthur.com.ajuda_mei.database.DatabaseMateriaPrima;
import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.UsoGeral;
import ajudamei.allan_arthur.com.ajuda_mei.controle.estoque.TipoEstoqueActivity;
import ajudamei.allan_arthur.com.ajuda_mei.domain.item.ItemMateriaPrima;

public class EscolherMatPrimaActivity extends Activity {
    private DatabaseMateriaPrima db;
    private ListView itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_mat_prima);

        db = new DatabaseMateriaPrima(this);

        itens = (ListView) findViewById(R.id.lista_mat_prima);
        View empty = findViewById(R.id.empty_mat_prima);
        itens.setEmptyView(empty);
        itens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemMateriaPrima item = (ItemMateriaPrima) parent.getItemAtPosition(position);
                UsoGeral g = (UsoGeral) getApplication();
                g.setItem(item);
                Intent intent = new Intent(EscolherMatPrimaActivity.this, ShowMatPrimaActivity.class);
                startActivity(intent);
            }
        });
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
        Intent intent = new Intent(EscolherMatPrimaActivity.this,AdicionarMatPrimaActivity.class);
        startActivity(intent);
        return(super.onOptionsItemSelected(item));
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<ItemMateriaPrima> temp = db.getAllItens();
        Toast.makeText(getApplicationContext(), "Qnt de itens: " + temp.size(), Toast.LENGTH_SHORT).show();
        AdapterMateria adapter = new AdapterMateria(EscolherMatPrimaActivity.this, R.layout.itemlista, temp);

        if(temp != null) {
            itens.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EscolherMatPrimaActivity.this, TipoEstoqueActivity.class));
        finish();

    }
}

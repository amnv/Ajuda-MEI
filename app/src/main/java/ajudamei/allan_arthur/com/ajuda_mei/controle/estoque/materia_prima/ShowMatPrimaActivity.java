package ajudamei.allan_arthur.com.ajuda_mei.controle.estoque.materia_prima;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.adapter.registro.AdapterRegistro;
import ajudamei.allan_arthur.com.ajuda_mei.database.DatabaseMateriaPrima;
import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.UsoGeral;
import ajudamei.allan_arthur.com.ajuda_mei.domain.registro.Registro;

public class ShowMatPrimaActivity extends Activity {
    private ListView registro;
    private ImageView imagem;
    private TextView nome;
    private UsoGeral g;
    private DatabaseMateriaPrima db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_mat_prima);
        g = (UsoGeral) getApplication();
        registro = (ListView) findViewById(R.id.extrato_mat_prima);
        imagem = (ImageView) findViewById(R.id.img_mat_prima);
        nome = (TextView) findViewById(R.id.txt_nome);

        nome.setText(g.getItem().getNome());
        imagem.setImageBitmap(g.getItem().getFoto());

        db = new DatabaseMateriaPrima(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem trocar = menu.add(0,0,0,"Deletar");
        trocar.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        db.delete(g.getItem());
        Toast.makeText(getApplicationContext(), "Item deletado!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ShowMatPrimaActivity.this,EscolherMatPrimaActivity.class);
        startActivity(intent);
        return(super.onOptionsItemSelected(item));
    }

    @Override
    protected void onStart() {
        super.onStart();
        g = (UsoGeral) getApplication();


        List<Registro> temp = db.getAllRegistros(g.getItem());
        //Toast.makeText(getApplicationContext(), "Qnt de itens: " + temp.size(), Toast.LENGTH_SHORT).show();
        AdapterRegistro adapter = new AdapterRegistro(ShowMatPrimaActivity.this, R.layout.itemlista, temp);
        if(temp != null) {
            registro.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        g = (UsoGeral) getApplication();
        g.setItem(null);
        startActivity(new Intent(ShowMatPrimaActivity.this, EscolherMatPrimaActivity.class));
        finish();
    }
}

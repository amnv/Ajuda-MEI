package ajudamei.allan_arthur.com.ajuda_mei;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
}

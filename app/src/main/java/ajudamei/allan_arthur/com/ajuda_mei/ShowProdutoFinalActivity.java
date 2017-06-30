package ajudamei.allan_arthur.com.ajuda_mei;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowProdutoFinalActivity extends Activity {
    private ListView registro;
    private ImageView imagem;
    private TextView nome;
    private UsoGeral ug;
    private DatabaseProdutoFinal db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_produto_final);
        ug = (UsoGeral) getApplication();
//        registro = (ListView) findViewById(R.id.extrato_produto_final);
        imagem = (ImageView) findViewById(R.id.img_produto_final);
        nome = (TextView) findViewById(R.id.txt_nome_prod);

        nome.setText(ug.getProduto().getNome());
        imagem.setImageBitmap(ug.getProduto().getFoto());

        db = new DatabaseProdutoFinal(this);
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
        db.delete(ug.getProduto());
        Toast.makeText(getApplicationContext(), "Item deletado!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ShowProdutoFinalActivity.this,EscolherProdutoFinalActivity.class);
        startActivity(intent);
        return(super.onOptionsItemSelected(item));
    }

    @Override
    protected void onStart() {
        super.onStart();
        ug = (UsoGeral) getApplication();


//        List<Registro> temp = db.getAllRegistros(g.getProduto());
//        AdapterRegistro adapter = new AdapterRegistro(ShowProdutoFinalActivity.this, R.layout.itemlista, temp);
//        if(temp != null) {
//            registro.setAdapter(adapter);
//        }
    }

}
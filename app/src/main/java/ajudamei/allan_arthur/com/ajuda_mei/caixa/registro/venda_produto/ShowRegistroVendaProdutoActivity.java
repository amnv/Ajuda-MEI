package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.venda_produto;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import ajudamei.allan_arthur.com.ajuda_mei.DatabaseProdutoFinal;
import ajudamei.allan_arthur.com.ajuda_mei.EscolherProdutoFinalActivity;
import ajudamei.allan_arthur.com.ajuda_mei.ItemProdutoFinal;
import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.ShowProdutoFinalActivity;
import ajudamei.allan_arthur.com.ajuda_mei.UsoGeral;

public class ShowRegistroVendaProdutoActivity extends Activity {
    private ListView registro;
    private ImageView imagem;
    private TextView nome;
    private TextView quantidade;
    private UsoGeral ug;
    private DatabaseProdutoFinal db;
    private Button bt_decrementaQnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_registro_venda_produto);
        ug = (UsoGeral) getApplication();
        registro = (ListView) findViewById(R.id.extrato_produto_final);
        imagem = (ImageView) findViewById(R.id.img_venda_produto);
        bt_decrementaQnt = (Button) findViewById(R.id.bt_decrementa);
        nome = (TextView) findViewById(R.id.txt_nome_venda_produto);
        quantidade = (TextView) findViewById(R.id.txt_quantidade_venda_produto);

        nome.setText(ug.getProduto().getNome());
        quantidade.setText(""+ug.getProduto().getQuantidade());
        imagem.setImageBitmap(ug.getProduto().getFoto());

        db = new DatabaseProdutoFinal(this);
        bt_decrementaQnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemProdutoFinal item = ug.getProduto();
                double qnt = item.getQuantidade()  -1;
                item.setQuantidade(qnt);
                db.update(item);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        db.delete(ug.getProduto());
        Toast.makeText(getApplicationContext(), "Item deletado!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ShowRegistroVendaProdutoActivity.this, EscolherProdutoFinalActivity.class);
        startActivity(intent);
        return (super.onOptionsItemSelected(item));
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

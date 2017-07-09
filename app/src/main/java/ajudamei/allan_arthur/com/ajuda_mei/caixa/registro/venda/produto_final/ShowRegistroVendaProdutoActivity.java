package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.venda.produto_final;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import ajudamei.allan_arthur.com.ajuda_mei.database.DatabaseProdutoFinal;
import ajudamei.allan_arthur.com.ajuda_mei.controle.estoque.produto_final.EscolherProdutoFinalActivity;
import ajudamei.allan_arthur.com.ajuda_mei.domain.item.ItemProdutoFinal;
import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.UsoGeral;

public class ShowRegistroVendaProdutoActivity extends Activity implements NumberPicker.OnValueChangeListener {
    private ListView registro;
    private ImageView imagem;
    private TextView nome;
    private TextView quantidade;
    private UsoGeral ug;
    private boolean adicionou = false;
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
                show(item);
            }
        });
    }

    public void show(final ItemProdutoFinal item) {

        final Dialog d = new Dialog(ShowRegistroVendaProdutoActivity.this);
        d.setTitle("Escolha a quantidade");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue((int)item.getQuantidade());
        np.setMinValue(0);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Qnt de itens escolhidos: "
                        + String.valueOf(np.getValue()), Toast.LENGTH_SHORT).show();
                db.modify(item, np.getValue());
                d.dismiss(); // dismiss the dialog
            }
        });
        d.show();
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

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ShowRegistroVendaProdutoActivity.this, VendaProdutoActivity.class));
        finish();
    }
}

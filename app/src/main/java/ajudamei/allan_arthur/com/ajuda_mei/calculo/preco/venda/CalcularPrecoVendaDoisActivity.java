package ajudamei.allan_arthur.com.ajuda_mei.calculo.preco.venda;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ajudamei.allan_arthur.com.ajuda_mei.database.DatabaseProdutoFinal;
import ajudamei.allan_arthur.com.ajuda_mei.MainActivity;
import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.UsoGeral;
import ajudamei.allan_arthur.com.ajuda_mei.domain.item.ItemProdutoFinal;

public class CalcularPrecoVendaDoisActivity extends Activity {

    private DatabaseProdutoFinal db;
    private UsoGeral g;

    private ImageView img;
    private TextView tvNome;
    private EditText margemAtual;
    private EditText novoPreco;
    private Button calcular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_preco_venda_dois);

        img = (ImageView) findViewById(R.id.img_view_foto);
        tvNome = (TextView) findViewById(R.id.tv_nome_produto);
        margemAtual = (EditText) findViewById(R.id.txt_margem_atual);
        novoPreco = (EditText) findViewById(R.id.txt_novo_preco);
        calcular = (Button) findViewById(R.id.bt_add_empregado);

        g = (UsoGeral) getApplication();
        final ItemProdutoFinal item = g.getTemp();
        db = new DatabaseProdutoFinal(this);

        img.setImageBitmap(item.getFoto());
        tvNome.setText(item.getNome());
        double temp = ((item.getPreco() - item.getCustoProducao()) / item.getCustoProducao()) * 100;
        margemAtual.setText(String.valueOf(temp) + "%");

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double np = Double.parseDouble(novoPreco.getText().toString());
                double custop = item.getCustoProducao();
                final double resp = ((np - custop) / custop) * 100;
                new AlertDialog.Builder(CalcularPrecoVendaDoisActivity.this)
                        .setTitle("Cálculo da nova margem")
                        .setMessage("Sua nova margem é de: " + resp + "%")
                        .setNegativeButton("Cancelar", null) // dismisses by default
                        .setPositiveButton("Att Preço", new DialogInterface.OnClickListener() {
                            @Override public void onClick(DialogInterface dialog, int which) {
                                db.modifyPreco(g.getTemp(), resp);
                                Toast.makeText(getApplicationContext(), "Preço atualizado!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CalcularPrecoVendaDoisActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .create()
                        .show();

            }
        });
    }
}

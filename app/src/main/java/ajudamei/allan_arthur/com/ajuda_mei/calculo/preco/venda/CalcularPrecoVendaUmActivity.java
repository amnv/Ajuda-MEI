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
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import ajudamei.allan_arthur.com.ajuda_mei.database.DatabaseProdutoFinal;
import ajudamei.allan_arthur.com.ajuda_mei.MainActivity;
import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.UsoGeral;
import ajudamei.allan_arthur.com.ajuda_mei.domain.item.ItemProdutoFinal;

public class CalcularPrecoVendaUmActivity extends Activity {
    private DatabaseProdutoFinal db;
    private UsoGeral g;

    private ImageView img;
    private TextView tvNome;
    private EditText custo;
    private EditText margem;
    private Button calcular;
    private SeekBar barra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_preco_venda_um);

        img = (ImageView) findViewById(R.id.img_view_foto);
        tvNome = (TextView) findViewById(R.id.tv_nome_produto);
        custo = (EditText) findViewById(R.id.txt_margem_atual);
        margem = (EditText) findViewById(R.id.txt_novo_preco);
        calcular = (Button) findViewById(R.id.bt_add_empregado);
        barra = (SeekBar) findViewById(R.id.seekBar);

        g = (UsoGeral) getApplication();
        ItemProdutoFinal item = g.getTemp();
        db = new DatabaseProdutoFinal(this);

        img.setImageBitmap(item.getFoto());
        tvNome.setText(item.getNome());
        custo.setText(String.valueOf(item.getCustoProducao()));


        barra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                progressChanged = progress;
                margem.setText(String.valueOf(progress));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double marg = Double.parseDouble(margem.getText().toString());
                double cust = Double.parseDouble(custo.getText().toString());
                final double resp = cust + (cust * (marg / 100));
                new AlertDialog.Builder(CalcularPrecoVendaUmActivity.this)
                        .setTitle("Cálculo do preço de venda")
                        .setMessage("O preço final do seu produto foi de: R$" + resp)
                        .setNegativeButton("Cancelar", null) // dismisses by default
                        .setPositiveButton("Att Preço", new DialogInterface.OnClickListener() {
                            @Override public void onClick(DialogInterface dialog, int which) {
                                db.modifyPreco(g.getTemp(), resp);
                                Toast.makeText(getApplicationContext(), "Preço atualizado!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CalcularPrecoVendaUmActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .create()
                        .show();

            }
        });
    }
}

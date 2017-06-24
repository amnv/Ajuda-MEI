package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.boleto;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ajudamei.allan_arthur.com.ajuda_mei.R;

public class AdicionarBoletoActivity extends Activity {
    private DatabaseRegistroBoleto db;
    private Button adicionar;

    private EditText descricao;
    private EditText preco;
    private EditText data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_registro_boleto);

        db = new DatabaseRegistroBoleto(this);

        adicionar = (Button) findViewById(R.id.bt_adicionar_boleto);
        descricao = (EditText) findViewById(R.id.editTextBoletoDescricao);
        preco = (EditText) findViewById(R.id.editTextBoletoPreco);
        data = (EditText) findViewById(R.id.editTextBoletoData);

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boleto aux = new Boleto(descricao.getText().toString(), Double.parseDouble(preco.getText().toString()), data.getText().toString());

                db.insert(aux);
            }
        });
    }
}
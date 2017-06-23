package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.venda_produto;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ajudamei.allan_arthur.com.ajuda_mei.R;

public class AdicionarVendaActivity extends Activity {
    private DatabaseRegistroVenda db;
    private Button adicionar;

    private EditText descricao;
    private EditText preco;
    private EditText data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_registro_venda);

        db = new DatabaseRegistroVenda(this);

        adicionar = (Button) findViewById(R.id.bt_adicionar_venda);
        descricao = (EditText) findViewById(R.id.editTextVendaDescricao);
        preco = (EditText) findViewById(R.id.editTextVendaPreco);
        data = (EditText) findViewById(R.id.editTextVendaData);

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Venda aux = new Venda(descricao.getText().toString(), Double.parseDouble(preco.getText().toString()), data.getText().toString());

                db.insert(aux);
            }
        });
    }
}
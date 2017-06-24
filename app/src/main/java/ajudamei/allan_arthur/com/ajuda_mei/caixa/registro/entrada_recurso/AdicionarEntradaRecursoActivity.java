package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.entrada_recurso;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ajudamei.allan_arthur.com.ajuda_mei.R;

public class AdicionarEntradaRecursoActivity extends Activity {
    DatabaseRegistroRecurso db;
    private Button adicionar;

    private EditText descricao;
    private EditText preco;
    private EditText data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_registro_entrada_recurso);

        db = new DatabaseRegistroRecurso(this);

        adicionar = (Button) findViewById(R.id.bt_adicionar_entrada_recurso);
        descricao = (EditText) findViewById(R.id.editTextEntradaRecursoDescricao);
        preco = (EditText) findViewById(R.id.editTextEntradaRecursoPreco);
        data = (EditText) findViewById(R.id.editTextEntradaRecursoData);

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Entrada aux = new Entrada(descricao.getText().toString(), Double.parseDouble(preco.getText().toString()), data.getText().toString());

                db.insert(aux);
            }
        });
    }
}
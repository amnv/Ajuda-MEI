package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.aquisicao_mat_prima;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ajudamei.allan_arthur.com.ajuda_mei.R;

public class AdicionarAquisicaoActivity extends Activity {
    private DatabaseRegistroAquisicao db;
    private Button adicionar;

    private EditText descricao;
    private EditText preco;
    private EditText data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_registro_aquisicao);

        db = new DatabaseRegistroAquisicao(this);

        adicionar = (Button) findViewById(R.id.bt_adicionar_aquisicao);
        descricao = (EditText) findViewById(R.id.editTextAquisicaoDescricao);
        preco = (EditText) findViewById(R.id.editTextAquisicaoPreco);
        data = (EditText) findViewById(R.id.editTextAquisicaoData);

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aquisicao aux = new Aquisicao(descricao.getText().toString(), Double.parseDouble(preco.getText().toString()), data.getText().toString(), 999, "tipo");

                db.insert(aux);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdicionarAquisicaoActivity.this, AquisicaoMatPrimaActivity.class));
        finish();
    }
}
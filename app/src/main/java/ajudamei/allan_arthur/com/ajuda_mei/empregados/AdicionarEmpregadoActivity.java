package ajudamei.allan_arthur.com.ajuda_mei.empregados;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.controle.estoque.materia_prima.AdicionarMatPrimaActivity;
import ajudamei.allan_arthur.com.ajuda_mei.controle.estoque.materia_prima.EscolherMatPrimaActivity;
import ajudamei.allan_arthur.com.ajuda_mei.database.DatabaseEmpregado;
import ajudamei.allan_arthur.com.ajuda_mei.domain.pessoa.Empregado;

public class AdicionarEmpregadoActivity extends Activity {
    private DatabaseEmpregado db;
    private EditText nome;
    private EditText salario;
    private EditText periodicidade;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_empregado);

        db = new DatabaseEmpregado(this);
        nome = (EditText) findViewById(R.id.txt_nome);
        salario = (EditText) findViewById(R.id.txt_salario);
        periodicidade = (EditText) findViewById(R.id.txt_periodicidade);
        add = (Button) findViewById(R.id.bt_add_empregado);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.insert(new Empregado(nome.getText().toString(),
                        Double.parseDouble(salario.getText().toString()),
                        Integer.parseInt(periodicidade.getText().toString())));
                Intent intent = new Intent(AdicionarEmpregadoActivity.this, ListaEmpregadosActivity.class);
                Toast.makeText(getApplicationContext(), "Empregado adicionado!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }
}

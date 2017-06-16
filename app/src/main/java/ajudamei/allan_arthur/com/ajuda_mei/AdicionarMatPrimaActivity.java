package ajudamei.allan_arthur.com.ajuda_mei;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdicionarMatPrimaActivity extends Activity {
    private DatabaseMateriaPrima db;




    private Button confirmarAdd;
    private EditText nome;
    private EditText quantidade;
    private EditText preco;
    private EditText tamanho;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_mat_prima);

        db = new DatabaseMateriaPrima(this);

        confirmarAdd = (Button) findViewById(R.id.bt_adicionar_mat_prima_ao_estoque);
        nome = (EditText) findViewById(R.id.editTextNome);
        quantidade = (EditText) findViewById(R.id.editTextQuantidade);
        preco = (EditText) findViewById(R.id.editTextPreco);
        tamanho = (EditText) findViewById(R.id.editTextTamanho);

        confirmarAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemMateriaPrima aux = new ItemMateriaPrima(nome.getText().toString() ,tamanho.getText().toString(), Double.parseDouble(quantidade.getText().toString()),
                        "avista", Double.parseDouble(preco.getText().toString()),null);

                db.insert(aux);
            }
        });
    }
}

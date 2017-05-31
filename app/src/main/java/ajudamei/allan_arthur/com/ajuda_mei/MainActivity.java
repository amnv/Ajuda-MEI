package ajudamei.allan_arthur.com.ajuda_mei;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bt1 = (Button) findViewById(R.id.bt_controle_seu_estoque);
        bt1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TipoEstoqueActivity.class);
                startActivity(intent);
            }
        });

        final Button bt2 = (Button) findViewById(R.id.bt_caixa_da_empresa);
        bt2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CaixaDaEmpresaActivity.class);
                startActivity(intent);
            }
        });

        final Button bt3 = (Button) findViewById(R.id.bt_preco_venda);
        bt3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Fazer próxima tela", Toast.LENGTH_SHORT).show();
            }
        });

        final Button bt4 = (Button) findViewById(R.id.bt_ctr_pessoal);
        bt4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListaPessoalActivity.class);
                startActivity(intent);
            }
        });

        final Button bt5 = (Button) findViewById(R.id.bt_ctr_empregados);
        bt5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EmpregadosActivity.class);
                startActivity(intent);
            }
        });


    }
}

package ajudamei.allan_arthur.com.ajuda_mei.empregados;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ajudamei.allan_arthur.com.ajuda_mei.MainActivity;
import ajudamei.allan_arthur.com.ajuda_mei.R;

public class EmpregadosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empregados);

        final Button bt1 = (Button) findViewById(R.id.bt_lista_empregados);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpregadosActivity.this, ListaEmpregadosActivity.class);
                startActivity(intent);
            }
        });

        final Button bt2 = (Button) findViewById(R.id.bt_conheca_os_direitos);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpregadosActivity.this, DireitosEmpregadosActivity.class);
                startActivity(intent);
            }
        });

        final Button bt3 = (Button) findViewById(R.id.bt_calcular_custo_empregado);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Fazer próxima tela", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EmpregadosActivity.this, MainActivity.class));
        finish();
    }

}

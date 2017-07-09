package ajudamei.allan_arthur.com.ajuda_mei.calculo.preco.venda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ajudamei.allan_arthur.com.ajuda_mei.MainActivity;
import ajudamei.allan_arthur.com.ajuda_mei.R;

public class CalcularPrecoVendaMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_preco_venda_main);

        final Button bt1 = (Button) findViewById(R.id.bt_estimar_ganho);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalcularPrecoVendaMainActivity.this,CalcularPrecoVendaUmActivity.class);
                startActivity(intent);
            }
        });

        final Button bt2 = (Button) findViewById(R.id.bt_ver_margem_meu_preco);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalcularPrecoVendaMainActivity.this,CalcularPrecoVendaDoisActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(CalcularPrecoVendaMainActivity.this, MainActivity.class));
        finish();

    }
}

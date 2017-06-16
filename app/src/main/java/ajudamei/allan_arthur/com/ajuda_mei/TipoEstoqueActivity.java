package ajudamei.allan_arthur.com.ajuda_mei;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TipoEstoqueActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_estoque);

        final Button bt1 = (Button) findViewById(R.id.bt_mat_prima);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TipoEstoqueActivity.this,EscolherMatPrimaActivity.class);
                startActivity(intent);
            }
        });

        final Button bt2 = (Button) findViewById(R.id.bt_produtos_finais);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Fazer próxima tela", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

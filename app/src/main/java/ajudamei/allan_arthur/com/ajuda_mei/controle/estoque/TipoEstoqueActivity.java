package ajudamei.allan_arthur.com.ajuda_mei.controle.estoque;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ajudamei.allan_arthur.com.ajuda_mei.MainActivity;
import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.controle.estoque.materia_prima.EscolherMatPrimaActivity;
import ajudamei.allan_arthur.com.ajuda_mei.controle.estoque.produto_final.EscolherProdutoFinalActivity;

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

        final Button bt2 = (Button) findViewById(R.id.bt_ver_margem_meu_preco);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TipoEstoqueActivity.this,EscolherProdutoFinalActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(TipoEstoqueActivity.this, MainActivity.class));
        finish();

    }

}

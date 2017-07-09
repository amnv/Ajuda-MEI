package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.aquisicao.materia_prima;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.UsoGeral;
import ajudamei.allan_arthur.com.ajuda_mei.adapter.item.AdapterMateria;
import ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.CaixaDaEmpresaActivity;
import ajudamei.allan_arthur.com.ajuda_mei.database.DatabaseMateriaPrima;
import ajudamei.allan_arthur.com.ajuda_mei.domain.item.ItemMateriaPrima;

public class AquisicaoMatPrimaActivity extends Activity {

    DatabaseMateriaPrima db;
    ListView aquisicoes;
    UsoGeral ug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_aquisicao_mat_prima);

        db = new DatabaseMateriaPrima(this);

        aquisicoes = (ListView) findViewById(R.id.lista_registro_aquisicao);
        View empty = findViewById(R.id.empty_mat_prima);
        aquisicoes.setEmptyView(empty);
        aquisicoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemMateriaPrima item = (ItemMateriaPrima) parent.getItemAtPosition(position);
                ug = (UsoGeral) getApplication();
                ug.setItem(item);
                Intent intent = new Intent(AquisicaoMatPrimaActivity.this, ShowRegistroAquisicaoMatPrimaActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<ItemMateriaPrima> temp = db.getAllItens();
        Toast.makeText(getApplicationContext(), "Qnt de itens: " + temp.size(), Toast.LENGTH_SHORT).show();
        AdapterMateria adapter = new AdapterMateria(AquisicaoMatPrimaActivity.this, R.layout.itemlista, temp);
        if (temp != null) {
            aquisicoes.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AquisicaoMatPrimaActivity.this, CaixaDaEmpresaActivity.class));
        finish();
    }
}

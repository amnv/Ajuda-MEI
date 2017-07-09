package ajudamei.allan_arthur.com.ajuda_mei.controle.estoque.produto_final;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.UsoGeral;
import ajudamei.allan_arthur.com.ajuda_mei.adapter.item.AdapterMateria;
import ajudamei.allan_arthur.com.ajuda_mei.database.DatabaseMateriaPrima;
import ajudamei.allan_arthur.com.ajuda_mei.domain.item.ItemMateriaPrima;

public class SelecionarMatPrimaActivity extends Activity implements NumberPicker.OnValueChangeListener{
    private DatabaseMateriaPrima db;
    private ListView itens;
    private UsoGeral g;
    private boolean adicionou = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_mat_prima);
        db = new DatabaseMateriaPrima(this);

        itens = (ListView) findViewById(R.id.lv_mat_prima);

        itens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemMateriaPrima item = (ItemMateriaPrima) parent.getItemAtPosition(position);
                g = (UsoGeral) getApplication();
                List<ItemMateriaPrima> aux = g.getItensParaDecrementar();
                aux.add(item);
                g.setItensParaDecrementar(aux);
                show(item);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        List<ItemMateriaPrima> temp = db.getAllItens();
        Toast.makeText(getApplicationContext(), "Qnt de itens: " + temp.size(), Toast.LENGTH_SHORT).show();
        AdapterMateria adapter = new AdapterMateria(SelecionarMatPrimaActivity.this, R.layout.itemlista, temp);

        if(temp != null) {
            itens.setAdapter(adapter);
        }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }


    public void show(ItemMateriaPrima item)
    {

        final Dialog d = new Dialog(SelecionarMatPrimaActivity.this);
        d.setTitle("Escolha a quantidade");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue((int)item.getQuantidade());
        np.setMinValue(0);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Qnt de itens escolhidos: "
                        + String.valueOf(np.getValue()), Toast.LENGTH_SHORT).show();
                g = (UsoGeral) getApplication();
                List<Integer> aux = g.getQuantidadesParaDecrementar();
                aux.add(np.getValue());
                g.setQuantidadesParaDecrementar(aux);
                adicionou = true;
                Intent intent = new Intent(SelecionarMatPrimaActivity.this, AdicionarProdutoFinalActivity.class);
                startActivity(intent);
                d.dismiss(); // dismiss the dialog
            }
        });
        d.show();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SelecionarMatPrimaActivity.this, AdicionarProdutoFinalActivity.class));
        finish();
    }
}

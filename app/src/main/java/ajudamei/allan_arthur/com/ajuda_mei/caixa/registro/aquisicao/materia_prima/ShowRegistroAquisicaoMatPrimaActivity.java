package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.aquisicao.materia_prima;

import android.app.Dialog;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.UsoGeral;
import ajudamei.allan_arthur.com.ajuda_mei.database.DatabaseMateriaPrima;
import ajudamei.allan_arthur.com.ajuda_mei.domain.item.ItemMateriaPrima;

public class ShowRegistroAquisicaoMatPrimaActivity extends Activity implements NumberPicker.OnValueChangeListener {
    private ListView registro;
    private ImageView imagem;
    private TextView nome;
    private TextView quantidade;
    private UsoGeral ug;
    private boolean adicionou = false;
    private DatabaseMateriaPrima db;
    private Button bt_incrementaQnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_registro_aquisicao_mat_prima);
        ug = (UsoGeral) getApplication();
        registro = (ListView) findViewById(R.id.extrato_aquisicao);
        imagem = (ImageView) findViewById(R.id.img_aquisicao_mat_prima);
        bt_incrementaQnt = (Button) findViewById(R.id.bt_incrementa);
        nome = (TextView) findViewById(R.id.txt_nome_aquisicao_mat_prima);
        quantidade = (TextView) findViewById(R.id.txt_quantidade_aquisicao);

        nome.setText(ug.getItem().getNome());
        quantidade.setText(""+ug.getItem().getQuantidade());
        imagem.setImageBitmap(ug.getItem().getFoto());

        db = new DatabaseMateriaPrima(this);
        bt_incrementaQnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemMateriaPrima item = ug.getItem();
                show(item);
            }
        });
    }

    private void show(final ItemMateriaPrima item) {
        final Dialog d = new Dialog(ShowRegistroAquisicaoMatPrimaActivity.this);
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
                db.modify(item, np.getValue(), false);
                d.dismiss(); // dismiss the dialog
            }
        });
        d.show();

    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

    }
}

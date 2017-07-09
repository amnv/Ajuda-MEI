package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.entrada.recurso;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.database.DatabaseRegistroRecurso;
import ajudamei.allan_arthur.com.ajuda_mei.domain.registro.Recurso;

public class AdicionarEntradaRecursoActivity extends Activity {
    DatabaseRegistroRecurso db;
    private Button adicionar;
    private Button bt_date;
    private EditText descricao;
    private EditText preco;
    private EditText data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_registro_entrada_recurso);

        db = new DatabaseRegistroRecurso(this);

        adicionar = (Button) findViewById(R.id.bt_adicionar_entrada_recurso);
        bt_date = (Button) findViewById(R.id.bt_calendario_entrada_recurso);
        descricao = (EditText) findViewById(R.id.editTextEntradaRecursoDescricao);
        preco = (EditText) findViewById(R.id.editTextEntradaRecursoPreco);
        data = (EditText) findViewById(R.id.editTextEntradaRecursoData);

        bt_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar mcurrentDate= Calendar.getInstance();
                int mYear=mcurrentDate.get(Calendar.YEAR);
                int mMonth=mcurrentDate.get(Calendar.MONTH);
                int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(AdicionarEntradaRecursoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        String mes;
                        String dia;
                        String ano = String.valueOf(selectedyear);

                        if(selectedmonth < 10 ) {
                            mes = "0" + String.valueOf(selectedmonth+1);
                        }
                        else {
                            mes = String.valueOf(selectedmonth+1);
                        }

                        if (selectedday < 10) {
                            dia = "0" + String.valueOf(selectedday);
                        } else {
                            dia = "0" + String.valueOf(selectedday);
                        }

                        data.setText(dia + "/" + mes + "/" + ano);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Selecione uma data");
                mDatePicker.show();
            }
        });

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recurso aux = new Recurso(descricao.getText().toString(), Double.parseDouble(preco.getText().toString()), data.getText().toString(), 999, "tipo");

                db.insert(aux);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdicionarEntradaRecursoActivity.this, EntradaRecursosActivity.class));
        finish();
    }
}
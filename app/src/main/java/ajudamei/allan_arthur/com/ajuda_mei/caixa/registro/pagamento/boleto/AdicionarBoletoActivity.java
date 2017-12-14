package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.pagamento.boleto;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import ajudamei.allan_arthur.com.ajuda_mei.R;
import ajudamei.allan_arthur.com.ajuda_mei.database.archComponent.RegistroBoletoDatabase;
import ajudamei.allan_arthur.com.ajuda_mei.database.archComponent.RegistroBoletoRoom;

public class AdicionarBoletoActivity extends Activity {
    //private DatabaseRegistroBoleto db;
    private RegistroBoletoDatabase db;
    private Button adicionar;
    private Button bt_date;
    private EditText descricao;
    private EditText preco;
    private EditText data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_registro_boleto);

        //db = new DatabaseRegistroBoleto(this);
        db = Room.databaseBuilder(this, RegistroBoletoDatabase.class, "RegistroBoletoRoom").build();
        adicionar = (Button) findViewById(R.id.bt_adicionar_boleto);
        bt_date = (Button) findViewById(R.id.bt_calendario_boleto);
        descricao = (EditText) findViewById(R.id.editTextBoletoDescricao);
        preco = (EditText) findViewById(R.id.editTextBoletoPreco);
        data = (EditText) findViewById(R.id.editTextBoletoData);

        bt_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar mcurrentDate= Calendar.getInstance();
                int mYear=mcurrentDate.get(Calendar.YEAR);
                int mMonth=mcurrentDate.get(Calendar.MONTH);
                int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(AdicionarBoletoActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                //Boleto aux = new Boleto(descricao.getText().toString(), Double.parseDouble(preco.getText().toString()), data.getText().toString(), 999, "tipo");
                new BancoAsync().execute();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdicionarBoletoActivity.this, PagamentoBoletoActivity.class));
        finish();
    }

    public class BancoAsync extends AsyncTask<Void, Void, Void>
    {
       @Override
        protected Void doInBackground(Void... voids) {
           RegistroBoletoRoom aux = new RegistroBoletoRoom();
           aux.setDescricao(descricao.getText().toString());
           aux.setPreco(Double.parseDouble(preco.getText().toString()));
           aux.setData(data.getText().toString());
           db.registroBoletoDao().insert(aux);
           return null;
       }

        @Override
        protected void onPostExecute(Void aVoid) {
            startActivity(new Intent(AdicionarBoletoActivity.this, PagamentoBoletoActivity.class));
        }
    }
}
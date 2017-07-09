package ajudamei.allan_arthur.com.ajuda_mei.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.domain.pessoa.Empregado;
import ajudamei.allan_arthur.com.ajuda_mei.domain.registro.Recurso;

/**
 * Created by arthur on 09/07/17.
 */

public class DatabaseEmpregado extends SQLiteOpenHelper {
    final static String TABLE_NAME = "empregados";
    final static String _ID = "_id";

    final static String EMP_NOME = "nome";
    final static String EMP_SALARIO = "salario";
    final static String EMP_PERIOD = "periodicidade";

    final private static String CREATE_CMD =

            "CREATE TABLE "+TABLE_NAME+" (" + _ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + EMP_NOME + " TEXT NOT NULL, "
                    + EMP_SALARIO + " TEXT NOT NULL, "
                    + EMP_PERIOD + " TEXT NOT NULL "
                    + ")";

    final private static String NAME = "empregados_db";
    final private static Integer VERSION = 1;
    final private Context mContext;

    public DatabaseEmpregado(Context context) {
        super(context, NAME, null, VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(Empregado emp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseEmpregado.EMP_NOME, emp.getNome());
        values.put(DatabaseEmpregado.EMP_SALARIO, emp.getSalario());
        values.put(DatabaseEmpregado.EMP_PERIOD, emp.getPeriodicidade());

        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();
    }

    public List<Empregado> getAllEmpregados(){
        List<Empregado> aux = new ArrayList<Empregado>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Empregado temp = new Empregado(cursor.getString(1),Double.parseDouble(cursor.getString(2)), Integer.parseInt(cursor.getString(3)));
                aux.add(temp);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return aux;
    }
}

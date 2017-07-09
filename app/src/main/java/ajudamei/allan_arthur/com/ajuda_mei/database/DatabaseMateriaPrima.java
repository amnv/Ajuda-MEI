package ajudamei.allan_arthur.com.ajuda_mei.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.domain.item.ItemMateriaPrima;
import ajudamei.allan_arthur.com.ajuda_mei.domain.registro.Registro;

/**
 * Created by Allan on 09/06/2017.
 */

public class DatabaseMateriaPrima extends SQLiteOpenHelper {

    final static String TABLE_NAME = "materiaprima";
    final static String TABLE2_NAME = "registro";
    final static String _ID = "_id";
    final static String _ID2 = "_rid";

    final static String ITEM_NOME = "name";
    final static String ITEM_TAMANHO = "tamanho";
    final static String ITEM_QUANTIDADE = "quantidade";
    final static String ITEM_PRECO = "preço";
    final static String ITEM_FORMA_AQUISICAO = "formaAquisicao";
    final static String ITEM_DATA = "dataAdicao";
    final static String ITEM_FOTO = "foto";

    final static String REG_MAT_PRIMA = "matPrima";
    final static String REG_DATA = "dataRegistro";
    final static String REG_SALDO = "saldo";
    final static String REG_TIPO = "tipo";

    final private static String CREATE_CMD =

            "CREATE TABLE "+TABLE_NAME+" (" + _ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ITEM_NOME + " TEXT NOT NULL, "
                    + ITEM_TAMANHO + " TEXT NOT NULL, "
                    + ITEM_QUANTIDADE + " TEXT NOT NULL, "
                    + ITEM_PRECO + " TEXT NOT NULL, "
                    + ITEM_FORMA_AQUISICAO + " TEXT NOT NULL, "
                    + ITEM_FOTO + " BLOB, "
                    + ITEM_DATA + " TEXT NOT NULL"
                    + ")";

    final private static String CREATE2_CMD =

            "CREATE TABLE " + TABLE2_NAME + " (" + _ID2
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + REG_MAT_PRIMA + " TEXT NOT NULL, "
                    + REG_DATA + " TEXT NOT NULL, "
                    + REG_SALDO + " TEXT NOT NULL, "
//                    + REG_TIPO + " TEXT NOT NULL, "
                    + " FOREIGN KEY(matPrima) REFERENCES materiaprima(name))";
    final private static String NAME = "materia_db";
    final private static Integer VERSION = 1;
    final private Context mContext;

    public DatabaseMateriaPrima(Context context){
        super(context, NAME, null, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_CMD);
        db.execSQL(CREATE2_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(ItemMateriaPrima item) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        ContentValues values = new ContentValues();
        values.put(DatabaseMateriaPrima.ITEM_NOME, item.getNome());
        values.put(DatabaseMateriaPrima.ITEM_TAMANHO, item.getTamanho());
        values.put(DatabaseMateriaPrima.ITEM_QUANTIDADE, item.getQuantidade());
        values.put(DatabaseMateriaPrima.ITEM_PRECO, item.getPreco());
        values.put(DatabaseMateriaPrima.ITEM_FORMA_AQUISICAO, item.getFormaDeAquisicao());
        values.put(DatabaseMateriaPrima.ITEM_FOTO, getBytes(item.getFoto()));
        values.put(DatabaseMateriaPrima.ITEM_DATA, dateFormat.format(date));

        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        insert(item.getNome(), dateFormat.format(date),(int)item.getQuantidade(), "adicao");
        db.close();
    }

    public void insert(String nome, String data, int modificacao, String tipo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseMateriaPrima.REG_DATA, data);
        values.put(DatabaseMateriaPrima.REG_MAT_PRIMA, nome);
        values.put(DatabaseMateriaPrima.REG_SALDO, modificacao);
        //values.put(DatabaseMateriaPrima.REG_TIPO, tipo);

        db.insertWithOnConflict(TABLE2_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }

    public void modify(ItemMateriaPrima item, int quantidade, boolean op){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + _ID +" FROM "
                + TABLE_NAME + " WHERE " + ITEM_NOME + "= '" + item.getNome()+ "'";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                ContentValues values = new ContentValues();

                if(op) {
                    values.put(DatabaseMateriaPrima.ITEM_QUANTIDADE, item.getQuantidade() - quantidade);
                } else {
                    values.put(DatabaseMateriaPrima.ITEM_QUANTIDADE, item.getQuantidade() + quantidade);
                }

                db.update(TABLE_NAME, values, "_id= "+ cursor.getInt(0), null);


            } while (cursor.moveToNext());
        }
        cursor.close();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        insert(item.getNome(), dateFormat.format(date), -(quantidade), "retirada");

    }

    public void delete (ItemMateriaPrima item)  {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + ITEM_NOME + "= '" + item.getNome() + "'");
        database.close();
    }

    public List<Registro> getAllRegistros(ItemMateriaPrima item){
        List<Registro> aux = new ArrayList<Registro>();

        String query = "SELECT " + REG_DATA +" , " + REG_SALDO + " FROM "
                    + TABLE2_NAME + " WHERE " + REG_MAT_PRIMA + "= '" + item.getNome()+ "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Registro temp = new Registro(cursor.getString(0), Integer.parseInt(cursor.getString(1)),
                        "add");
                aux.add(temp);
            } while (cursor.moveToNext());

        }
        cursor.close();

        return aux;
    }

    public List<ItemMateriaPrima> getAllItens(){
        List<ItemMateriaPrima> aux = new ArrayList<ItemMateriaPrima>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                ItemMateriaPrima temp = new ItemMateriaPrima(cursor.getString(1),cursor.getString(2), Double.parseDouble(cursor.getString(3)),
                         cursor.getString(5),Double.parseDouble(cursor.getString(4)), getImage(cursor.getBlob(6)), cursor.getString(7));
                aux.add(temp);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return aux;
    }

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    void deleteDatabase() {
        mContext.deleteDatabase(NAME);
    }


}

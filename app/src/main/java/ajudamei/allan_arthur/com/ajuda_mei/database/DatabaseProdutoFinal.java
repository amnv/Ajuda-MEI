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

import ajudamei.allan_arthur.com.ajuda_mei.domain.item.ItemProdutoFinal;
import ajudamei.allan_arthur.com.ajuda_mei.domain.registro.Registro;

/**
 * Created by Allan on 09/06/2017.
 */

public class DatabaseProdutoFinal extends SQLiteOpenHelper {

    final static String TABLE_NAME = "produtos_finais";
    final static String TABLE2_NAME = "registro";
    final static String _ID = "_id";

    final static String ITEM_NOME = "name";
    final static String ITEM_TAMANHO = "tamanho";
    final static String ITEM_QUANTIDADE = "quantidade";
    final static String ITEM_PRECO = "preço";
    final static String ITEM_DATA = "dataAdicao";
    final static String ITEM_FOTO = "foto";
    final static String ITEM_CUSTO_PROD = "custoProducao";

    final static String _ID2 = "_id2";
    final static String REG_PROD_FINAL = "prodFinal";
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
                    + ITEM_FOTO + " BLOB, "
                    + ITEM_DATA + " TEXT NOT NULL, "
                    + ITEM_CUSTO_PROD + " TEXT NOT NULL"
                    + ")";
    final private static String CREATE2_CMD =

            "CREATE TABLE " + TABLE2_NAME + " (" + _ID2
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + REG_PROD_FINAL + " TEXT NOT NULL, "
                    + REG_DATA + " TEXT NOT NULL, "
                    + REG_SALDO + " TEXT NOT NULL, "
//                    + REG_TIPO + " TEXT NOT NULL, "
                    + " FOREIGN KEY(prodFinal) REFERENCES materiaprima(name))";

    final private static String NAME = "produto_db";
    final private static Integer VERSION = 1;
    final private Context mContext;

    public DatabaseProdutoFinal(Context context) {
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

    public void insert(ItemProdutoFinal item, double custoProducao) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        ContentValues values = new ContentValues();

        values.put(DatabaseProdutoFinal.ITEM_NOME, item.getNome());
        values.put(DatabaseProdutoFinal.ITEM_TAMANHO, item.getTamanho());
        values.put(DatabaseProdutoFinal.ITEM_QUANTIDADE, item.getQuantidade());
        values.put(DatabaseProdutoFinal.ITEM_PRECO, item.getPreco());
        values.put(DatabaseProdutoFinal.ITEM_FOTO, getBytes(item.getFoto()));
        values.put(DatabaseProdutoFinal.ITEM_DATA, dateFormat.format(date));
        values.put(DatabaseProdutoFinal.ITEM_CUSTO_PROD, custoProducao);

        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        insert(item.getNome(), dateFormat.format(date), (int)item.getQuantidade(), "adicao");
        db.close();
    }

    public void insert(String nome, String data, int modificacao, String tipo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseProdutoFinal.REG_DATA, data);
        values.put(DatabaseProdutoFinal.REG_PROD_FINAL, nome);
        values.put(DatabaseProdutoFinal.REG_SALDO, modificacao);
        //values.put(DatabaseProdutoFinal.REG_TIPO, tipo);

        db.insertWithOnConflict(TABLE2_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }

    public void update(ItemProdutoFinal item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseProdutoFinal.ITEM_NOME, item.getNome());
        values.put(DatabaseProdutoFinal.ITEM_TAMANHO, item.getTamanho());
        values.put(DatabaseProdutoFinal.ITEM_QUANTIDADE, item.getQuantidade());
        values.put(DatabaseProdutoFinal.ITEM_PRECO, item.getPreco());
        values.put(DatabaseProdutoFinal.ITEM_FOTO, getBytes(item.getFoto()));

        db.update(DatabaseProdutoFinal.TABLE_NAME, values, DatabaseProdutoFinal.ITEM_NOME + "=" + item.getNome(), null);
        db.close();
    }

    public void modify(ItemProdutoFinal item, int quantidade){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + _ID +" FROM "
                + TABLE_NAME + " WHERE " + ITEM_NOME + "= '" + item.getNome()+ "'";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                ContentValues values = new ContentValues();
                values.put(DatabaseMateriaPrima.ITEM_QUANTIDADE, item.getQuantidade() - quantidade);

                db.update(TABLE_NAME, values, "_id= "+ cursor.getInt(0), null);


            } while (cursor.moveToNext());
        }
        cursor.close();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        insert(item.getNome(), dateFormat.format(date), -(quantidade), "retirada");

    }

    public void modifyPreco(ItemProdutoFinal item, double preco){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + _ID +" FROM "
                + TABLE_NAME + " WHERE " + ITEM_NOME + "= '" + item.getNome()+ "'";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                ContentValues values = new ContentValues();
                values.put(DatabaseMateriaPrima.ITEM_PRECO,preco);

                db.update(TABLE_NAME, values, "_id= "+ cursor.getInt(0), null);


            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public List<Registro> getAllRegistros(ItemProdutoFinal item){
        List<Registro> aux = new ArrayList<Registro>();

        String query = "SELECT " + REG_DATA +" , " + REG_SALDO + " FROM "
                + TABLE2_NAME + " WHERE " + REG_PROD_FINAL + "= '" + item.getNome()+ "'";

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

    public void delete (ItemProdutoFinal item)  {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + ITEM_NOME + "= '" + item.getNome() + "'");
        database.close();
    }

    public List<ItemProdutoFinal> getAllItens(){
        List<ItemProdutoFinal> aux = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                ItemProdutoFinal temp = new ItemProdutoFinal(cursor.getString(1),cursor.getString(2), Double.parseDouble(cursor.getString(3)),
                        Double.parseDouble(cursor.getString(4)), getImage(cursor.getBlob(5)),
                        cursor.getString(6), Double.parseDouble(cursor.getString(7)));
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

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getId() {
        return _ID;
    }

    public static String getItemNome() {
        return ITEM_NOME;
    }

    public static String getItemTamanho() {
        return ITEM_TAMANHO;
    }

    public static String getItemQuantidade() {
        return ITEM_QUANTIDADE;
    }

    public static String getItemPreco() {
        return ITEM_PRECO;
    }
}

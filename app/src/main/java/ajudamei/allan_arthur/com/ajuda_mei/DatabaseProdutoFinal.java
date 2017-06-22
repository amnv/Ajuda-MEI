package ajudamei.allan_arthur.com.ajuda_mei;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allan on 09/06/2017.
 */

public class DatabaseProdutoFinal extends SQLiteOpenHelper {

    final static String TABLE_NAME = "produtos_finais";
    final static String _ID = "_id";

    final static String ITEM_NOME = "name";
    final static String ITEM_TAMANHO = "tamanho";
    final static String ITEM_QUANTIDADE = "quantidade";
    final static String ITEM_PRECO = "preço";
    final static String ITEM_DATA = "dataAdicao";
    final static String ITEM_FOTO = "foto";

    final private static String CREATE_CMD =

            "CREATE TABLE "+TABLE_NAME+" (" + _ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ITEM_NOME + " TEXT NOT NULL, "
                    + ITEM_TAMANHO + " TEXT NOT NULL, "
                    + ITEM_QUANTIDADE + " TEXT NOT NULL, "
                    + ITEM_PRECO + " TEXT NOT NULL, "
                    + ITEM_FOTO + " BLOB"
                    + ")";

    final private static String NAME = "produto_db";
    final private static Integer VERSION = 1;
    final private Context mContext;

    public DatabaseProdutoFinal(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, NAME, factory, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CMD);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(ItemProdutoFinal item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseProdutoFinal.ITEM_NOME, item.getNome());
        values.put(DatabaseProdutoFinal.ITEM_TAMANHO, item.getTamanho());
        values.put(DatabaseProdutoFinal.ITEM_QUANTIDADE, item.getQuantidade());
        values.put(DatabaseProdutoFinal.ITEM_PRECO, item.getPreco());
        values.put(DatabaseProdutoFinal.ITEM_FOTO, getBytes(item.getFoto()));

        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();
    }

    public List<ItemProdutoFinal> getAllItens(){
        List<ItemProdutoFinal> aux = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                ItemProdutoFinal temp = new ItemProdutoFinal(cursor.getString(1),cursor.getString(2), Double.parseDouble(cursor.getString(3)),
                        Double.parseDouble(cursor.getString(4)), getImage(cursor.getBlob(5)));
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

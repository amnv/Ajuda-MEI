package ajudamei.allan_arthur.com.ajuda_mei;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

/**
 * Created by Allan on 09/06/2017.
 */

public class DatabaseMateriaPrima extends SQLiteOpenHelper {

    final static String TABLE_NAME = "materia-prima";
    final static String _ID = "_id";

    final static String ITEM_NOME = "name";
    final static String ITEM_TAMANHO = "tamanho";
    final static String ITEM_QUANTIDADE = "quantidade";
    final static String ITEM_PRECO = "preço";
    final static String ITEM_FORMA_AQUISICAO = "formaAquisicao";
//    final static bitmap ITEM_FOTO;

    final private static String CREATE_CMD =

            "CREATE TABLE "+TABLE_NAME+" (" + _ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ITEM_NOME + " TEXT NOT NULL, "
                    + ITEM_TAMANHO + " TEXT NOT NULL, "
                    + ITEM_QUANTIDADE + " TEXT NOT NULL, "
                    + ITEM_PRECO + " TEXT NOT NULL, "
//                    + ITEM_FOTO + " ,"
                    + ITEM_FORMA_AQUISICAO + "TEXT NOT NULL"
                    + ")";

    final private static String NAME = "materia_db";
    final private static Integer VERSION = 1;
    final private Context mContext;

    public DatabaseMateriaPrima(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, NAME, factory, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(ItemMateriaPrima item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseMateriaPrima.ITEM_NOME, item.getNome());
        values.put(DatabaseMateriaPrima.ITEM_TAMANHO, item.getTamanho());
        values.put(DatabaseMateriaPrima.ITEM_QUANTIDADE, item.getQuantidade());
        values.put(DatabaseMateriaPrima.ITEM_PRECO, item.getPreco());
        values.put(DatabaseMateriaPrima.ITEM_FORMA_AQUISICAO, item.getFormaDeAquisicao());
//        values.put(DatabaseMateriaPrima.ITEM_FOTO, item.getFoto());

        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        db.close();
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

    public static String getItemFormaAquisicao() {
        return ITEM_FORMA_AQUISICAO;
    }
}

package ajudamei.allan_arthur.com.ajuda_mei;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Allan on 09/06/2017.
 */

public class DatabaseProdutoFinal extends SQLiteOpenHelper {

    final private static String NAME = "produto_db";
    final private static Integer VERSION = 1;
    final private Context mContext;

    public DatabaseProdutoFinal(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, NAME, factory, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(ItemProdutoFinal item) {

    }

    void deleteDatabase() {
        mContext.deleteDatabase(NAME);
    }
}

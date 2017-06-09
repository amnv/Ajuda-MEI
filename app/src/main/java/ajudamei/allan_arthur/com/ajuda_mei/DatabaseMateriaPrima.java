package ajudamei.allan_arthur.com.ajuda_mei;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Allan on 09/06/2017.
 */

public class DatabaseMateriaPrima extends SQLiteOpenHelper {

    final private static String NAME = "materia_db";
    final private static Integer VERSION = 1;

    public DatabaseMateriaPrima(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, NAME, factory, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

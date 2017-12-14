package ajudamei.allan_arthur.com.ajuda_mei.database.archComponent;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by allyson on 13/12/17.
 */
@Database(entities = {RegistroBoletoRoom.class}, version = 1)
public abstract class RegistroBoletoDatabase extends RoomDatabase {
    public abstract RegistroBoletoDao registroBoletoDao();
}

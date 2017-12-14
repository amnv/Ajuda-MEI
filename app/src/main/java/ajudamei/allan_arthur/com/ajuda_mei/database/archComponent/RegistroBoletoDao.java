package ajudamei.allan_arthur.com.ajuda_mei.database.archComponent;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by allyson on 13/12/17.
 */
@Dao
public interface RegistroBoletoDao {
    @Insert
    void insert(RegistroBoletoRoom... boleto);

    @Delete
    void delete(RegistroBoletoRoom boleto);

    @Query("SELECT * FROM RegistroBoletoRoom")
    List<RegistroBoletoRoom> getAll();
}

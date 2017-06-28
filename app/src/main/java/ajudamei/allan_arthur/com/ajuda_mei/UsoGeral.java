package ajudamei.allan_arthur.com.ajuda_mei;

import android.app.Application;

/**
 * Created by arthur on 26/06/17.
 */

public class UsoGeral extends Application {

    private ItemMateriaPrima item;

    public ItemMateriaPrima getItem() {
        return item;
    }

    public void setItem(ItemMateriaPrima item) {
        this.item = item;
    }
}

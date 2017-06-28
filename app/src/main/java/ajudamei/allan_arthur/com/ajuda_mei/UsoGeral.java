package ajudamei.allan_arthur.com.ajuda_mei;

import android.app.Application;

/**
 * Created by arthur on 26/06/17.
 */

public class UsoGeral extends Application {

    private ItemMateriaPrima item;


    public ItemProdutoFinal getProduto() {
        return produto;
    }

    public UsoGeral setProduto(ItemProdutoFinal produto) {
        this.produto = produto;
        return this;
    }

    private ItemProdutoFinal produto;
    public ItemMateriaPrima getItem() {
        return item;
    }

    public void setItem(ItemMateriaPrima item) {
        this.item = item;
    }
}

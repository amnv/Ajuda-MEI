package ajudamei.allan_arthur.com.ajuda_mei;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import ajudamei.allan_arthur.com.ajuda_mei.domain.item.ItemMateriaPrima;
import ajudamei.allan_arthur.com.ajuda_mei.domain.item.ItemProdutoFinal;

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

    private List<ItemMateriaPrima> itensParaDecrementar = new ArrayList<ItemMateriaPrima>();

    public List<ItemMateriaPrima> getItensParaDecrementar() {
        return itensParaDecrementar;
    }

    public void setItensParaDecrementar(List<ItemMateriaPrima> itensParaDecrementar) {
        this.itensParaDecrementar = itensParaDecrementar;
    }

    private List<Integer> quantidadesParaDecrementar = new ArrayList<Integer>();

    public List<Integer> getQuantidadesParaDecrementar() {
        return quantidadesParaDecrementar;
    }

    public void setQuantidadesParaDecrementar(List<Integer> quantidadesParaDecrementar) {
        this.quantidadesParaDecrementar = quantidadesParaDecrementar;
    }

    private ItemProdutoFinal temp = new ItemProdutoFinal("","",0, 0, null, "", 0);

    public ItemProdutoFinal getTemp() {
        return temp;
    }

    public void setTemp(ItemProdutoFinal temp) {
        this.temp = temp;
    }
}

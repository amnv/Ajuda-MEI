package ajudamei.allan_arthur.com.ajuda_mei.domain.item;

import android.graphics.Bitmap;

/**
 * Created by arthur on 28/05/17.
 */

public class ItemProdutoFinal extends Item{
    private double custoProducao;

    public ItemProdutoFinal(String nome, String tamanho, double quantidade,
                            double preco, Bitmap foto, String data, double custoProducao){
        super(nome, tamanho, quantidade, preco, foto, data);
        this.custoProducao = custoProducao;
    }

    public double getCustoProducao() {
        return custoProducao;
    }

    public void setCustoProducao(double custoProducao) {
        this.custoProducao = custoProducao;
    }


}

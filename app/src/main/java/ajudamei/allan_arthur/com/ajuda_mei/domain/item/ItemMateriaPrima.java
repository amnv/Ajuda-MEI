package ajudamei.allan_arthur.com.ajuda_mei.domain.item;

import android.graphics.Bitmap;

/**
 * Created by arthur on 28/05/17.
 */

public class ItemMateriaPrima extends Item{
    private String formaDeAquisicao;

    public ItemMateriaPrima(String nome, String tamanho, double quantidade, String formaDeAquisicao,
                            double preco, Bitmap foto, String data){
        super(nome, tamanho, quantidade, preco, foto, data);
        this.formaDeAquisicao = formaDeAquisicao;
    }

    public String getFormaDeAquisicao() {
        return formaDeAquisicao;
    }

    public void setFormaDeAquisicao(String formaDeAquisicao) {
        this.formaDeAquisicao = formaDeAquisicao;
    }
}

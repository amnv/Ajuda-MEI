package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.venda_produto;

import ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.Registro;

/**
 * Created by Allan on 22/06/2017.
 */

public class Venda extends Registro {
    public Venda(String nome, double preco, String data) {
        super(nome, preco, data);
        this.setTipo(0);
    }
}

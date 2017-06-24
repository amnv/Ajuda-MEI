package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.entrada_recurso;

import ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.Registro;

/**
 * Created by Allan on 22/06/2017.
 */

public class Entrada extends Registro {

    public Entrada(String nome, double preco, String data) {
        super(nome, preco, data);
        setTipo(0);
    }
}

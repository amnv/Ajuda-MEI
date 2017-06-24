package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.aquisicao_mat_prima;

import ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.Registro;

/**
 * Created by Allan on 22/06/2017.
 */

public class Aquisicao extends Registro {

    public Aquisicao(String nome, double preco, String data) {
        super(nome, preco, data);
        setTipo(1);
    }
}

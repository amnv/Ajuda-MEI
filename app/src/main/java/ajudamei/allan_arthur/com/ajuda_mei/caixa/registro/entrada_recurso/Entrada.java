package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro.entrada_recurso;

import ajudamei.allan_arthur.com.ajuda_mei.domain.Registro;

/**
 * Created by Allan on 22/06/2017.
 */

public class Entrada extends Registro {
    private double preco;
    private String descricao;

    public Entrada(String descricao, double preco, String data, int quantidade, String tipo) {
        super(data, quantidade, tipo);
        this.descricao = descricao;
        this.preco = preco;

        setFlag(0);
    }

    public double getPreco() {
        return preco;
    }

    public Entrada setPreco(double preco) {
        this.preco = preco;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Entrada setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }
}

package ajudamei.allan_arthur.com.ajuda_mei.domain.registro;

/**
 * Created by Allan on 22/06/2017.
 */

public class Aquisicao extends Registro {
    String descricao;
    double preco;

    public Aquisicao(String descricao, double preco, String data, int quantidade, String tipo) {
        super(data, quantidade, tipo);
        this.descricao = descricao;
        this.preco = preco;
        setFlag(1);
    }

    public String getDescricao() {
        return descricao;
    }

    public Aquisicao setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public double getPreco() {
        return preco;
    }

    public Aquisicao setPreco(double preco) {
        this.preco = preco;
        return this;
    }
}

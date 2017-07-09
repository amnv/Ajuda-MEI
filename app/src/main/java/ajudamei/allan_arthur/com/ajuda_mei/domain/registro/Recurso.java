package ajudamei.allan_arthur.com.ajuda_mei.domain.registro;

/**
 * Created by Allan on 22/06/2017.
 */

public class Recurso extends Registro {
    private double preco;
    private String descricao;

    public Recurso(String descricao, double preco, String data, int quantidade, String tipo) {
        super(data, quantidade, tipo);
        this.descricao = descricao;
        this.preco = preco;

        setFlag(0);
    }

    public double getPreco() {
        return preco;
    }

    public Recurso setPreco(double preco) {
        this.preco = preco;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Recurso setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }
}

package ajudamei.allan_arthur.com.ajuda_mei.domain.registro;

/**
 * Created by Allan on 22/06/2017.
 */

public class Boleto extends Registro {
    private String descricao;
    private double preco;

    public Boleto(String descricao, double preco, String data, int quantidade, String tipo) {
        super(data, quantidade, tipo);
        this.descricao = descricao;
        this.preco = preco;
        setFlag(1);
    }

    public String getDescricao() {
        return descricao;
    }

    public Boleto setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public double getPreco() {
        return preco;
    }

    public Boleto setPreco(double preco) {
        this.preco = preco;
        return this;
    }
}

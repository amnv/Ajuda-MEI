package ajudamei.allan_arthur.com.ajuda_mei.caixa.registro;

/**
 * Created by Allan on 22/06/2017.
 */

public class Registro {
    private String nome;
    private String data;
    private double preco;
    private int tipo; //0 entrada, 1 saida

    public Registro(String nome, double preco, String data) {
        this.nome = nome;
        this.data = data;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public Registro setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getData() {
        return data;
    }

    public Registro setData(String data) {
        this.data = data;
        return this;
    }

    public double getPreco() {
        return preco;
    }

    public Registro setPreco(double preco) {
        this.preco = preco;
        return this;
    }

    public int getTipo() {
        return tipo;
    }

    public Registro setTipo(int tipo) {
        this.tipo = tipo;
        return this;
    }
}

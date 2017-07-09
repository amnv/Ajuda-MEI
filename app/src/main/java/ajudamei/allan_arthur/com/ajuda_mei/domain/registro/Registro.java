package ajudamei.allan_arthur.com.ajuda_mei.domain.registro;

/**
 * Created by Allan on 22/06/2017.
 */

public class Registro {
    private int flag;
    private String tipo;
    private int quantidade;
    private String data;

    public Registro(String data, int quantidade, String tipo) {
        this.data = data;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public int getFlag() {
        return flag;
    }

    public Registro setFlag(int flag) {
        this.flag = flag;
        return this;
    }

    public String getData() {
        return data;
    }

    public Registro setData(String data) {
        this.data = data;
        return this;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Registro setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public String getTipo() {
        return tipo;
    }

    public Registro setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }
}

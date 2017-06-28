package ajudamei.allan_arthur.com.ajuda_mei;

/**
 * Created by arthur on 28/06/17.
 */

public class Registro {
    private String data;
    private int quantidade;

    public Registro(String data, int quantidade){
        this.data = data;
        this.quantidade = quantidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

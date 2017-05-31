package ajudamei.allan_arthur.com.ajuda_mei;

import android.graphics.Bitmap;

/**
 * Created by arthur on 28/05/17.
 */

public class ItemMateriaPrima {
    private String nome;
    private String tamanho;
    private double quantidade;
    private String formaDeAquisicao;
    private double preco;
    private Bitmap foto;

    public ItemMateriaPrima(String nome, String tamanho, double quantidade, String formaDeAquisicao,
                            double preco, Bitmap foto){
        this.nome = nome;
        this.tamanho = tamanho;
        this.quantidade = quantidade;
        this.formaDeAquisicao = formaDeAquisicao;
        this.preco = preco;
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getFormaDeAquisicao() {
        return formaDeAquisicao;
    }

    public void setFormaDeAquisicao(String formaDeAquisicao) {
        this.formaDeAquisicao = formaDeAquisicao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }
}

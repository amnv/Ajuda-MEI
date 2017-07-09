package ajudamei.allan_arthur.com.ajuda_mei.domain.item;

import android.graphics.Bitmap;

/**
 * Created by Allan on 31/05/2017.
 */

public class Item {
    private String nome;
    private String tamanho;
    private double quantidade;
    private double preco;
    private Bitmap foto;
    private String data;

    public Item(String nome, String tamanho, double quantidade, double preco, Bitmap foto, String data) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.quantidade = quantidade;
        this.preco = preco;
        this.foto = foto;
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public Item setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getTamanho() {
        return tamanho;
    }

    public Item setTamanho(String tamanho) {
        this.tamanho = tamanho;
        return this;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public Item setQuantidade(double quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public double getPreco() {
        return preco;
    }

    public Item setPreco(double preco) {
        this.preco = preco;
        return this;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public Item setFoto(Bitmap foto) {
        this.foto = foto;
        return this;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

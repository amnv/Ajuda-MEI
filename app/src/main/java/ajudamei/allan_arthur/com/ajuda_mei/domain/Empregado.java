package ajudamei.allan_arthur.com.ajuda_mei.domain;

import android.graphics.Bitmap;

/**
 * Created by Allan on 08/07/2017.
 */

public class Empregado {

    private String nome;
    private String cpf;
    private String telefone;
    private Bitmap foto;

    public Empregado(String nome, String cpf, String telefone, Bitmap foto) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public Empregado setFoto(Bitmap foto) {
        this.foto = foto;
        return this;
    }
}

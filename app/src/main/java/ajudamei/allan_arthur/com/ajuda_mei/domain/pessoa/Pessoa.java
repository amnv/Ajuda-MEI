package ajudamei.allan_arthur.com.ajuda_mei.domain.pessoa;

/**
 * Created by arthur on 03/07/17.
 */

public abstract class Pessoa {
    private String nome;
    private String telefone;
    private String email;

    public Pessoa(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = "";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

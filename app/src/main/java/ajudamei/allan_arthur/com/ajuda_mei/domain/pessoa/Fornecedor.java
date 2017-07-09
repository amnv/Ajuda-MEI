package ajudamei.allan_arthur.com.ajuda_mei.domain.pessoa;

/**
 * Created by arthur on 29/05/17.
 */

public class Fornecedor {

    private String nome;
    private String email;
    private String telefone;

    public Fornecedor(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}

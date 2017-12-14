package ajudamei.allan_arthur.com.ajuda_mei.database.archComponent;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import ajudamei.allan_arthur.com.ajuda_mei.domain.registro.Boleto;

/**
 * Created by allyson on 13/12/17.
 */

@Entity
public class RegistroBoletoRoom {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String descricao;
    private String data;
    private double preco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public static Boleto toBoleto(RegistroBoletoRoom r)
    {
        return new Boleto(r.getDescricao(), r.getPreco(), r.getData(), 9999, "tipo");
    }
}

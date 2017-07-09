package ajudamei.allan_arthur.com.ajuda_mei.domain.pessoa;

import android.graphics.Bitmap;

/**
 * Created by Allan on 08/07/2017.
 */

public class Empregado {

    private String nome;
    private double salario;
    private int periodicidade;

    public Empregado(String nome, double salario, int periodicidade) {
        this.nome = nome;
        this.salario = salario;
        this.periodicidade = periodicidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(int periodicidade) {
        this.periodicidade = periodicidade;
    }
}

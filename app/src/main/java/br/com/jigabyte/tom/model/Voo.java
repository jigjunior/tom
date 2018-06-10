package br.com.jigabyte.tom.model;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class Voo {

    private int id;
    private String origem;
    private String destino;
    private DateTime dataIda;
    private DateTime dataVolta;
    private int lotacao;
    private ArrayList<Usuario> passageiros;

    public Voo() {
    }

    public Voo(int id, String origem, String destino, DateTime dataIda, DateTime dataVolta, int lotacao, ArrayList<Usuario> passageiros) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.dataIda = dataIda;
        this.dataVolta = dataVolta;
        this.lotacao = lotacao;
        this.passageiros = passageiros;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public DateTime getDataIda() {
        return dataIda;
    }

    public void setDataIda(DateTime dataIda) {
        this.dataIda = dataIda;
    }

    public DateTime getDataVolta() {
        return dataVolta;
    }

    public void setDataVolta(DateTime dataVolta) {
        this.dataVolta = dataVolta;
    }

    public int getLotacao() {
        return lotacao;
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    public ArrayList<Usuario> getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(ArrayList<Usuario> passageiros) {
        this.passageiros = passageiros;
    }
}

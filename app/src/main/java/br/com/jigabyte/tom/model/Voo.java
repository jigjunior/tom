package br.com.jigabyte.tom.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.jigabyte.tom.BR;

public class Voo implements Serializable, Observable {

    @Expose
    private long id;
    @Expose
    private Aeroporto origem;
    @Expose
    private Aeroporto destino;
    @Expose
    private String dataVoo;
    @Expose
    private double valorPassagem;
    @Expose
    private Aviao aviao;

    @SerializedName("passagens")
    private List<Poltrona> poltronas;

    private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();


    public Voo() {
    }

    public Voo(long id, Aeroporto origem, Aeroporto destino, String dataVoo, double valorPassagem, Aviao aviao) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.dataVoo = dataVoo;
        this.valorPassagem = valorPassagem;
        this.aviao = aviao;
    }

    @Bindable
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        notifyChange(BR.id);
    }

    @Bindable
    public Aeroporto getOrigem() {
        return origem;
    }

    public void setOrigem(Aeroporto origem) {
        this.origem = origem;
        notifyChange(BR.origem);
    }

    @Bindable
    public Aeroporto getDestino() {
        return destino;
    }

    public void setDestino(Aeroporto destino) {
        this.destino = destino;
        notifyChange(BR.destino);
    }

    @Bindable
    public String getDataVoo() {
        return dataVoo;
    }

    public void setDataVoo(String dataVoo) {
        this.dataVoo = dataVoo;
        notifyChange(BR.dataVoo);
    }

    @Bindable
    public double getValorPassagem() {
        return valorPassagem;
    }

    public void setValorPassagem(double valorPassagem) {
        this.valorPassagem = valorPassagem;
        notifyChange(BR.valorPassagem);
    }

    @Bindable
    public Aviao getAviao() {
        return aviao;
    }

    public void setAviao(Aviao aviao) {
        this.aviao = aviao;
        notifyChange(BR.aviao);
    }

    private synchronized void notifyChange(int propertyId) {
        if (propertyChangeRegistry == null) {
            propertyChangeRegistry = new PropertyChangeRegistry();
        }
        propertyChangeRegistry.notifyChange(this, propertyId);
    }

    @Override
    public synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (propertyChangeRegistry == null) {
            propertyChangeRegistry = new PropertyChangeRegistry();
        }
        propertyChangeRegistry.add(callback);

    }

    @Override
    public synchronized void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (propertyChangeRegistry != null) {
            propertyChangeRegistry.remove(callback);
        }
    }


    /* ----------------------------------- JODATIME PARA TRABALHAR COM DATAS ----------------------------*/
    /* --------------------------------------------------------------------------------------------------*/
    public LocalDateTime getDataCorreta(){
        //"=28/08/2018 02:00:00"
        String dataCorreta = dataVoo.substring(1,19);
        return LocalDateTime.parse(dataCorreta);
    }

    public boolean vooDentroDaData(String dataInicio, String dataFim){
        LocalDateTime dataVoo = getDataCorreta();
        LocalDateTime inicio = LocalDateTime.parse(dataInicio + "00:00:00");
        LocalDateTime fim = LocalDateTime.parse(dataFim + "23:59:59");
        if (inicio.isBefore(dataVoo) && fim.isAfter(dataVoo)) {
            return true;
        }
        return false;
    }
    /* --------------------------------------------------------------------------------------------------*/
    /* --------------------------------------------------------------------------------------------------*/
}

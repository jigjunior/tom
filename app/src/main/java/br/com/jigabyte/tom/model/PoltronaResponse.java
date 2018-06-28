package br.com.jigabyte.tom.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

import br.com.jigabyte.tom.BR;

public class PoltronaResponse implements Observable, Serializable {

    @Expose
    String assento;
    @Expose
    boolean ocupado;
    @Expose
    String origem;
    @Expose
    String destino;
    @Expose
    String dataVoo;
    @Expose
    Double valorPassagem;
    @Expose
    String aviao;
    private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();

    public PoltronaResponse() {
    }

    public PoltronaResponse(String assento, boolean ocupado, String origem, String destino, String dataVoo, Double valorPassagem, String aviao) {
        this.assento = assento;
        this.ocupado = ocupado;
        this.origem = origem;
        this.destino = destino;
        this.dataVoo = dataVoo;
        this.valorPassagem = valorPassagem;
        this.aviao = aviao;
    }

    @Bindable
    public String getAssento() {
        return assento;
    }

    public void setAssento(String assento) {
        this.assento = assento;
        notifyChange(BR.assento);
    }

    @Bindable
    public boolean getOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
        notifyChange(BR.ocupado);
    }

    @Bindable
    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
        notifyChange(BR.origem);
    }

    @Bindable
    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
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
    public Double getValorPassagem() {
        return valorPassagem;
    }

    public void setValorPassagem(Double valorPassagem) {
        this.valorPassagem = valorPassagem;
        notifyChange(BR.valorPassagem);
    }

    @Bindable
    public String getAviao() {
        return aviao;
    }

    public void setAviao(String aviao) {
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

    @Override
    public String toString() {
        return getAssento();
    }
}

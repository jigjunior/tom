package br.com.jigabyte.tom.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

import br.com.jigabyte.tom.BR;

public class Aviao implements Observable, Serializable {

    @Expose
    private int id;
    @Expose
    private String prefixo;
    @Expose
    private String modelo;
    @Expose
    private int capacidade;
    private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();

    public Aviao() {
    }

    public Aviao(int id, String prefixo, String modelo, int capacidade) {
        this.id = id;
        this.prefixo = prefixo;
        this.modelo = modelo;
        this.capacidade = capacidade;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyChange(BR.id);
    }

    @Bindable
    public String getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
        notifyChange(BR.prefixo);
    }

    @Bindable
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
        notifyChange(BR.modelo);
    }

    @Bindable
    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
        notifyChange(BR.capacidade);
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
        return "Aviao{" +
                "id=" + id +
                ", prefixo='" + prefixo + '\'' +
                ", modelo='" + modelo + '\'' +
                ", capacidade=" + capacidade +
                ", propertyChangeRegistry=" + propertyChangeRegistry +
                '}';
    }
}

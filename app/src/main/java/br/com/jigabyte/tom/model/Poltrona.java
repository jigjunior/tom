package br.com.jigabyte.tom.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.google.gson.annotations.Expose;

import br.com.jigabyte.tom.BR;

public class Poltrona implements Observable {

    @Expose
    private int id;
    @Expose
    private String assento;
    @Expose
    private boolean ocupado;
    @Expose
    private Usuario usuario;

    private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();

    public Poltrona() {
    }

    public Poltrona(int id, String assento, boolean ocupado, Usuario usuario) {
        this.id = id;
        this.assento = assento;
        this.ocupado = ocupado;
        this.usuario = usuario;
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
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        notifyChange(BR.usuario);
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
        return assento;
    }
}

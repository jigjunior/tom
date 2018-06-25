package br.com.jigabyte.tom.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

import br.com.jigabyte.tom.BR;

public class Usuario implements Serializable, Observable {
/*
    "id":0,
    "login":"string",
    "senha":"string"
    "email":"string",
    "nome":"string",
    "token":"ieYUsnb6cH1pRIf+uJDh4XeLSJn7fpKImxRCQb0JXaCKW199NN2LeX4805w4g73h"


    @Expose
    String myString;  // will be serialized as myString

    @SerializedName("m_s")
    String myString; // will be serialized as m_s
 */

    @Expose
    private int id;
    @Expose
    private String login;
    @Expose
    private String senha;
    @Expose
    private String nome;
    @Expose
    private String email;
    @Expose
    private String token;

    private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();

    public Usuario() {
    }

    public Usuario(int id, String login, String senha, String nome, String email, String token) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.email = email;
        this.token = token;
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
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        notifyChange(BR.login);
    }

    @Bindable
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
        notifyChange(BR.senha);
    }

    @Bindable
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        notifyChange(BR.nome);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyChange(BR.email);
    }

    @Bindable
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        notifyChange(BR.token);
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
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}

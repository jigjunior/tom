package br.com.jigabyte.tom.model;

import com.google.gson.annotations.Expose;

public class UsuarioLogin {

    @Expose
    private String login;

    @Expose
    private String senha;

    public UsuarioLogin() {
    }

    public UsuarioLogin(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

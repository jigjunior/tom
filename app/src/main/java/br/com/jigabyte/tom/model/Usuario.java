package br.com.jigabyte.tom.model;

public class Usuario {

    private int id; // 1
    private String nome; // "Zezin
    private String email; // "ze@ze"
    private String login; // "ze"
    private String senha; // "123"
    private boolean ativo; // true
    private String cadastro; // "=04/06/2017 12:00:00"
    private String tipo; // "CLIENTE"

    public Usuario() {
    }

    public Usuario(int id, String nome, String email, String login, String senha, boolean ativo, String cadastro, String tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
        this.cadastro = cadastro;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getCadastro() {
        return cadastro;
    }

    public void setCadastro(String cadastro) {
        this.cadastro = cadastro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}

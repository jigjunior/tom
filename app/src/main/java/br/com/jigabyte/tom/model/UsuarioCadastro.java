package br.com.jigabyte.tom.model;

public class UsuarioCadastro {

    public int id;
    public String email;
    public String login;
    public String senha;
    public String nome;


    public UsuarioCadastro(int id, String nome, String email, String login, String senha) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
    }


}

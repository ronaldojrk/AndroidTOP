package com.example.movielist.Models;

public class Filmefavoritos {
    int id ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String nome;
    private String idfilme;
    private String idusuario;
    private Boolean sucesso;

    public Filmefavoritos() {

    }

    public Boolean getSucesso() {
        return sucesso;
    }

    public void setSucesso(Boolean sucesso) {
        this.sucesso = sucesso;
    }

    public Filmefavoritos(int id, String nome, String idfilme, String idusuario) {
        this.id=id;
        this.nome = nome;
        this.idfilme = idfilme;
        this.idusuario = idusuario;
    }

    public Filmefavoritos(String nome, String idfilme, String idusuario) {
        this.nome = nome;
        this.idfilme = idfilme;
        this.idusuario = idusuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdfilme() {
        return idfilme;
    }

    public void setIdfilme(String idfilme) {
        this.idfilme = idfilme;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }
}

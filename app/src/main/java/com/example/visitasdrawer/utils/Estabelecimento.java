package com.example.visitasdrawer.utils;

public class Estabelecimento {

    private int id, numero, cnpj;
    private String razao, cep,cidade;

    public Estabelecimento() {
    }

    public Estabelecimento(int id, int numero, int cnpj, String razao, String cep, String cidade) {
        this.id = id;
        this.numero = numero;
        this.cnpj = cnpj;
        this.razao = razao;
        this.cep = cep;
        this.cidade = cidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}

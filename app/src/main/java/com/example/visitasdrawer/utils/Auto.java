package com.example.visitasdrawer.utils;

public class Auto {

   int id;
   String data, tipoAuto, artigo, recura_receber, obs, equipe, estabelecimento;

    public Auto() {
    }

    public Auto(int id, String data, String tipoAuto, String artigo, String recura_receber, String obs, String equipe, String estabelecimento) {
        this.id = id;
        this.data = data;
        this.tipoAuto = tipoAuto;
        this.artigo = artigo;
        this.recura_receber = recura_receber;
        this.obs = obs;
        this.equipe = equipe;
        this.estabelecimento = estabelecimento;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipoAuto() {
        return tipoAuto;
    }

    public void setTipoAuto(String tipoAuto) {
        this.tipoAuto = tipoAuto;
    }

    public String getArtigo() {
        return artigo;
    }

    public void setArtigo(String artigo) {
        this.artigo = artigo;
    }

    public String getRecura_receber() {
        return recura_receber;
    }

    public void setRecura_receber(String recura_receber) {
        this.recura_receber = recura_receber;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }
}

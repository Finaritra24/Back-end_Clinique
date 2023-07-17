package com.projetfy.clinique.model;

import com.projetfy.clinique.genericDAO.DBTable;

public class Genre extends DBTable {
    private String idGenre;
    private String nom;

    public Genre() {}

    public Genre(String nom) {
        this.nom = nom;
    }

    public String getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(String idGenre) {
        this.idGenre = idGenre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNameAuto() {
        return "CTG";
    }

    public String getSeqName() {
        return "sgenre";
    }

    public String toString() {
        return "Genre{" +
                "idGenre='" + idGenre + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }
}

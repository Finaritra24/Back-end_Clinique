package com.projetfy.clinique.model;

import com.projetfy.clinique.genericDAO.DBTable;

public class Depense extends DBTable {
    private String idDepense;
    private String idUser;
    private String idCategorieDepense;
    private String nom;
    private double prix;
    private String dateDepense;

    public Depense() {}

    public Depense(String idUser, String idCategorieDepense, String nom, double prix, String dateDepense) {
        this.idUser = idUser;
        this.idCategorieDepense = idCategorieDepense;
        this.nom = nom;
        this.prix = prix;
        this.dateDepense = dateDepense;
    }

    public String getIdDepense() {
        return idDepense;
    }

    public void setIdDepense(String idDepense) {
        this.idDepense = idDepense;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdCategorieDepense() {
        return idCategorieDepense;
    }

    public void setIdCategorieDepense(String idCategorieDepense) {
        this.idCategorieDepense = idCategorieDepense;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getStringDepense() {
        return dateDepense;
    }

    public void setStringDepense(String dateDepense) {
        this.dateDepense = dateDepense;
    }

    public String getNameAuto() {
        return "DEP";
    }

    public String getSeqName() {
        return "sdepense";
    }

    public String toString() {
        return "Depense{" +
                "idDepense='" + idDepense + '\'' +
                ", idUser='" + idUser + '\'' +
                ", idCategorieDepense='" + idCategorieDepense + '\'' +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", dateDepense=" + dateDepense +
                '}';
    }
}

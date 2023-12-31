package com.projetfy.clinique.model;

import com.projetfy.clinique.genericDAO.DBTable;
import com.projetfy.clinique.genericDAO.AnnotInsert;

public class Depense extends DBTable {
    private String idDepense;
    @AnnotInsert
    private String idUtilisateur;
    private String idCategorieDepense;
    private String nom;
    private double prix;
    private String dateDepense;

    public Depense() {}

    public Depense(String idUtilisateur, String idCategorieDepense, String nom, double prix, String dateDepense) {
        this.idUtilisateur = idUtilisateur;
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

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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

    public String getDateDepense() {
        return dateDepense;
    }

    public void setDateDepense(String dateDepense) {
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
                ", idUtilisateur='" + idUtilisateur + '\'' +
                ", idCategorieDepense='" + idCategorieDepense + '\'' +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", dateDepense=" + dateDepense +
                '}';
    }
}

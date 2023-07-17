package com.projetfy.clinique.model;

import com.projetfy.clinique.genericDAO.DBTable;

public class AchatPatient extends DBTable {
    private String idAchatPatient;
    private String idPatient;
    private String idCategorieRecette;
    private String nom;
    private double prix;
    private String dateRecette;

    public AchatPatient() {}

    public AchatPatient(String idPatient, String idCategorieRecette, String nom, double prix, String dateRecette) {
        this.idPatient = idPatient;
        this.idCategorieRecette = idCategorieRecette;
        this.nom = nom;
        this.prix = prix;
        this.dateRecette = dateRecette;
    }

    public String getIdAchatPatient() {
        return idAchatPatient;
    }

    public void setIdAchatPatient(String idAchatPatient) {
        this.idAchatPatient = idAchatPatient;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public String getIdCategorieRecette() {
        return idCategorieRecette;
    }

    public void setIdCategorieRecette(String idCategorieRecette) {
        this.idCategorieRecette = idCategorieRecette;
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

    public String getStringRecette() {
        return dateRecette;
    }

    public void setStringRecette(String dateRecette) {
        this.dateRecette = dateRecette;
    }

    public String getNameAuto() {
        return "ACP";
    }

    public String getSeqName() {
        return "sachatpatient";
    }

    public String toString() {
        return "AchatPatient{" +
                "idAchatPatient='" + idAchatPatient + '\'' +
                ", idPatient='" + idPatient + '\'' +
                ", idCategorieRecette='" + idCategorieRecette + '\'' +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", dateRecette=" + dateRecette +
                '}';
    }
}

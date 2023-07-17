package com.projetfy.clinique.model;

import com.projetfy.clinique.genericDAO.DBTable;

public class CategorieDepense extends DBTable {
    private String idCategorieDepense;
    private String nom;
    private String img;

    public CategorieDepense() {}

    public CategorieDepense(String nom, String img) {
        this.nom = nom;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNameAuto() {
        return "CTR";
    }

    public String getSeqName() {
        return "scategorierecette";
    }

    public String toString() {
        return "CategorieDepense{" +
                "idCategorieDepense='" + idCategorieDepense + '\'' +
                ", nom='" + nom + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}

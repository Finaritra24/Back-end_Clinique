package com.projetfy.clinique.model;

import com.projetfy.clinique.genericDAO.DBTable;

public class CategorieRecette extends DBTable {
    private String idCategorieRecette;
    private String nom;
    private String img;
    private double budget;
    private String code;

    public double getBudget() {
        return this.budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CategorieRecette() {}

    public CategorieRecette(String nom, String img,double budget,String code) {
        this.nom = nom;
        this.img = img;
        this.budget=budget;
        this.code=code;
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
        return "CategorieRecette{" +
                "idCategorieRecette='" + idCategorieRecette + '\'' +
                ", nom='" + nom + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}

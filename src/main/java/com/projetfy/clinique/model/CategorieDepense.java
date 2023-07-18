package com.projetfy.clinique.model;

import com.projetfy.clinique.genericDAO.DBTable;

public class CategorieDepense extends DBTable {
    private String idCategorieDepense;
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


    public CategorieDepense() {}

    public CategorieDepense(String nom, String img,double budget,String code) {
        this.nom = nom;
        this.img = img;
        this.budget=budget;
        this.code=code;
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

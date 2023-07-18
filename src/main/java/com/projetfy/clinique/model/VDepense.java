package com.projetfy.clinique.model;

import com.projetfy.clinique.genericDAO.DBTable;

public class VDepense extends DBTable {
    private String idDepense;
    private String idUtilisateur;
    private String idCategorieDepense;
    private String nom;
    private double prix;
    private String dateDepense;
    private String nomUtilisateur;
    private String nomCateg;

	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getNomCateg() {
		return this.nomCateg;
	}

	public void setNomCateg(String nomCateg) {
		this.nomCateg = nomCateg;
	}


    public VDepense() {}

    public VDepense(String idUtilisateur, String idCategorieDepense, String nom, double prix, String dateDepense) {
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
        return "ACP";
    }

    public String getSeqName() {
        return "sachatpatient";
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

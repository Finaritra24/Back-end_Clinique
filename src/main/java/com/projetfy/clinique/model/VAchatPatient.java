package com.projetfy.clinique.model;

import com.projetfy.clinique.genericDAO.DBTable;

public class VAchatPatient extends DBTable {
    private String idAchatPatient;
    private String idPatient;
    private String idCategorieRecette;
    private String nom;
    private double prix;
    private String dateRecette;
    private String nomPatient;
    private String nomCateg;

	public String getNomPatient() {
		return this.nomPatient;
	}

	public void setNomPatient(String nomPatient) {
		this.nomPatient = nomPatient;
	}

	public String getNomCateg() {
		return this.nomCateg;
	}

	public void setNomCateg(String nomCateg) {
		this.nomCateg = nomCateg;
	}


    public VAchatPatient() {}

    public VAchatPatient(String idPatient, String idCategorieRecette, String nom, double prix, String dateRecette) {
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

    public String getDateRecette() {
        return dateRecette;
    }

    public void setDateRecette(String dateRecette) {
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

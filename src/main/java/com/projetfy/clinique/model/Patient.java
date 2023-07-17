package com.projetfy.clinique.model;

import com.projetfy.clinique.genericDAO.DBTable;

public class Patient extends DBTable {
    private String idPatient;
    private String nom;
    private String prenom;
    private String idGenre;
    private String dateNaissance;
    private String img;
    private int remboursement;

    public int getRemboursement() {
        return this.remboursement;
    }

    public void setRemboursement(int remboursement) {
        this.remboursement = remboursement;
    }

    public Patient() {}

    public Patient(String nom, String prenom, String idGenre, String dateNaissance, String img,int remboursement) {
        this.nom = nom;
        this.prenom = prenom;
        this.idGenre = idGenre;
        this.dateNaissance = dateNaissance;
        this.img = img;
        this.remboursement=remboursement;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(String idGenre) {
        this.idGenre = idGenre;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNameAuto() {
        return "PTT";
    }

    public String getSeqName() {
        return "spatient";
    }

    public String toString() {
        return "Patient{" +
                "idPatient='" + idPatient + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", idGenre='" + idGenre + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", img='" + img + '\'' +
                '}';
    }
}

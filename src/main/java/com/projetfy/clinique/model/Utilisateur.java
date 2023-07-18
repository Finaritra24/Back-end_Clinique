package com.projetfy.clinique.model;

import com.projetfy.clinique.genericDAO.DBTable;

public class Utilisateur extends DBTable {
    private String idUtilisateur;
    private String identification;
    private String passwrd;

    public Utilisateur() {}

    public Utilisateur(String identification, String passwrd) {
        this.identification = identification;
        this.passwrd = passwrd;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getPasswrd() {
        return passwrd;
    }

    public void setPasswrd(String passwrd) {
        this.passwrd = passwrd;
    }

    public String getNameAuto() {
        return "USR";
    }

    public String getSeqName() {
        return "suser";
    }

    public String toString() {
        return "Utilisateur{" +
                "idUtilisateur='" + idUtilisateur + '\'' +
                ", identification='" + identification + '\'' +
                ", passwrd='" + passwrd + '\'' +
                '}';
    }
}

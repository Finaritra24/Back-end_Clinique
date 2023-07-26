package com.projetfy.clinique.model;

import java.util.List;

public class ObjetDepense {
    private String jour;
    private String annee;
    private List<String> mois;
    private String categorie;
    private String nom;
    private String prix;
    public ObjetDepense(String jour,String annee,List<String> mois,String categorie,String nom,String prix){
        this.jour=jour;
        this.annee=annee;
        this.mois=mois;
        this.categorie=categorie;
        this.nom=nom;
        this.prix=prix;
    }
    public String getCategorie() {
        return this.categorie;
    }

    public void setCategorie(String categorie ) {
        this.categorie = categorie;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrix() {
        return this.prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
  
    // Ajoutez des getters et setters pour les propriétés...
  

    public String getJour() {
        return this.jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getAnnee() {
        return this.annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public List<String> getMois() {
      return mois;
    }
  
    public void setMois(List<String> mois) {
      this.mois = mois;
    }
  }
  
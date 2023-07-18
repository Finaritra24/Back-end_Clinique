package com.projetfy.clinique.model;
import com.projetfy.clinique.genericDAO.DBTable;

public class Budget extends DBTable {
    private int annee;
    private int mois;
    private double totalachats;
    private double totaldepenses;
    private double differences;

    public Budget(){}
    public Budget(int annee,int mois,double totalachats,double totaldepenses,double differences){
        this.annee=annee;
        this.mois=mois;
        this.totalachats=totalachats;
        this.totaldepenses=totaldepenses;
        this.differences=differences;
    }
    public int getAnnee() {
        return this.annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getMois() {
        return this.mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public double getTotalachats() {
        return this.totalachats;
    }

    public void setTotalachats(double totalachats) {
        this.totalachats = totalachats;
    }

    public double getTotaldepenses() {
        return this.totaldepenses;
    }

    public void setTotaldepenses(double totaldepenses) {
        this.totaldepenses = totaldepenses;
    }

    public double getDifferences() {
        return this.differences;
    }

    public void setDifferences(double differences) {
        this.differences = differences;
    }

    public String getNameAuto() {
        return "ADM";
    }

    public String getSeqName() {
        return "sadmin";
    }

    public String toString() {
    return "annee: " + annee +
            ", mois: " + mois +
            ", totalachats: " + totalachats +
            ", totaldepenses: " + totaldepenses +
            ", differences: " + differences;
}

}

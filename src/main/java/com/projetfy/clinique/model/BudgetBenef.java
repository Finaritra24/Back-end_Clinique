package com.projetfy.clinique.model;

public class BudgetBenef {
    private String typeacte;
    private double reel;
    private double budget;
    private double realisation;

    public BudgetBenef(){}
    public BudgetBenef(String typeacte,double reel,double budget,double realisation){
        this.typeacte=typeacte;
        this.reel=reel;
        this.budget=budget;
        this.realisation=realisation;
    }
    public String getTypeacte() {
        return this.typeacte;
    }

    public void setTypeacte(String typeacte) {
        this.typeacte = typeacte;
    }

    public double getReel() {
        return this.reel;
    }

    public void setReel(double reel) {
        this.reel = reel;
    }

    public double getBudget() {
        return this.budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public double getRealisation() {
        return this.realisation;
    }

    public void setRealisation(double realisation) {
        this.realisation = realisation;
    }
    @Override
    public String toString() {
        return "typeacte: " + typeacte +
            ", reel: " + reel +
            ", budget: " + budget +
            ", realisation: " + realisation;
    }

}

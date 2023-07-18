package com.projetfy.clinique.model;

public class ClassCsv {
    private String date;
    private String code;
    private double budget;
    public ClassCsv(){}
    public ClassCsv(String date,String code,double budget){
        this.date=date;
        this.code=code;
        this.budget=budget;
    }
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getBudget() {
        return this.budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

}

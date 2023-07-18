package com.projetfy.clinique.service;

import java.sql.Connection;
import java.util.Vector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.projetfy.clinique.genericDAO.Connex;

import com.projetfy.clinique.model.BudgetBenef;

public class ServBudgetBenef {
    public static Vector<BudgetBenef> listRecette(int mois,int annee){
        Vector<BudgetBenef> list=new Vector<>();
        String sql="SELECT cr.nom,COALESCE(cr.budget / 12, 0) AS budget_mensuel,COALESCE(SUM(ap.prix), 0) AS total_achats,(COALESCE(SUM(ap.prix), 0) / COALESCE(cr.budget / 12, 0)) * 100 AS realisation FROM categorierecette cr LEFT JOIN (SELECT idCategorierecette,prix,EXTRACT(MONTH FROM daterecette) AS mois,EXTRACT(YEAR FROM daterecette) AS annee FROM achatpatient WHERE EXTRACT(MONTH FROM daterecette) = "+mois+" AND EXTRACT(YEAR FROM daterecette) = "+annee+") ap ON cr.idCategorierecette = ap.idCategorierecette GROUP BY cr.idCategorierecette,cr.nom,cr.budget ORDER BY cr.idCategorierecette";
        try {
            // select sum(prix) from achatpatient where EXTRACT(YEAR FROM daterecette)=2023 and (MONTH FROM daterecette)=2
            Connection con = new Connex().getConnection();
           
             PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String typeacte = rs.getString(1);
                double reel=rs.getDouble(3);
                double budget=rs.getDouble(2);
                double realisation=rs.getDouble(4);
                BudgetBenef b=new BudgetBenef(typeacte,reel,budget,realisation);
                list.add(b);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    public static Vector<BudgetBenef> listDepense(int mois,int annee){
        Vector<BudgetBenef> list=new Vector<>();
        String sql="SELECT cr.nom,COALESCE(cr.budget / 12, 0) AS budget_mensuel,COALESCE(SUM(ap.prix), 0) AS total_depense,(COALESCE(SUM(ap.prix), 0) / COALESCE(cr.budget / 12, 0)) * 100 AS realisation FROM categoriedepense cr LEFT JOIN (SELECT idCategoriedepense,prix,EXTRACT(MONTH FROM datedepense) AS mois,EXTRACT(YEAR FROM datedepense) AS annee FROM depense WHERE EXTRACT(MONTH FROM datedepense) = "+mois+" AND EXTRACT(YEAR FROM datedepense) = "+annee+") ap ON cr.idCategoriedepense = ap.idCategoriedepense GROUP BY cr.idCategoriedepense,cr.nom,cr.budget ORDER BY cr.idCategoriedepense";
        try {
            // select sum(prix) from achatpatient where EXTRACT(YEAR FROM daterecette)=2023 and (MONTH FROM daterecette)=2
            Connection con = new Connex().getConnection();
           
             PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String typeacte = rs.getString(1);
                double reel=rs.getDouble(3);
                double budget=rs.getDouble(2);
                double realisation=rs.getDouble(4);
                BudgetBenef b=new BudgetBenef(typeacte,reel,budget,realisation);
                list.add(b);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String[]args) {
        Vector<BudgetBenef> list=listRecette(1, 2023);
        for(BudgetBenef bf:list){
            System.out.println(bf);
        }
    }
}

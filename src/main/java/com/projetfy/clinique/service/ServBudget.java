package com.projetfy.clinique.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.projetfy.clinique.genericDAO.Connex;
import com.projetfy.clinique.model.Budget;

public class ServBudget {
    public static Vector<Budget> list(int annee){
        String sql="SELECT EXTRACT(YEAR FROM dates.month_year) AS annee, EXTRACT(MONTH FROM dates.month_year) AS mois, COALESCE(ap.total_achats, 0) AS total_achats, COALESCE(d.total_depenses, 0) AS total_depenses,  COALESCE(ap.total_achats, 0) - COALESCE(d.total_depenses, 0) AS difference FROM (SELECT generate_series(DATE '"+annee+"-01-01', DATE '"+annee+"-12-31', INTERVAL '1 month') AS month_year) AS dates LEFT JOIN ( SELECT EXTRACT(MONTH FROM ap.daterecette) AS mois, EXTRACT(YEAR FROM ap.daterecette) AS annee, SUM(ap.prix) AS total_achats FROM achatpatient ap GROUP BY EXTRACT(MONTH FROM ap.daterecette), EXTRACT(YEAR FROM ap.daterecette)) AS ap ON EXTRACT(MONTH FROM dates.month_year) = ap.mois AND EXTRACT(YEAR FROM dates.month_year) = ap.annee LEFT JOIN (SELECT EXTRACT(MONTH FROM d.datedepense) AS mois, EXTRACT(YEAR FROM d.datedepense) AS annee, SUM(d.prix) AS total_depenses FROM depense d GROUP BY EXTRACT(MONTH FROM d.datedepense), EXTRACT(YEAR FROM d.datedepense) ) AS d ON EXTRACT(MONTH FROM dates.month_year) = d.mois AND EXTRACT(YEAR FROM dates.month_year) = d.annee ORDER BY EXTRACT(YEAR FROM dates.month_year), EXTRACT(MONTH FROM dates.month_year) ";
        
        Vector<Budget> list=new Vector<>();
        try {
            // select sum(prix) from achatpatient where EXTRACT(YEAR FROM daterecette)=2023 and (MONTH FROM daterecette)=2
            Connection con = new Connex().getConnection();
           
             PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int years = rs.getInt(1);
                int mois = rs.getInt(2);
                double totalachats=rs.getDouble(3);
                double totaldepenses=rs.getDouble(4);
                double differences=rs.getDouble(5);
                Budget b=new Budget(years,mois,totalachats,totaldepenses,differences);
                list.add(b);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    public static Vector<Budget> listtop(int annee,int nombre){
        String sql="SELECT EXTRACT(YEAR FROM dates.month_year) AS annee, EXTRACT(MONTH FROM dates.month_year) AS mois, COALESCE(ap.total_achats, 0) AS total_achats, COALESCE(d.total_depenses, 0) AS total_depenses,  COALESCE(ap.total_achats, 0) - COALESCE(d.total_depenses, 0) AS difference FROM (SELECT generate_series(DATE '"+annee+"-01-01', DATE '"+annee+"-12-31', INTERVAL '1 month') AS month_year) AS dates  LEFT JOIN ( SELECT EXTRACT(MONTH FROM ap.daterecette) AS mois, EXTRACT(YEAR FROM ap.daterecette) AS annee, SUM(ap.prix) AS total_achats FROM achatpatient ap GROUP BY EXTRACT(MONTH FROM ap.daterecette), EXTRACT(YEAR FROM ap.daterecette)) AS ap ON EXTRACT(MONTH FROM dates.month_year) = ap.mois AND EXTRACT(YEAR FROM dates.month_year) = ap.annee LEFT JOIN (SELECT EXTRACT(MONTH FROM d.datedepense) AS mois, EXTRACT(YEAR FROM d.datedepense) AS annee, SUM(d.prix) AS total_depenses FROM depense d GROUP BY EXTRACT(MONTH FROM d.datedepense), EXTRACT(YEAR FROM d.datedepense) ) AS d ON EXTRACT(MONTH FROM dates.month_year) = d.mois AND EXTRACT(YEAR FROM dates.month_year) = d.annee ORDER BY difference desc limit "+nombre;
        
        Vector<Budget> list=new Vector<>();
        try {
            // select sum(prix) from achatpatient where EXTRACT(YEAR FROM daterecette)=2023 and (MONTH FROM daterecette)=2
            Connection con = new Connex().getConnection();
           
             PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int years = rs.getInt(1);
                int mois = rs.getInt(2);
                double totalachats=rs.getDouble(3);
                double totaldepenses=rs.getDouble(4);
                double differences=rs.getDouble(5);
                Budget b=new Budget(years,mois,totalachats,totaldepenses,differences);
                list.add(b);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
   
       
    public static void main(String[] args){
        // Vector<Budget> list=listtop(2023);
        // for(Budget b:list){
        //     System.out.println(b);
        // }
    }
}

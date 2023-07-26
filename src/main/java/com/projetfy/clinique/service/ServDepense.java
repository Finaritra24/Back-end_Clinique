package com.projetfy.clinique.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import org.springframework.cglib.core.Local;

import com.projetfy.clinique.genericDAO.Connex;
import com.projetfy.clinique.model.ClassCsv;
import com.projetfy.clinique.model.Depense;
import com.projetfy.clinique.model.ObjetDepense;

public class ServDepense {
    public static boolean isValidDate(int annee, int mois, int jour) {
        if (annee < 1 || mois < 1 || mois > 12 || jour < 1 || jour > 31) {
            return false; // Les valeurs ne correspondent pas à une date valide
        }

        // Nombre de jours maximum dans chaque mois
        int[] joursMax = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        // Gestion des années bissextiles (février a 29 jours)
        if (annee % 4 == 0 && (annee % 100 != 0 || annee % 400 == 0)) {
            joursMax[2] = 29;
        }

        // Vérification du nombre de jours pour le mois donné
        if (jour > joursMax[mois]) {
            return false; // Le jour dépasse le nombre de jours pour le mois donné
        }

        return true; // Les valeurs correspondent à une date valide
    }

    public static ArrayList<String> ajoutdepense(String id,ObjetDepense o){
        ArrayList<String> list=new ArrayList<>();
        int test=0;
            for(String m:o.getMois()){
                int annee=Integer.parseInt(o.getAnnee());
                int jour=Integer.parseInt(o.getJour());
                int mois=Integer.parseInt(m);
                if(!isValidDate(annee, mois, jour)){
                    String m2=""+mois;
                    String j2=""+jour;
                    if(mois<10){
                        m2="0"+mois;
                    }
                    if(jour<10){
                        j2="0"+jour;
                    }
                    list.add(""+annee+"-"+m2+"-"+j2);
                    test++;
                }
                
            }
            return list;
    }
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            if(Double.parseDouble(str)<0){
                return false;
            }
            return true; // La chaîne est de type double
        } catch (NumberFormatException e) {
            return false; // La chaîne n'est pas de type double
        }
    }
    public static void ajoutdepensevrai(String id,ObjetDepense o){
        for(String m:o.getMois()){
            String j=o.getJour();
        if(Integer.parseInt(j)<10){
            j="0"+j;
        }
        try {
            String date=o.getAnnee()+"-"+m+"-"+j;
            Double d=Double.parseDouble(o.getPrix());
            Depense depense=new Depense(id,myDepense(o.getCategorie()) ,o.getNom(),d,date);
            depense.create("DEP", null);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        }
    }
    public static void ajoutdepensecsv(ClassCsv o){
            try {
                createCsv(null,o.getCode(),o.getBudget(),o.getDate());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
    public static void createCsv(Connection con,String code,double prix,String date) throws Exception {
        boolean fermeo = false;
        if (con == null) {
            con = Connex.getConnection();
            fermeo = true;
        }
        PreparedStatement stmt = null;
        try {
            String requete = "insert into depense (idutilisateur,idcategoriedepense,nom,prix,datedepense) values ('USR1',(select idcategoriedepense from categoriedepense where code='"+code+"'),'Clinique',"+prix+",'"+date+"')";
            System.out.println(requete);
            stmt = con.prepareStatement(requete);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (fermeo) {
                con.close();
            }
        }
    }
    public static int isDepense(String code){
        String sql="select*from categoriedepense where code='"+code+"'";
        int test=0;
        try {
            // select sum(prix) from achatpatient where EXTRACT(YEAR FROM daterecette)=2023 and (MONTH FROM daterecette)=2
            Connection con = new Connex().getConnection();
            
             PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                test++;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return test;
    }
    public static String myDepense(String code){
        String sql="select*from categoriedepense where code="+code;
        String test="";
        try {
            // select sum(prix) from achatpatient where EXTRACT(YEAR FROM daterecette)=2023 and (MONTH FROM daterecette)=2
            Connection con = new Connex().getConnection();
            
             PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            test=rs.getObject(0).toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return test;
    }

}

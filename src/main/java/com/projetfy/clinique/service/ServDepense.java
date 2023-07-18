package com.projetfy.clinique.service;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.projetfy.clinique.genericDAO.Connex;
import com.projetfy.clinique.model.ClassCsv;
import com.projetfy.clinique.model.Depense;
import com.projetfy.clinique.model.ObjetDepense;

public class ServDepense {
    public static void ajoutdepense(String id,ObjetDepense o){
        for(String mois:o.getMois()){
            String date=o.getAnnee()+"-"+mois+"-"+o.getJour();
            Depense depense=new Depense(id,o.getCategorie(),o.getNom(),o.getPrix(),date);
            try {
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

}

package com.projetfy.clinique.service;

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
}

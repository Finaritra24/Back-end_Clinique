package com.projetfy.clinique.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.projetfy.clinique.genericDAO.Connex;
import com.projetfy.clinique.model.AchatPatient;

public class ServRecette {
    public static int testIfRemboursable(String idpatient){
            String sql="select*from patient where idpatient='"+idpatient+"' and remboursement=1";
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
    public static void updateRecette(String idpatient,Double prix,String date) throws Exception {
        int test=testIfRemboursable(idpatient);
        if(test!=0){
                AchatPatient a=new AchatPatient(idpatient, "CTR10", "rembourse", prix, "20-12-2023");
                a.create("ACP", null);
        }
        
    }
}

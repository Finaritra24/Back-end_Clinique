package com.projetfy.clinique.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.type.TrueFalseConverter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projetfy.clinique.genericDAO.GenerController;
import com.projetfy.clinique.model.ClassCsv;
import com.projetfy.clinique.model.ObjetDepense;
import com.projetfy.clinique.service.ServAdmin;
import com.projetfy.clinique.service.ServDepense;
import com.projetfy.clinique.service.ServRecette;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class ControllDepense {
    //admin
    @PostMapping("/ajoutdepense")
    public ArrayList<String> ajoutdepense(@RequestBody ObjetDepense data, HttpServletResponse response,HttpServletRequest request) throws Exception{
        String jour = data.getJour();
        String annee=data.getAnnee();
        List<String>mois=data.getMois();
        String categorie=data.getCategorie();
        String nom=data.getNom();
        String prix=data.getPrix();
        GenerController gc=new GenerController();
        String idu=gc.getCookieObject(request, "Utilisateur");
        ObjetDepense o=new ObjetDepense(jour, annee, mois, categorie, nom, prix);
        ServDepense sv=new ServDepense();
        ArrayList<String> test=sv.ajoutdepense(idu, o);
        if(sv.isDepense(categorie)!=0){
        }
        else{
            
            test.add("Code "+categorie +" n'existe pas");
        }
        if(sv.isDouble(prix)!=true){
            test.add(""+prix+"n'est pas numeric");
        }

        if(test.size()>0){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }else if(test.size()==0){
            sv.ajoutdepensevrai(idu, o);
           response.setStatus(HttpServletResponse.SC_OK);
        }
        return test;
    }
    @PostMapping("/ajoutdepensecsv")
    public String ajoutdepensecsv(@RequestParam MultipartFile file) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            List<ClassCsv> classCsvList = new ArrayList<>();
    
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(";");
                if (row.length >= 3) {
                    String date = row[0];
                    String code = row[1];
                    double budget = Double.parseDouble(row[2]);
    
                    ClassCsv classCsv = new ClassCsv(date, code, budget);
                    classCsvList.add(classCsv);
                }
            }
            ServDepense sd=new ServDepense();
            for(ClassCsv cs:classCsvList){
                sd.ajoutdepensecsv(cs);
            }
            // Utilisez classCsvList comme vous le souhaitez, par exemple en appelant un service ou en effectuant d'autres opérations
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "Effectue csv";
    }
    
    @PostMapping("/validerrecette")
    public String validerrecette(@RequestBody Map<String, String> vdata, HttpServletResponse response,HttpServletRequest request) throws Exception{
       String l="";
        String idpatient=vdata.get("idpatient");
        Double prix=Double.parseDouble(vdata.get("prix"))+0.0;
        String d=vdata.get("dateRecette");
        ServRecette sv=new ServRecette();
        if(sv.testIfRemboursable(idpatient)!=0){
            sv.updateRecette(idpatient,prix,d);
            return "ok";
        }
       return "non ok";
    }
}

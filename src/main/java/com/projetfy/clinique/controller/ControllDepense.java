package com.projetfy.clinique.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class ControllDepense {
    //admin
    @PostMapping("/ajoutdepense")
    public String ajoutdepense(@RequestBody ObjetDepense data, HttpServletResponse response,HttpServletRequest request) throws Exception{
        String jour = data.getJour();
        String annee=data.getAnnee();
        List<String>mois=data.getMois();
        String categorie=data.getCategorie();
        String nom=data.getNom();
        double prix=data.getPrix();
        GenerController gc=new GenerController();
        String idu=gc.getCookieObject(request, "Utilisateur");
        ObjetDepense o=new ObjetDepense(jour, annee, mois, categorie, nom, prix);
        ServDepense sv=new ServDepense();
        sv.ajoutdepense(idu, o);
        return "ajout depense";
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
    
}

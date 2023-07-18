package com.projetfy.clinique.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetfy.clinique.genericDAO.GenerController;
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
}

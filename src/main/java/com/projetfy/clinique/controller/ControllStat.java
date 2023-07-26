package com.projetfy.clinique.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@CrossOrigin
public class ControllStat{
@GetMapping("/listdonnee")
    public int[]listdonnee() { 
        int[] donnee={10,3,7};
        return donnee;
    }
@GetMapping("/listtitre")
    public String[]listtitre(HttpServletRequest request) { 
        String[] titre={"titre1","titre2","titre3"};
        return titre;
    }
}
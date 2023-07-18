package com.projetfy.clinique.controller;
import java.util.Vector;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetfy.clinique.genericDAO.GenerController;
import com.projetfy.clinique.model.Budget;
import com.projetfy.clinique.model.BudgetBenef;
import com.projetfy.clinique.service.ServBudget;
import com.projetfy.clinique.service.ServBudgetBenef;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class ControllBudget {
    //admin
    @GetMapping("/listbudget")
    public Vector<Budget> getAdminId(HttpServletRequest request) {
        ServBudget sb=new ServBudget();
        GenerController gc=new GenerController();
        int cookieAnnee=Integer.parseInt(gc.getCookieObject(request, "Budget"));
        Vector<Budget> list=sb.list(cookieAnnee);
        return list; // Cookie non trouvé
    }
    @GetMapping("/listrecette")
    public Vector<BudgetBenef> recette(HttpServletRequest request) {
        ServBudgetBenef sb=new ServBudgetBenef();
        GenerController gc=new GenerController();
        int cookieAnnee=Integer.parseInt(gc.getCookieObject(request, "Budget"));
        int cookieMois=Integer.parseInt(gc.getCookieObject(request, "BudgetBenef"));
        Vector<BudgetBenef> list=sb.listRecette(cookieMois,cookieAnnee);
        return list; // Cookie non trouvé
    }
    @GetMapping("/listdepense")
    public Vector<BudgetBenef> depense(HttpServletRequest request) {
        ServBudgetBenef sb=new ServBudgetBenef();
        GenerController gc=new GenerController();
        int cookieAnnee=Integer.parseInt(gc.getCookieObject(request, "Budget"));
        int cookieMois=Integer.parseInt(gc.getCookieObject(request, "BudgetBenef"));
        Vector<BudgetBenef> list=sb.listDepense(cookieMois,cookieAnnee);
        return list; // Cookie non trouvé
    }

    //fin admin
}

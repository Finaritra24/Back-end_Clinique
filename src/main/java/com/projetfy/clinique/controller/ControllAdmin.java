package com.projetfy.clinique.controller;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetfy.clinique.service.ServAdmin;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class ControllAdmin {
    //admin
    @PostMapping("/ifMdpAdmin")
    public String ifMdpAdmin(@RequestBody Map<String, String> loginData, HttpServletResponse response,HttpServletRequest request) throws Exception{
        String mdp = loginData.get("mdp");
        String id=getAdminId(request);
        boolean btest=new ServAdmin().ifMdp(id, mdp);
        if (btest) {
            response.setStatus(HttpServletResponse.SC_OK);
            return "Connexion réussie";
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "Informations d'identification incorrectes";
    }
    @GetMapping("/getAdminId")
    public String getAdminId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("adminId")) {
                    return cookie.getValue();
                }
            }
        }
        return "null"; // Cookie non trouvé
    }

    @PostMapping("/loginAdmin")
    public String testLogin(@RequestBody Map<String, String> loginData, HttpServletResponse response) throws Exception{
        String identification = loginData.get("identification");
        String mdp = loginData.get("mdp");
        boolean btest=new ServAdmin().testLoginAdmin(identification, mdp);
        if (btest) {
            String id=new ServAdmin().getIdAdmin(identification, mdp);
            Cookie cookie = new Cookie("adminId", id);
            cookie.setMaxAge(60 * 60 * 24); // Durée de vie du cookie (1 jour)
            response.addCookie(cookie);
            HttpHeaders headers = new HttpHeaders();
            headers.add(new HttpHeaders().SET_COOKIE, cookie.toString());
            response.setStatus(HttpServletResponse.SC_OK);
            return "Connexion réussie";
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "Informations d'identification incorrectes";
    }
    //fin admin
}

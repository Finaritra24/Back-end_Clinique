package com.projetfy.clinique.controller;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetfy.clinique.service.ServUser;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class ControllUser {
    //user
    @PostMapping("/ifMdpUser")
    public String ifMdpUser(@RequestBody Map<String, String> loginData, HttpServletResponse response,HttpServletRequest request) throws Exception{
        String mdp = loginData.get("mdp");
        String id=getUserId(request);
        boolean btest=new ServUser().ifMdp(id, mdp);
        if (btest) {
            response.setStatus(HttpServletResponse.SC_OK);
            return "Connexion réussie";
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "Informations d'identification incorrectes";
    }
    @PostMapping("/loginUser")
    public String testUser(@RequestBody Map<String, String> loginData, HttpServletResponse response,HttpServletRequest request) throws Exception{
        String identification = loginData.get("identification");
        String mdp = loginData.get("mdp");
        boolean btest=new ServUser().testLoginUser(identification, mdp);
            String idUser=new ServUser().getIdUser(identification, mdp);
            
            if (btest) {
                String id=new ServUser().getIdUser(identification, mdp);
                Cookie cookie = new Cookie("idUtilisateur", id);
                cookie.setMaxAge(60 * 60 * 24); // Durée de vie du cookie (1 jour)
                response.addCookie(cookie);
                HttpHeaders headers = new HttpHeaders();
                headers.add(new HttpHeaders().SET_COOKIE, cookie.toString());
                response.setStatus(HttpServletResponse.SC_OK);
                return "Connexion réussie";
            }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "Informations d'identification incorrectes pour idUser="+idUser;
    }
    @GetMapping("/getUserId")
    public String getUserId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("idUtilisateur")) {
                    return cookie.getValue();
                }
            }
        }
        return "USR1"; // Cookie non trouvé
    }
    //finuser
}

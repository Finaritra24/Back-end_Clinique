package com.projetfy.clinique.service;

import java.util.Vector;

import com.projetfy.clinique.model.Administrateur;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;

public class ServAdmin {
    public static boolean testLoginAdmin(String identification, String mdp) throws Exception{
        Vector<Administrateur> list=new Administrateur().find("SELECT * FROM administrateur where identification='"+identification+"' and passwrd='"+mdp+"'", null);
        if(list.size()>=1){return true;}
        return false;
    }

    public static String getIdAdmin(String identification, String mdp) throws Exception{
        Vector<Administrateur> list=new Administrateur().find("SELECT * FROM administrateur where identification='"+identification+"' and passwrd='"+mdp+"'", null);
        String a=list.get(0).getIdAdmin();
        return a;
    }
    // public Vector<Administrateur> getListAdmin(HttpServletRequest request) throws Exception{
    //     Cookie[] cookies = request.getCookies();
    //     String userId = null;
    //     if (cookies != null) {
    //         for (Cookie cookie : cookies) {
    //             if (cookie.getName().equals("userId")) {
    //                 userId = cookie.getValue();
    //                 break;
    //             }
    //         }
    //     }
    //     Vector<Administrateur> list=new Administrateur().find("SELECT * FROM administrateur where idadmin='"+userId+"' and passwrd='"+mdp+"'", null);
    //     return list;
    // }
    public String getCookie(HttpServletRequest request) throws Exception{
        Cookie[] cookies = request.getCookies();
        String userId = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userId")) {
                    userId = cookie.getValue();
                    break;
                }
            }
        }
        return userId;
    }
    public static boolean ifMdp(String id,String mdp) throws Exception{
        Vector<Administrateur> list=new Administrateur().find("SELECT * FROM administrateur where idadmin='"+id+"' and passwrd='"+mdp+"'", null);
        if(list.size()>=1){return true;}
        return false;
    }
    public static void main(String[]args)throws Exception{
        System.out.println(getIdAdmin("admin1","pass1"));
    }
}

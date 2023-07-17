package com.projetfy.clinique.service;

import java.util.Vector;

import com.projetfy.clinique.model.Utilisateur;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;

public class ServUser {
    public static boolean testLoginUser(String identification, String mdp) throws Exception{
        Vector<Utilisateur> list=new Utilisateur().find("SELECT * FROM utilisateur where identification='"+identification+"' and passwrd='"+mdp+"'", null);
        if(list.size()>=1){return true;}
        return false;
    }

    public static String getIdUser(String identification, String mdp) throws Exception{
        Vector<Utilisateur> list=new Utilisateur().find("SELECT * FROM utilisateur where identification='"+identification+"' and passwrd='"+mdp+"'", null);
        String a=list.get(0).getIdUser();
        return a;
    }
    public static Vector<Utilisateur> listUtilisateur() throws Exception{
        Vector<Utilisateur> list=new Utilisateur().find("SELECT * FROM utilisateur", null);
        return list;
    }
    // public Vector<Utilisateur> getListUser(HttpServletRequest request) throws Exception{
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
    //     Vector<Utilisateur> list=new Utilisateur().find("SELECT * FROM administrateur where idadmin='"+userId+"' and passwrd='"+mdp+"'", null);
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
        Vector<Utilisateur> list=new Utilisateur().find("SELECT * FROM administrateur where iduser='"+id+"' and passwrd='"+mdp+"'", null);
        if(list.size()>=1){return true;}
        return false;
    }
    public static void main(String[]args)throws Exception{
        Vector<Utilisateur> l=listUtilisateur();
        for(Utilisateur u:l){
            System.out.println(u);
        }
    }
}

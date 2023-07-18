package com.projetfy.clinique.genericDAO;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.Vector;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class GenerController {
    //composant
    public static String wType(String nom,String nom2){
        String type="";
        if(nom=="java.lang.String"){
            if(nom2.startsWith("id") && !nom2.equals("identification")){
                type= "list";
            }
            else if(nom2.equals("passwrd")){
                type="mdp";
            }
            else{
                type= "text";
            }
        }
        else if(nom=="java.lang.Double" ){
           type= "double";
        }
        else{
            type= "int";
        }
        return type;
    }
    //fonction 

    public static int getType(String input) {
        try {
            Integer.parseInt(input);
            return 0;
        } catch (NumberFormatException e1) {
            try {
                Double.parseDouble(input);
                return 1;
            } catch (NumberFormatException e2) {
                return 2;
            }
        }
    }
    

    //Cookie
    @PostMapping("/setCookie-*")
    public static String setCookieObject(@RequestBody Map<String, String> vdata, HttpServletResponse response,HttpServletRequest request) {
        String id = vdata.get("id");
        String path = request.getRequestURI().substring(request.getContextPath().length()).replace("/setCookie-",""); // Récupère le chemin de l'URL
        String nameClass="";
        try {
            if(path.equals("Budget")){
                Cookie cookie = new Cookie("budget", id);
                cookie.setMaxAge(60 * 60 * 24); // Durée de vie du cookie (1 jour)
                response.addCookie(cookie);
                HttpHeaders headers = new HttpHeaders();
                headers.add(new HttpHeaders().SET_COOKIE, cookie.toString());
                response.setStatus(HttpServletResponse.SC_OK);
                return "value:"+id;
            }
            else{
                Class c = Class.forName("com.projetfy.clinique.model."+path);
                nameClass=c.getName().replace("com.projetfy.clinique.model.", "");
                Cookie cookie = new Cookie("id"+nameClass, id);
                cookie.setMaxAge(60 * 60 * 24); // Durée de vie du cookie (1 jour)
                response.addCookie(cookie);
                HttpHeaders headers = new HttpHeaders();
                headers.add(new HttpHeaders().SET_COOKIE, cookie.toString());
                response.setStatus(HttpServletResponse.SC_OK);
                return "Ajout Cookie réussie,nom cookie=id"+nameClass+"valeur="+id;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "Erreur ajout Cookie";
    }
    public static String setCookieInsert(String nom,String id, HttpServletResponse response,HttpServletRequest request) {
        String path = request.getRequestURI().substring(request.getContextPath().length()).replace("/setCookieInsert-",""); // Récupère le chemin de l'URL
        String nameClass="";
        try {
            Class c = Class.forName("com.projetfy.clinique.model."+path);
            nameClass=c.getName().replace("com.projetfy.clinique.model.", "");
            Cookie cookie = new Cookie("id"+nameClass, id);
            cookie.setMaxAge(60 * 60 * 24); // Durée de vie du cookie (1 jour)
            response.addCookie(cookie);
            HttpHeaders headers = new HttpHeaders();
            headers.add(new HttpHeaders().SET_COOKIE, cookie.toString());
            response.setStatus(HttpServletResponse.SC_OK);
            return "Ajout Cookie réussie,nom cookie=id"+nameClass+"valeur="+id;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return "Erreur ajout Cookie";
    }
    @GetMapping("/getCookie-*")
    public static String getCookieObject(HttpServletRequest request,String nameClass) {
        try {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if(nameClass.equals("Budget")){
                    if (cookie.getName().equals("budget")) {
                        return cookie.getValue();
                    }
                }else if(nameClass.equals("BudgetBenef")){
                    if (cookie.getName().equals("budgetbenef")) {
                        return cookie.getValue();
                    }
                }
                else{
                    Class c=Class.forName("com.projetfy.clinique.model."+nameClass);
                        if (cookie.getName().equals("id"+nameClass)) {
                        return cookie.getValue();
                     }
                }
            }
        }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "La class "+nameClass+" n'existe pas";
        }
        return null; // Cookie non trouvé
    }
    public int ifInListParameter(String[] lesAttributs,String nom){
        for(String s:lesAttributs){
            if(s.equals(nom)){
                return 1;
            }
        }
        return 0;
    }
    @PostMapping("/dropg-*")
    public static String drop(@RequestBody Map<String, String> vdata,HttpServletResponse response,HttpServletRequest request){
        String path = request.getRequestURI().substring(request.getContextPath().length()).replace("/dropg-",""); // Récupère le chemin de l'URL
        String id=vdata.get("id");
        try {
            Connection con=new Connex().getConnection();
            String sql="delete from "+path+" where id"+path+"='"+id+"'";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            if(con!=null || stmt!=null ){
                con.close();
                stmt.close();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    //Ajout
    @PostMapping("/ajoutg-*")
    public String ajout(@RequestBody Map<String, String> vdata,HttpServletResponse response,HttpServletRequest request){
        String path = request.getRequestURI().substring(request.getContextPath().length()).replace("/ajoutg-",""); // Récupère le chemin de l'URL
        String nameClass="";
        if(path.equals("Panier")){
            String idcookie=getCookieObject(request, path);
            if(idcookie!=null){
                return "Panier deja existant";
            }
        }
        try {
            Class c = Class.forName("com.projetfy.clinique.model."+path);
            nameClass=c.getName();
            Object instance = c.newInstance();
            String[] lesAttributs = new String[c.getDeclaredFields().length];
                for (int i = 1; i < c.getDeclaredFields().length; i++) {
                        String myMethod="";
                        lesAttributs[i] = c.getDeclaredFields()[i].getName();
                        String value=vdata.get(lesAttributs[i].toLowerCase());
                    try {
                        Class cAttribut=c.getDeclaredFields()[i].getType();
                        String nameTypeAttribut=cAttribut.getTypeName();
                        myMethod="set" + toUpperCaseFirst(lesAttributs[i]);
                        if( lesAttributs[i].startsWith("id") && c.getDeclaredFields()[i].isAnnotationPresent(AnnotInsert.class)){
                            String namecId=lesAttributs[i].replace("id","");
                            Class cId=Class.forName("com.projetfy.clinique.model."+namecId);
                            String myIdCookie=getCookieObject(request,namecId);
                            Method method = c.getMethod(myMethod, String.class);
                            method.invoke(instance, myIdCookie);
                        }
                        else{
                            if(nameTypeAttribut=="java.lang.String"){
                                Method method = c.getMethod(myMethod, String.class);
                                method.invoke(instance, value);
                            }
                            else if(nameTypeAttribut=="java.lang.Double" || nameTypeAttribut=="double" ){
                                Method method = c.getMethod(myMethod, Double.TYPE);
                                Double v=Double.parseDouble(value)+0.0;
                                method.invoke(instance, v);
                            }
                            else{
                                Method method = c.getMethod(myMethod, Integer.TYPE);
                                int v=Integer.parseInt(value);
                                method.invoke(instance, v);
                            }
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                            | NoSuchMethodException | SecurityException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        System.out.print(myMethod);
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        return "La methode "+myMethod+" n'existe pas" +lesAttributs[i];
                    }
                }
            //2
            Method nameMethode = c.getMethod("getNameAuto");
            String name=(String)nameMethode.invoke(instance);
            Method createMethod = c.getMethod("create", String.class, Connection.class);
            createMethod.invoke(instance, name, null);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "La class "+path+" n'existe pas";
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
        | SecurityException e) {
            // TODO Auto-generated catch block
            Throwable cause = e.getCause();
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "Erreur ajout "+nameClass+" et "+vdata.get("identification");
        } catch (InstantiationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return null;
    }
    @PostMapping("/modif-*")
    public String modif(@RequestBody Map<String, String> vdata,HttpServletResponse response,HttpServletRequest request){
        String path = request.getRequestURI().substring(request.getContextPath().length()).replace("/modif-",""); // Récupère le chemin de l'URL
        String nameClass="";
        try {
            Class c = Class.forName("com.projetfy.clinique.model."+path);
            nameClass=c.getName();
            Object instance = c.newInstance();
            String[] lesAttributs = new String[c.getDeclaredFields().length];
                for (int i = 0; i < c.getDeclaredFields().length; i++) {
                    String myMethod="";
                        lesAttributs[i] = c.getDeclaredFields()[i].getName();
                        String value=vdata.get(lesAttributs[i].toLowerCase());
                    try {
                        Class cAttribut=c.getDeclaredFields()[i].getType();
                        String nameTypeAttribut=cAttribut.getTypeName();
                        myMethod="set" + toUpperCaseFirst(lesAttributs[i]);
                        if(value=="" || value==null && i!=0 && !lesAttributs[i].equals("idUtilisateur")){
                            Method method = c.getMethod(myMethod, String.class);
                            method.invoke(instance, null);
                        }
                        else{
                            if( lesAttributs[i].startsWith("id") && c.getDeclaredFields()[i].isAnnotationPresent(AnnotInsert.class)){
                                String namecId=lesAttributs[i].replace("id","");
                                Class cId=Class.forName("com.projetfy.clinique.model."+namecId);
                                String myIdCookie=getCookieObject(request,namecId);
                                Method method = c.getMethod(myMethod, String.class);
                                method.invoke(instance, myIdCookie);
                            }                        
                            else if(lesAttributs[i].startsWith("id") && i==0){
                                String myIdCookie=getCookieObject(request,path);
                                Method method = c.getMethod(myMethod, String.class);
                                method.invoke(instance, myIdCookie);
                            }
    
                            else{
                                if(nameTypeAttribut=="java.lang.String"){
                                    Method method = c.getMethod(myMethod, String.class);
                                    method.invoke(instance, value);
                                }
                                else if(nameTypeAttribut=="java.lang.Double" || nameTypeAttribut=="double" ){
                                    Method method = c.getMethod(myMethod, Double.TYPE);
                                    Double v=Double.parseDouble(value)+0.0;
                                    method.invoke(instance, v);
                                }
                                else{
                                    Method method = c.getMethod(myMethod, Integer.TYPE);
                                    int v=Integer.parseInt(value);
                                    method.invoke(instance, v);
                                }
                            }
                        }
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                            | NoSuchMethodException | SecurityException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        System.out.print(myMethod);
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        return "La methode "+myMethod+" n'existe pas" +lesAttributs[i];
                    }
                }
            //2
            Method createMethod = c.getMethod("update", Connection.class);
            Connection con = null;
            if (con == null) {
                try {
                    con = Connex.getConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            createMethod.invoke(instance, con);

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "La class "+path+" n'existe pas";
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
        | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "Erreur ajout "+nameClass+" et "+vdata.get("id");
        } catch (InstantiationException e1) {
            Throwable cause = e1.getCause();
            cause.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "Erreur lors de l'invocation de la méthode : " + cause.getMessage();
        }
        return null;
    }
    public static String toUpperCaseFirst(String str){
        if (str.length() > 0) {
            String firstLetter = str.substring(0, 1).toUpperCase();
            String restOfString = str.substring(1);

            String result = firstLetter + restOfString;
            return result;
        } else {
            System.out.println(str); // Chaîne vide, aucune modification nécessaire
        }
        return str;
    }
    //getObject
    @GetMapping("/getObject-*")
    public static Object getTheObject(HttpServletRequest request){
        String path = request.getRequestURI().substring(request.getContextPath().length()).replace("/getObject-","");
        try {
            Class c = Class.forName("com.projetfy.clinique.model."+path);
            String value=getCookieObject(request,path);
            String nomClass=path;
            if(nomClass.equals("VProduit")){
                nomClass="Produit";
            }
            String sql="select*from "+path+" where id"+nomClass+"='"+value+"'";
            Method method = c.getMethod("find", String.class,Connection.class);
            Object o=c.newInstance();Connection con=null;
            Vector<?>v=(Vector<?>)method.invoke(o,sql ,con);
            o=v.get(0);
            return o;
        } catch (ClassNotFoundException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    //Liste
    @GetMapping("/listg-*")
    public Vector<?> listObject(HttpServletRequest request) { 
        String path = request.getRequestURI().substring(request.getContextPath().length()).replace("/listg-",""); // Récupère le chemin de l'URL
        String nameClass="";
        try {
            Class c = Class.forName("com.projetfy.clinique.model."+path);
            nameClass=c.getName().replace("com.projetfy.clinique.model.", "");
            Object o=c.newInstance();
                Method method = c.getMethod("find", String.class,Connection.class);
                Connection con=null;
                String sql="select*from "+nameClass;
                if(path.equals("VPanierProduit")){
                    String idvp=getCookieObject(request, "PanierProduit");
                    sql+=" where idpanierproduit='"+idvp+"'";
                }
                Vector<?>v=(Vector<?>)method.invoke(o,sql ,con);
                return v;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    //deconnexion
    @GetMapping("/deconnexion")
    public String supprimerCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies(); // Récupérer tous les cookies de la requête

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0); // Définir l'âge du cookie à 0 pour le supprimer
                response.addCookie(cookie); // Ajouter le cookie à la réponse avec un âge nul
                cookie.setMaxAge(60 * 60 * 24); // Durée de vie du cookie (1 jour
            }
        }
        return "Cookies supprimés";
    }

    public static String myClassName(String url){
        String myClass=url.replace("com.projetfy.clinique.model.", "");
        int index =myClass.indexOf("_");
        myClass=myClass.replace(myClass.substring(index),"");
        return myClass;
    }
    public static String myFunctionName(String url){
        String myClass=url.replace("com.projetfy.clinique.model.", "");
        int index =myClass.indexOf("_");
        myClass=myClass.replace(myClass.substring(index),"");
        return myClass;
    }
    public static void main (String[]args) throws Exception{
    }
}

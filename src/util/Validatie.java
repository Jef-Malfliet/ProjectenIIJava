/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Nante
 */
public class Validatie {
    
    public static boolean rijksregisternummerIsCorrect(String rijksregisternummer){
        
        String pattern = "^([0-9]{2}).(0[1-9]|1[0-2]).((0[1-9])|(1[0-9])|(2[0-9]|3[0-1]))-([0-9]{3}).([0-9]{2})$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(rijksregisternummer);
        if (!m.matches()) {
            return false;
        }

        int controle = 0;
        int getal = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(m.group(1)).append(m.group(2)).append(m.group(3)).append(m.group(7));
        try {
            getal = Integer.parseInt(sb.toString());
            controle = Integer.parseInt(m.group(8));//controle cijfer
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        int modulo = getal % 97;
        if (97 - modulo != controle) {
            return false;
        }
        return true;
        
    }
    public static boolean isNullOrEmpty(String parameter){
        return parameter == null || parameter.isEmpty();
            
        }

    
}

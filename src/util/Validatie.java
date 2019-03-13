/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Nante
 */
public class Validatie {

    public static boolean rijksregisternummerIsCorrect(String rijksregisternummer) {

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

    public static boolean isNullOrEmpty(String parameter) {
        return parameter == null || parameter.isEmpty();

    }

    public static boolean isGsmNummer(String nummer) {
        return nummer.matches("0\\d{9}") || nummer.matches("00\\d{11}");
    }

    public static boolean isVasteTelefoonNummer(String nummer) {
        return nummer.matches("0\\d{8}") || nummer.matches("00\\d{10}");
    }

    public static boolean isGeldigEmailAdres(String email) {
        return email.matches("^([a-zA-Z0-9éèà]+[a-zA-Z0-9.\\-éèàïëöüäîôûêâù]*)@([a-zA-Z]+)[.]([a-z]+)([.][a-z]+)*$");
    }

    public static boolean isValidLeeg(String parameter) {

        return parameter.equals("/");
    }

    public static boolean isPostcode(String postcode) {
        return postcode.matches("[1-9][0-9]{3}");
    }

    public static boolean isNull(Object parameter) {
        return parameter == null;
    }

    public static boolean isLeeg(String parameter) {
        return parameter.isEmpty();
    }

    public static boolean isHuisnummer(String huisnummer) {
        return huisnummer.matches("[0-9]*[a-zA-Z]*");
    }

    public static String getYoutubeId(String url) {
        String pattern = "https?:\\/\\/(?:[0-9A-Z-]+\\.)?(?:youtu\\.be\\/|youtube\\.com\\S*[^\\w\\-\\s])([\\w\\-]{11})(?=[^\\w\\-]|$)(?![?=&+%\\w]*(?:['\"][^<>]*>|<\\/a>))[?=&+%\\w]*";
        Pattern compiledPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = compiledPattern.matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        }//from   w  w w . jav a 2s. c o m
        else {
            throw new IllegalArgumentException("Foutieve Youtube URL");
        }
    }

    public static boolean isANumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isInFuture(LocalDate date) {
        LocalDate today = LocalDate.now();
        if (date.getYear() >= today.getYear() && date.getDayOfYear() >= today.getDayOfYear()) {
            return true;
        }
        return false;
    }

}

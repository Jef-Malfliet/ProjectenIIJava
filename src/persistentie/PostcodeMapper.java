/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Land;
import domein.Postcode;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nante
 */
public class PostcodeMapper {

    private static final Map<Land, Map<String, Postcode>> lijst = new HashMap<>() {
        {
            put(Land.België, getPostcodesBelgië());
            put(Land.Nederland, getPostcodesNederland());

        }
    };

    public PostcodeMapper() {
        lijst.put(Land.België, getPostcodesBelgië());
        lijst.put(Land.Nederland, getPostcodesNederland());

    }

    public static Postcode getGemeentesBelgië(String postcode, Land land) {
        return lijst.get(land).get(postcode);
    }

    private static Map<String, Postcode> getPostcodesBelgië() {
        Map<String, Postcode> lijst = new HashMap<>();
        try (Scanner input = new Scanner(new File("PostcodesBelgië.csv"))) {

            while (input.hasNext()) {
                String[] split = input.nextLine().split(";");
                if (lijst.containsKey(split[0])) {
                    List<String> gemeentes = lijst.get(split[0]).getGemeentes();
                    gemeentes.add(split[1]);
                    lijst.put(split[0], new Postcode(split[0], gemeentes, Land.België));
                } else {
                    List<String> gemeentes = new ArrayList();
                    gemeentes.add(split[1]);
                    lijst.put(split[0], new Postcode(split[0], gemeentes, Land.België));
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PostcodeMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lijst;
    }

    private static Map<String, Postcode> getPostcodesNederland() {
        Map<String, Postcode> lijst = new HashMap<>();
        try (Scanner input = new Scanner(new File("PostcodesNederland.csv"))) {

            while (input.hasNext()) {
                String[] split = input.nextLine().split(";");
                if (lijst.containsKey(split[0])) {
                    List<String> gemeentes = lijst.get(split[0]).getGemeentes();
                    gemeentes.add(split[1]);
                    lijst.put(split[0], new Postcode(split[0], gemeentes, Land.Nederland));
                } else {
                    List<String> gemeentes = new ArrayList();
                    gemeentes.add(split[1]);
                    lijst.put(split[0], new Postcode(split[0], gemeentes, Land.Nederland));
                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(PostcodeMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lijst;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Activiteit;
import domein.ActiviteitType;
import domein.Geslacht;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mout
 */
public class ActiviteitenMapper {

    public static List<Activiteit> getActiviteiten() {
        List<Activiteit> lijst = new ArrayList<>();
        try (Scanner input = new Scanner(new File("DataActiviteiten.csv"))) {
            while (input.hasNext()) {
                String[] split = input.nextLine().split(";");
                lijst.add(new Activiteit(split[0], LocalDate.of(Integer.parseInt(split[1].trim()), Integer.parseInt(split[2].trim()), Integer.parseInt(split[3].trim())),
                        LocalDate.of(Integer.parseInt(split[4].trim()), Integer.parseInt(split[5].trim()), Integer.parseInt(split[6].trim())),
                        Integer.parseInt(split[8]), randomType()));
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            Logger.getLogger(PostcodeMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lijst;
    }

    private static ActiviteitType randomType() {
        SecureRandom r = new SecureRandom();
        return ActiviteitType.values()[r.nextInt(2)];
    }
}

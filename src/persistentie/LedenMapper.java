/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Geslacht;
import domein.Graad;
import domein.Land;
import domein.LesType;
import domein.Lid;
import domein.RolType;
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
public class LedenMapper {

    public static List<Lid> getLeden() {
        List<Lid> lijst = new ArrayList<>();
        try (Scanner input = new Scanner(new File("DataLeden.csv"))) {
            while (input.hasNext()) {
                String[] split = input.nextLine().split(";");
                lijst.add(new Lid(split[0], split[1], split[2], "0" + split[3], "0" + split[4], split[5], split[6], split[7], split[8], split[9], Land.valueOf(split[10]),
                        split[11], split[12], split[13], LocalDate.of(Integer.parseInt(split[14]), Integer.parseInt(split[15]), Integer.parseInt(split[16])),
                        LocalDate.of(Integer.parseInt(split[17]), Integer.parseInt(split[18]), Integer.parseInt(split[19])),
                        randomAanwezigheden(), randomGeslacht(), randomGraad(), randomType(), randomFormule()));
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            Logger.getLogger(PostcodeMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lijst;
    }

    private static Geslacht randomGeslacht() {
        SecureRandom r = new SecureRandom();
        return Geslacht.values()[r.nextInt(3)];
    }

    private static Graad randomGraad() {
        SecureRandom r = new SecureRandom();
        return Graad.values()[r.nextInt(18)];
    }

    private static RolType randomType() {
        SecureRandom r = new SecureRandom();
        double kans = r.nextDouble();
        if (kans <= 0.7) {
            return RolType.LID;
        } else if (kans <= 0.9) {
            return RolType.NIET_LID;
        } else {
            return RolType.LESGEVER;
        }
    }

    private static LesType randomFormule() {
        SecureRandom r = new SecureRandom();
        return LesType.values()[r.nextInt(5)];
    }

    private static List<LocalDate> randomAanwezigheden() {
        SecureRandom r = new SecureRandom();
        List<LocalDate> temp = new ArrayList<>();
        for (int i = 0; i < r.nextInt(20); i++) {
            temp.add(LocalDate.of(r.nextInt(15) + 2000, r.nextInt(11) + 1, r.nextInt(27) + 1));
        }
        return temp;
    }
}

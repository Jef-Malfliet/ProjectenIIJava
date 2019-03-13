/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Kampioenschap;
import java.io.File;
import java.io.FileNotFoundException;
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
public class KampioenschapMapper {

    public static List<Kampioenschap> getKampioenschappen() {
        List<Kampioenschap> lijst = new ArrayList<>();
        try (Scanner input = new Scanner(new File("DataKampioenschap.csv"))) {

//            String[] splitBegin = input.nextLine().split(";");
//            System.out.println(Integer.parseInt(splitBegin[0].substring(1).trim()));
//            lijst.add(new Kampioenschap(LocalDate.of(Integer.parseInt(splitBegin[0].substring(1).trim()), Integer.parseInt(splitBegin[1].trim()), Integer.parseInt(splitBegin[2].trim())),
//                    convertToArray(splitBegin[3]), convertToArray(splitBegin[4])));
            while (input.hasNext()) {
                String[] split = input.nextLine().split(";");
                lijst.add(new Kampioenschap(split[0], LocalDate.of(Integer.parseInt(split[1].trim()), Integer.parseInt(split[2].trim()), Integer.parseInt(split[3].trim())),
                        convertToArray(split[4]), convertToArray(split[5])));
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            Logger.getLogger(PostcodeMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lijst;
    }

    private static String[] convertToArray(String string) {
        return string.split(" ");
    }
}

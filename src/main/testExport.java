/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
 
import domein.DomeinController;
import domein.Geslacht;
import domein.Graad;
import domein.Lid;
import domein.RolType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import persistentie.ExportFiles;
 
/**
 *
 * @author Nante
 */
public class testExport {
 
    public static void main(String[] args) {
        DomeinController dc = new DomeinController();
 
            Lid lid1 = new Lid("Nante", "Vermeulen", "nv12345", "0479154879", "053548216", "Straat", "100","/", "9320", "Landegem", "België", "nante.vermeulen@student.hogent.be",
                "ouders.nante@telenet.be", LocalDate.of(1998, 8, 16), LocalDate.of(2014, 5, 9), new ArrayList<>(), Geslacht.MAN, Graad.GROEN, RolType.BEHEERDER);
 
        Lid lid0 = new Lid("Indy", "Van Canegem", "ivc12345", "0479154978", "053698442", "Straat", "13","88", "9520", "Zele", "België", "indy.vancanegem@student.hogent.be",
                "ouders.indy@skynet.be", LocalDate.of(1998, 8, 16), LocalDate.of(2014, 5, 9), new ArrayList<>(), Geslacht.ANDERS, Graad.GROEN, RolType.LID);
 
        Lid lid2 = new Lid("Jef", "Malfliet", "jm12345", "0234567890", "053698420", "Straat", "1","2.9", "9220", "Hamme", "België", "jef.malfliet@student.hogent.be",
                "ouders.jef@proxymus.be", LocalDate.of(1999, 10, 24), LocalDate.of(2016, 8, 31), new ArrayList<>(), Geslacht.VROUW, Graad.WIT, RolType.LESGEVER);
 
        Lid lid3 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14","8", "9320", "Erembodegen", "België", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER);
 
        dc.voegLidToe(lid0);
        dc.voegLidToe(lid1);
        dc.voegLidToe(lid2);
        dc.voegLidToe(lid3);
 
        String locatie = System.getProperty("user.home");
        locatie += "/Desktop/";
        String locatie2 = locatie;
        locatie += "TESTTT";
 
        locatie2 += "hallow12.pdf";
        locatie += ".xls";
 
        //ExportFiles.toExcel(dc.getLeden(), 25, 20, locatie);
        // ExportFiles.toPdf(locatie2, locatie2);
        //  ExportFiles.toPdf(locatie, locatie + ".pdf");
        new ExportFiles().toExcel(dc.getLeden(), 25, 20, locatie);
        new ExportFiles().toPdf(locatie, locatie2);
 
    }
 
}
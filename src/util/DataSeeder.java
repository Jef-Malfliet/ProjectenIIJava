/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import domein.Activiteit;
import domein.DomeinController;
import domein.Geslacht;
import domein.Graad;
import domein.Land;
import domein.LesType;
import domein.Lid;
import domein.Oefening;
import domein.RolType;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 *
 * @author Mout
 */
public class DataSeeder {
    public static void seedData(DomeinController dc){
                Lid lid1 = new Lid("Nante", "Vermeulen", "nv12345", "0479154879", "053548216", "Straat", "100", "/", "9850", "Landegem", Land.België, "98.10.19-333.61", "nante.vermeulen@student.hogent.be",
                "ouders.nante@telenet.be", LocalDate.of(1998, 8, 16), LocalDate.of(2014, 5, 9), new ArrayList<>(), Geslacht.MAN, Graad.GROEN, RolType.BEHEERDER, LesType.DI_DO);

        Lid lid0 = new Lid("Indy", "Van Canegem", "ivc12345", "0479154978", "053698442", "Straat", "13", "88", "9240", "Zele", Land.België, "98.10.19-333.61", "indy.vancanegem@student.hogent.be",
                "ouders.indy@skynet.be", LocalDate.of(1998, 8, 16), LocalDate.of(2014, 5, 9), new ArrayList<>(), Geslacht.ANDERS, Graad.GROEN, RolType.LID, LesType.DI_DO);

        Lid lid2 = new Lid("Jef", "Malfliet", "jm12345", "0234567890", "053698420", "Straat", "1", "2.9", "9220", "Hamme", Land.België, "98.10.19-333.61", "jef.malfliet@student.hogent.be",
                "ouders.jef@proxymus.be", LocalDate.of(1999, 10, 24), LocalDate.of(2016, 8, 31), new ArrayList<>(), Geslacht.VROUW, Graad.WIT, RolType.LESGEVER, LesType.DI_DO);

        Lid lid3 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);

        Lid lid4 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);

        Lid lid5 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid6 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid7 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid8 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid9 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid10 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid11 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid12 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid13 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid14 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid15 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid16 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid17 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid18 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid19 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid20 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid21 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid22 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid23 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid24 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid25 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid26 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid27 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid28 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid29 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid30 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid31 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid32 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid33 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid34 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid35 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid36 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid37 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid38 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid39 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid40 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid41 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid42 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid43 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid44 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid45 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid46 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid47 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid48 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid49 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid50 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid51 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid52 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid53 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);
        
        Lid lid54 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);

        dc.voegLidToe(lid0);
        dc.voegLidToe(lid1);
        dc.voegLidToe(lid2);
        dc.voegLidToe(lid3);

        Activiteit act1 = new Activiteit("Uitstap", LocalDate.of(2014, Month.FEBRUARY, 11), LocalDate.of(2014, Month.FEBRUARY, 11), false, 50);
        Activiteit act2 = new Activiteit("Weekend", LocalDate.of(2019, Month.MARCH, 5), LocalDate.of(2019, Month.MARCH, 7), true, 100);

        dc.voegActiviteitToe(act1);
        dc.voegActiviteitToe(act2);

        dc.schrijfLidIn(act1.getNaam(), lid3.getEmail());

        dc.schrijfLidIn(act2.getNaam(), lid1.getEmail());
        dc.schrijfLidIn(act2.getNaam(), lid2.getEmail());

        Oefening oef1 = new Oefening(Graad.GROEN, "Test1");
        Oefening oef2 = new Oefening(Graad.DAN11, "Test2");
        Oefening oef3 = new Oefening(Graad.BLAUW, "Test3");

        dc.addLesMateriaal(oef3);
        dc.addLesMateriaal(oef2);
        dc.addLesMateriaal(oef1);
    }
}

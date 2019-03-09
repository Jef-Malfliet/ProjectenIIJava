/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domein.Activiteit;
import domein.DomeinController;
import domein.Geslacht;
import domein.Graad;
import domein.Land;
import domein.LesType;
import domein.Lid;
import domein.Oefening;
import domein.RolType;
import gui.MainPanel;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import util.FullScreenResolution;

/**
 *
 * @author IndyV
 */
public class StartUp extends Application {

    @Override
    public void start(Stage primaryStage) {
        //Get screen width and height.
        Rectangle2D waardenScreen = Screen.getPrimary().getVisualBounds();
        FullScreenResolution.setHeight(waardenScreen.getHeight());
        FullScreenResolution.setWidth(waardenScreen.getWidth());

        DomeinController dc = new DomeinController();

        Lid lid1 = new Lid("Nante", "Vermeulen", "nv12345", "0479154879", "053548216", "Straat", "100", "/", "9850", "Landegem", Land.België, "98.10.19-333.61", "nante.vermeulen@student.hogent.be",
                "ouders.nante@telenet.be", LocalDate.of(1998, 8, 16), LocalDate.of(2014, 5, 9), new ArrayList<>(), Geslacht.MAN, Graad.GROEN, RolType.BEHEERDER, LesType.DI_DO);

        Lid lid0 = new Lid("Indy", "Van Canegem", "ivc12345", "0479154978", "053698442", "Straat", "13", "88", "9240", "Zele", Land.België, "98.10.19-333.61", "indy.vancanegem@student.hogent.be",
                "ouders.indy@skynet.be", LocalDate.of(1998, 8, 16), LocalDate.of(2014, 5, 9), new ArrayList<>(), Geslacht.ANDERS, Graad.GROEN, RolType.LID, LesType.DI_DO);

        Lid lid2 = new Lid("Jef", "Malfliet", "jm12345", "0234567890", "053698420", "Straat", "1", "2.9", "9220", "Hamme", Land.België, "98.10.19-333.61", "jef.malfliet@student.hogent.be",
                "ouders.jef@proxymus.be", LocalDate.of(1999, 10, 24), LocalDate.of(2016, 8, 31), new ArrayList<>(), Geslacht.VROUW, Graad.WIT, RolType.LESGEVER, LesType.DI_DO);

        Lid lid3 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegem", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
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

        MainPanel root = new MainPanel(dc);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Taijitan Jiu Jitsu");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Setting stage and root on max width and max height.
        primaryStage.setMaximized(true);
        root.setPrefSize(FullScreenResolution.getWidth(), FullScreenResolution.getHeight());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

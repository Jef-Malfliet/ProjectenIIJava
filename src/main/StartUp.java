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
import domein.LesType;
import domein.Lid;
import domein.RolType;
import gui.MainPanel;
import java.time.LocalDate;
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

        Lid lid1 = new Lid("Nante", "Vermeulen", "nv12345", "0479154879", "053548216", "Straat", "100", "/", "9320", "Landegem", "België", "98.10.19-333.61", "nante.vermeulen@student.hogent.be",
                "ouders.nante@telenet.be", LocalDate.of(1998, 8, 16), LocalDate.of(2014, 5, 9), new ArrayList<>(), Geslacht.MAN, Graad.GROEN, RolType.BEHEERDER, LesType.DI_DO);

        Lid lid0 = new Lid("Indy", "Van Canegem", "ivc12345", "0479154978", "053698442", "Straat", "13", "88", "9520", "Zele", "België", "98.10.19-333.61", "indy.vancanegem@student.hogent.be",
                "ouders.indy@skynet.be", LocalDate.of(1998, 8, 16), LocalDate.of(2014, 5, 9), new ArrayList<>(), Geslacht.ANDERS, Graad.GROEN, RolType.LID, LesType.DI_DO);

        Lid lid2 = new Lid("Jef", "Malfliet", "jm12345", "0234567890", "053698420", "Straat", "1", "2.9", "9220", "Hamme", "België", "98.10.19-333.61", "jef.malfliet@student.hogent.be",
                "ouders.jef@proxymus.be", LocalDate.of(1999, 10, 24), LocalDate.of(2016, 8, 31), new ArrayList<>(), Geslacht.VROUW, Graad.WIT, RolType.LESGEVER, LesType.DI_DO);

        Lid lid3 = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegen", "België", "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_ZA);

        dc.voegLidToe(lid0);
        dc.voegLidToe(lid1);
        dc.voegLidToe(lid2);
        dc.voegLidToe(lid3);

        Activiteit act1 = new Activiteit("Uitstap", new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), false);
        Activiteit act2 = new Activiteit("Weekend", new GregorianCalendar(2019, Calendar.MARCH, 5).getTime(), new GregorianCalendar(2019, Calendar.MARCH, 7).getTime(), true);

        dc.voegActiviteitToe(act1);
        dc.voegActiviteitToe(act2);

        dc.schrijfLidIn(act1, lid3);

        dc.schrijfLidIn(act2, lid1);
        dc.schrijfLidIn(act2, lid2);

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

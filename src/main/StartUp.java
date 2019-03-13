/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domein.Activiteit;
import domein.DomeinController;
import domein.Graad;
import domein.IOefening;
import domein.Kampioenschap;
import domein.Lid;
import domein.Oefening;
import gui.MainPanel;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import persistentie.KampioenschapMapper;
import persistentie.LedenMapper;
import persistentie.OefeningMapper;

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

        List<Lid> ledenLijst = LedenMapper.getLeden();
        
        OefeningMapper oefMapper = new OefeningMapper();
        for(int i=0;i<100;i++){
           Oefening oef = oefMapper.maakOefening();
           dc.addLesMateriaal(oef);
        }

        Activiteit act1 = new Activiteit("Uitstap", LocalDate.of(2014, Month.FEBRUARY, 11), LocalDate.of(2014, Month.FEBRUARY, 11), false, 50);
        Activiteit act2 = new Activiteit("Weekend", LocalDate.of(2019, Month.MARCH, 5), LocalDate.of(2019, Month.MARCH, 7), true, 100);

        dc.voegActiviteitToe(act1);
        dc.voegActiviteitToe(act2);

        for (int i = 0; i < ledenLijst.size(); i++) {
            if (i % 15 == 0) {
                dc.schrijfLidIn(act1.getNaam(), act1.getBeginDatum(), act1.getEindDatum(), ledenLijst.get(i).getEmail());
            }
            if (i % 17 == 0) {
                dc.schrijfLidIn(act2.getNaam(), act2.getBeginDatum(), act2.getEindDatum(), ledenLijst.get(i).getEmail());
            }
        }

        List<Kampioenschap> kampioenschappen = KampioenschapMapper.getKampioenschappen();
        for (Kampioenschap kampioenschap : kampioenschappen) {
            dc.addKampioenschap(kampioenschap);
        }

        for (int i = 0; i < ledenLijst.size(); i++) {
            Kampioenschap temp1 = kampioenschappen.get(i % 10);
            Kampioenschap temp2 = kampioenschappen.get((i + 1) % 10);
            if (i % 15 == 0) {
                dc.schrijfLidInVoorActiviteit(temp1.getNaam(), temp1.getDatum(), ledenLijst.get(i).getEmail());
            }
            if (i % 17 == 0) {
                dc.schrijfLidInVoorActiviteit(temp2.getNaam(), temp2.getDatum(), ledenLijst.get(i).getEmail());
            }
        }

       

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

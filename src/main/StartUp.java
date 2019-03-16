/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domein.Activiteit;
import domein.DomeinController;
import domein.Kampioenschap;
import domein.Lid;
import domein.Oefening;
import gui.MainPanel;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import persistentie.ActiviteitenMapper;
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

        ledenLijst.forEach((lid) -> {
            dc.voegLidToe(lid);
        });

        OefeningMapper oefMapper = new OefeningMapper();
        for (int i = 0; i < 100; i++) {
            Oefening oef = oefMapper.maakOefening();
            dc.addLesMateriaal(oef);
        }

        List<Activiteit> activiteiten = ActiviteitenMapper.getActiviteiten();
        activiteiten.forEach((activiteit) -> {
            dc.voegActiviteitToe(activiteit);
        });
        for (int i = 0; i < ledenLijst.size(); i++) {
            Activiteit temp1 = activiteiten.get(i % 10);
            Activiteit temp2 = activiteiten.get((i + 1) % 10);
            if (i % 7 == 0) {
                dc.schrijfLidIn(temp1.getNaam(), temp1.getBeginDatum(), temp1.getEindDatum(), ledenLijst.get(i).getEmail());
            }
            if (i % 12 == 0) {
                dc.schrijfLidIn(temp2.getNaam(), temp2.getBeginDatum(), temp2.getEindDatum(), ledenLijst.get(i).getEmail());
            }
        }

        List<Kampioenschap> kampioenschappen = KampioenschapMapper.getKampioenschappen();
        kampioenschappen.forEach((kampioenschap) -> {
            dc.addKampioenschap(kampioenschap);
        });

        for (int i = 0; i < ledenLijst.size(); i++) {
            Kampioenschap temp1 = kampioenschappen.get(i % 10);
            Kampioenschap temp2 = kampioenschappen.get((i + 1) % 10);
            if (i % 15 == 0) {
                dc.schrijfLidInVoorActiviteit(temp1.getName(), temp1.getDate(), ledenLijst.get(i).getEmail());
            }
            if (i % 17 == 0) {
                dc.schrijfLidInVoorActiviteit(temp2.getName(), temp2.getDate(), ledenLijst.get(i).getEmail());
            }
        }

        MainPanel root = new MainPanel(dc);

        Scene scene = new Scene(root);

        primaryStage.setTitle("Taijitan Jiu Jitsu");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/images/TaijitanLogo.png"));
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

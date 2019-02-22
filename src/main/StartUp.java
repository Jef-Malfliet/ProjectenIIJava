/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domein.DomeinController;
import domein.Graad;
import domein.Lid;
import gui.MainPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author IndyV
 */
public class StartUp extends Application {

    @Override
    public void start(Stage primaryStage) {

        DomeinController dc = new DomeinController();

        Lid lid0 = new Lid("Nante", "Vermeulen", Graad.GEEL, "123", "nante.email@email.com", "Straatstraat", 9220, "Hamme");
        Lid lid1 = new Lid("Indy", "Van Canegem", Graad.BRUIN, "456", "indy.email@email.com", "Straatstraat", 9220, "Hamme");
        Lid lid2 = new Lid("Jef", "Malfliet", Graad.ORANJE, "789", "jef.email@email.com", "Straatstraat", 9220, "Hamme");
        Lid lid3 = new Lid("Mout", "Pessemier", Graad.WIT, "321", "mout.email@email.com", "Straatstraat", 9220, "Hamme");

        dc.voegLidToe(lid0);
        dc.voegLidToe(lid1);
        dc.voegLidToe(lid2);
        dc.voegLidToe(lid3);
        MainPanel root = new MainPanel(dc);

        Scene scene = new Scene(root, 1440, 900);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

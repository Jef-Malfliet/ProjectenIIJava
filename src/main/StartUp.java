/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domein.DomeinController;
import domein.Graad;
import domein.Lid;
import domein.RolType;
import gui.MainPanel;
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

        Lid lid1 = new Lid("Nante", "Vermeulen", Graad.GROEN, "0472112233", "nante.vermeulen@student.hogent.be", "Straat", 9300, "Landeghem", RolType.LESGEVER);
        Lid lid0 = new Lid("Indy", "Van Canegem", Graad.BRUIN, "0032472112233", "indy.vancanegem@student.hogent.be", "Straat", 9240, "Zele", RolType.LID);
        Lid lid2 = new Lid("Jef", "Malfliet", Graad.ORANJE, "0234567890", "jef.malfliet@student.hogent.be", "Straat", 9220, "Hamme", RolType.BEHEERDER);
        Lid lid3 = new Lid("Mout", "Pessemier", Graad.WIT, "0234567890", "mout.pessemier@student.hogent.be", "Straat", 9320, "Erembodegen", RolType.LID);

        dc.voegLidToe(lid0);
        dc.voegLidToe(lid1);
        dc.voegLidToe(lid2);
        dc.voegLidToe(lid3);
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

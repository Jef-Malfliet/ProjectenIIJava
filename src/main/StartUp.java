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

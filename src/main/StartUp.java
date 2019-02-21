/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domein.DomeinController;
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
        MainPanel root = new MainPanel(dc);
        
        Scene scene = new Scene(root, 1024 , 768);
        
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author IndyV
 */
public class BeheerderControlsController extends VBox {

    @FXML
    private HBox btnLogUit;
    @FXML
    private HBox btnInschrijvenLid;
    @FXML
    private HBox btnBekijkLesMateriaal;
    @FXML
    private HBox btnBeheerLesMateriaal;

   public BeheerderControlsController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BeheerderControls.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
   }
}

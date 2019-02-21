/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Nante
 */
public class DetailPaneelController extends VBox{

     private DomeinController dc;
    
    @FXML
    private TextField txfVoornaam;
    @FXML
    private TextField txfAchternaam;
    @FXML
    private TextField txfGraad;
    @FXML
    private TextField txfStraat;
    @FXML
    private TextField txfPostCode;
    @FXML
    private TextField txfGemeente;
    @FXML
    private TextField txfEmail;
    @FXML
    private TextField txfTelefoonnummer;

    public DetailPaneelController(DomeinController dc) {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("BeheerderControl.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.dc=dc;
        buildGui();
    }

    @FXML
    private void bevestigWijziging(ActionEvent event) {
    }

    @FXML
    private void annuleerwijziging(ActionEvent event) {
    }

    private void buildGui() {
        
    }

   
    
}

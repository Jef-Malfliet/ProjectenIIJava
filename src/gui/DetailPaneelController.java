/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Graad;
import domein.Lid;
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
    private TextField txtVoornaam;
    @FXML
    private TextField txtAchternaam;
    @FXML
    private TextField txtGraad;
    @FXML
    private TextField txtStraat;
    @FXML
    private TextField txtPostCode;
    @FXML
    private TextField txtGemeente;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefoonnummer;

    public DetailPaneelController(DomeinController dc) {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailPaneel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.dc=dc;
        
    }
    
    public void fillLid(Lid lid){
        txtVoornaam.setText(lid.getVoornaam());
        txtGraad.setText(lid.getGraad().name());
    }
    
    
    @FXML
    private void bevestigWijziging(ActionEvent event) {
               
         String voornaam = txtVoornaam.getText();
         String graad = txtGraad.getText();
         Lid lid = new Lid(voornaam, Graad.valueOf(graad));
         dc.wijzigLid(lid);
         
    }

    @FXML
    private void annuleerwijziging(ActionEvent event) {
       txtVoornaam.clear();
       txtGraad.clear();
    }


   
    
}

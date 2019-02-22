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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Nante
 */
public class DetailPaneelController extends VBox {

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
    private Lid current_lid;
    @FXML
    private Label errorMessage;
    @FXML
    private Label lblDetail;
    private boolean nieuwlid;

    public DetailPaneelController(DomeinController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailPaneel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.dc = dc;

    }

    public void fillLid(Lid lid) {
        
        lblDetail.setText("Lid wijzigen");
        current_lid = lid;
        txtVoornaam.setText(lid.getVoornaam());
        txtAchternaam.setText(lid.getAchternaam());
        txtGraad.setText(lid.getGraad().name());
        txtStraat.setText(lid.getStraat());
        txtGemeente.setText(lid.getGemeente());
        txtPostCode.setText(String.format("%s",lid.getPostcode()));
        txtEmail.setText(lid.getEmail());
        txtTelefoonnummer.setText(lid.getTelefoon());
        nieuwlid = false;
    }

    @FXML
    private void bevestigWijziging(ActionEvent event) {

        errorMessage.setVisible(false);

        String voornaam = txtVoornaam.getText();
        String achternaam = txtAchternaam.getText();
        String graad = txtGraad.getText().toUpperCase();
        String telefoon = txtTelefoonnummer.getText();
        String email = txtEmail.getText();
        String straat = txtStraat.getText();
        int postcode = Integer.parseInt(txtPostCode.getText());
        String gemeente = txtGemeente.getText();

        try {
            if (nieuwlid) {
                dc.voegLidToe(new Lid(voornaam, achternaam, Graad.valueOf(graad), telefoon, email, straat, postcode, gemeente));
            } else {
                current_lid.wijzigLid(voornaam, Graad.valueOf(graad));
                dc.wijzigLid(current_lid);

            }
        } catch (IllegalArgumentException e) {
            errorMessage.setText("Deze graad is niet toegelaten");
            errorMessage.setVisible(true);
        }

    }

    @FXML
    private void annuleerwijziging(ActionEvent event) {
        current_lid = null;
        clearTextFields();
    }

    private void clearTextFields() {
        txtVoornaam.clear();
        txtAchternaam.clear();
        txtGraad.clear();
        txtStraat.clear();
        txtPostCode.clear();
        txtGemeente.clear();
        txtEmail.clear();
        txtTelefoonnummer.clear();
        
    }
    
    public void nieuwLid() {
        nieuwlid = true;
        lblDetail.setText("Nieuw Lid toevoegen");
        clearTextFields();
    }

}

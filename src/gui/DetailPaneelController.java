/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Graad;
import domein.Lid;
import domein.RolType;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import util.FullScreenResolution;

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
    private TextField txtStraat;
    @FXML
    private TextField txtPostCode;
    @FXML
    private TextField txtGemeente;
    @FXML
    private TextField txtEmail;
    private Lid current_lid;
    @FXML
    private Label errorMessage;
    @FXML
    private Label lblDetail;
    private boolean nieuwlid;
    @FXML
    private ComboBox<Graad> cboGraad;
    @FXML
    private ComboBox<RolType> cboType;
    @FXML
    private Button btnNieuwLid;
    @FXML
    private Button btnVerwijderLid;

    private OverzichtSceneController osc;
    @FXML
    private TextField txtWachtwoord;
    @FXML
    private TextField txtGsmnummer;
    @FXML
    private TextField txtHuisnummer;
    @FXML
    private TextField txtLand;
    @FXML
    private TextField txtEmail_ouders;
    @FXML
    private TextField txtGeboortedatum;
    @FXML
    private TextField txtInschrijvingsdatum;
    @FXML
    private TextField txtVasteTelefoon;
	private TextField txtGraad;
	@FXML()
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
        this.dc = dc;
        buildGui();
    }

    private void buildGui() {
        cboGraad.setItems(FXCollections.observableArrayList(Arrays.asList(Graad.values())));
        cboType.setItems(FXCollections.observableArrayList(Arrays.asList(RolType.values())));
        nieuwLid();
        btnNieuwLid.setOnMouseClicked(e -> {
            nieuwLid();
        });

        btnVerwijderLid.setOnMouseClicked(e -> {
            osc.verwijdergeselecteerdLid();
        });
    }

    public void fillLid(Lid lid) {
        errorMessage.setVisible(false);
        lblDetail.setText("Lid wijzigen");
        current_lid = lid;
        txtVoornaam.setText(lid.getVoornaam());
        txtAchternaam.setText(lid.getFamilienaam());
        txtWachtwoord.setText(lid.getWachtwoord());
       // txtGsmnummer.setText(lid.getG);
        txtVasteTelefoon.setText(lid.getTelefoon_vast());
        txtStraat.setText(lid.getStraatnaam());
        txtHuisnummer.setText(String.format("%s",lid.getHuisnummer()));
        txtGemeente.setText(lid.getStad());
        txtPostCode.setText(String.format("%s", lid.getPostcode()));
        txtLand.setText(lid.getLand());
        txtEmail.setText(lid.getEmail());
        txtGeboortedatum.setText(lid.getGeboortedatum().toString());
        txtInschrijvingsdatum.setText(lid.getInschrijvingsdatum().toString());
        txtEmail_ouders.setText(lid.getEmail_ouders());
        cboGraad.getSelectionModel().select(lid.getGraad());
        cboType.getSelectionModel().select(lid.getType());
        nieuwlid = false;
        
        
    }

    @FXML
    private void bevestigWijziging(ActionEvent event) {

        errorMessage.setVisible(false);

        String voornaam = txtVoornaam.getText();
        String achternaam = txtAchternaam.getText();
        Graad graad = cboGraad.getSelectionModel().getSelectedItem();
        String telefoon = txtVasteTelefoon.getText();
        String email = txtEmail.getText();
        String straat = txtStraat.getText();
        String gemeente = txtGemeente.getText();
        RolType type = cboType.getSelectionModel().getSelectedItem();
        
        if (voornaam == null || voornaam.isEmpty() || achternaam == null || achternaam.isEmpty() || graad == null
                || telefoon == null || telefoon.isEmpty() || email == null || email.isEmpty() || straat == null
                || straat.isEmpty() || gemeente == null || gemeente.isEmpty() ||type == null ) {
            errorMessage.setText("Sommige gegevens ontbreken");
            errorMessage.setVisible(true);

        } else {
            try {
                    if (!txtPostCode.getText().matches("[1-9][0-9]{3}")) {
                        errorMessage.setText("Geen geldige postcode");
                        errorMessage.setVisible(true);
                    } else {

                        int postcode = Integer.parseInt(txtPostCode.getText());
                        if (nieuwlid) {
                            dc.voegLidToe(new Lid(voornaam, achternaam, graad, telefoon, email, straat, postcode, gemeente, type));
                        } else {
                            current_lid.wijzigLid(voornaam, achternaam,graad, telefoon, email, straat, postcode, gemeente,type);
                            dc.wijzigLid(current_lid);

                        }
                        errorMessage.setText("Wijzigingen worden opgeslaan");
                        errorMessage.setVisible(true);

                    }

                
            } catch (IllegalArgumentException e) {
                errorMessage.setText(e.getMessage());
                errorMessage.setVisible(true);
            }
        }
    }

    @FXML
    private void annuleerwijziging(ActionEvent event) {
        current_lid = null;
        nieuwLid();
    }

    public void clearTextFields() {
        errorMessage.setVisible(false);
        txtVoornaam.clear();
        txtAchternaam.clear();
        cboGraad.getSelectionModel().clearSelection();
        txtStraat.clear();
        txtPostCode.clear();
        txtGemeente.clear();
        txtEmail.clear();
        txtGsmnummer.clear();
        cboType.getSelectionModel().clearSelection();

    }

    public void nieuwLid() {
        nieuwlid = true;
        lblDetail.setText("Nieuw Lid toevoegen");
        clearTextFields();
    }

    public void setOverzichtSceneController(OverzichtSceneController osc) {
        this.osc = osc;
    }

}

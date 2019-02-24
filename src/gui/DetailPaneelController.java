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
    @FXML
    private ComboBox<Graad> cboGraad;
        @FXML
    private ComboBox<RolType> cboType;
    @FXML
    private Button btnNieuwLid;
    @FXML
    private Button btnVerwijderLid;

    private OverzichtSceneController osc;


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
        txtAchternaam.setText(lid.getAchternaam());
        cboGraad.getSelectionModel().select(lid.getGraad());
        txtStraat.setText(lid.getStraat());
        txtGemeente.setText(lid.getGemeente());
        txtPostCode.setText(String.format("%s", lid.getPostcode()));
        txtEmail.setText(lid.getEmail());
        txtTelefoonnummer.setText(lid.getTelefoon());
        nieuwlid = false;
    }

    @FXML
    private void bevestigWijziging(ActionEvent event) {

        errorMessage.setVisible(false);

        String voornaam = txtVoornaam.getText();
        String achternaam = txtAchternaam.getText();
        String graad = cboGraad.getSelectionModel().getSelectedItem().toString();
        String telefoon = txtTelefoonnummer.getText();
        String email = txtEmail.getText();
        String straat = txtStraat.getText();
        String gemeente = txtGemeente.getText();
        String type = cboType.getSelectionModel().getSelectedItem().toString();
        if (voornaam == null || voornaam.isEmpty() || achternaam == null || achternaam.isEmpty() || graad == null || graad.isEmpty()
                || telefoon == null || telefoon.isEmpty() || email == null || email.isEmpty() || straat == null
                || straat.isEmpty() || gemeente == null || gemeente.isEmpty()) {
            errorMessage.setText("Sommige gegevens ontbreken");
            errorMessage.setVisible(true);

        } else {
            try {
                if (Arrays.stream(Graad.values()).noneMatch(g -> graad.toUpperCase().equals(g.toString().toUpperCase()))) {
                    errorMessage.setText("Deze graad is niet toegelaten");
                    errorMessage.setVisible(true);
                } else {
                    if (!txtPostCode.getText().matches("[1-9][0-9]{3}")) {
                        errorMessage.setText("Geen geldige postcode");
                        errorMessage.setVisible(true);
                    } else {

                        int postcode = Integer.parseInt(txtPostCode.getText());
                        if (nieuwlid) {
                            dc.voegLidToe(new Lid(voornaam, achternaam, Graad.valueOf(graad), telefoon, email, straat, postcode, gemeente, RolType.valueOf(type)));
                        } else {
                            current_lid.wijzigLid(voornaam, achternaam, Graad.valueOf(graad), telefoon, email, straat, postcode, gemeente,RolType.valueOf(type));
                            dc.wijzigLid(current_lid);

                        }
                        errorMessage.setText("Wijzigingen worden opgeslaan");
                        errorMessage.setVisible(true);

                    }

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
        clearTextFields();
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
        txtTelefoonnummer.clear();

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

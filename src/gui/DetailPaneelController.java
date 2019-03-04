/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Geslacht;
import domein.Graad;
import domein.ILid;
import domein.Lid;
import domein.RolType;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    private ILid current_lid;
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
    private TextField txtVasteTelefoon;
    @FXML
    private Label lblM_Voornaam;
    @FXML
    private Label lblM_Familienaam;
    @FXML
    private Label lblM_Wachtwoord;
    @FXML
    private Label lblM_Gsmnummer;
    @FXML
    private Label lblM_VasteTelefoon;
    @FXML
    private Label lblM_Straatnaam;
    @FXML
    private Label lblM_Huisnummer;
    @FXML
    private Label lblM_Postcode;
    @FXML
    private Label lblM_Stad;
    @FXML
    private Label lblM_Land;
    @FXML
    private Label lblM_Email;
    @FXML
    private Label lblM_Emailouder;
    @FXML
    private Label lblM_Geboortedatum;
    @FXML
    private Label lblM_Inschrijvingsdatum;
    @FXML
    private TextField txtBusnummer;
    @FXML
    private Label lblM_Busnummer;
    @FXML
    private DatePicker dpGeboorte;
    @FXML
    private DatePicker dpInschrijving;
    private List<TextField> niet_verplicht = new ArrayList<>();
    @FXML
    private TextField txtBusnummer1;
    @FXML
    private Label lblM_Lessen;
    @FXML
    private ComboBox<Geslacht> cboGeslacht;
    private boolean veldenCompleet;

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
        cboGeslacht.setItems(FXCollections.observableArrayList(Arrays.asList(Geslacht.values())));
        nieuwLid();
        btnNieuwLid.setOnMouseClicked(e -> {
            nieuwLid();
        });

        btnVerwijderLid.setOnMouseClicked(e -> {
            osc.verwijdergeselecteerdLid();
        });
        niet_verplicht.add(txtVasteTelefoon);
        niet_verplicht.add(txtEmail_ouders);
        niet_verplicht.add(txtBusnummer);
        dpGeboorte.setEditable(false);
        dpInschrijving.setEditable(false);
    }

    private void opvullen(ILid lid) {
        
        lblDetail.setText("Lid wijzigen");
        current_lid = lid;
        txtVoornaam.setText(lid.getVoornaam());
        txtAchternaam.setText(lid.getFamilienaam());
        txtWachtwoord.setText(lid.getWachtwoord());
        txtGsmnummer.setText(lid.getGsm());
        txtVasteTelefoon.setText(lid.getTelefoon_vast());
        txtStraat.setText(lid.getStraatnaam());
        txtHuisnummer.setText(lid.getHuisnummer());
        txtBusnummer.setText(lid.getBusnummer());
        txtGemeente.setText(lid.getStad());
        txtPostCode.setText(lid.getPostcode());
        txtLand.setText(lid.getLand());
        txtEmail.setText(lid.getEmail());
        dpGeboorte.setValue(lid.getGeboortedatum());
        dpInschrijving.setValue(lid.getInschrijvingsdatum());
        txtEmail_ouders.setText(lid.getEmail_ouders());
        cboGraad.getSelectionModel().select(lid.getGraad());
        cboType.getSelectionModel().select(lid.getType());
        cboGeslacht.getSelectionModel().select(lid.getGeslacht());
        nieuwlid = false;
    }

    public void fillLid(ILid lid) {

        if (clearTextFields()) {
            errorMessage.setVisible(false);
            opvullen(lid);
        }

    }

    private boolean moetIngevuldzijn(TextField textfield) {
        if (niet_verplicht.contains(textfield)) {
            return false;
        }
        if (textfield == null || textfield.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    @FXML
    private void bevestigWijziging(ActionEvent event) {
        makeElementsWhiteLabelsInvisible();
        veldenCompleet = true;
        valideerVelden();
        if (veldenCompleet) {
            if (nieuwlid) {
                maaknieuwlid();
            } else {
                if (isgewijzigd()) {
                    wijziglid();
                } else {
                    errorMessage.setText("Er zijn geen wijzigingen");
                    errorMessage.setVisible(true);
                }

            }
        } else {
            errorMessage.setText("Niet alle velden zijn correct");
            errorMessage.setVisible(true);
        }
    }

    private void valideerVelden() {
        TextField[] textfields = geefTextfields();
        Label[] errormessages = geefLabels();

        errorMessage.setVisible(false);
        for (int i = 0; i < textfields.length; i++) {
            if (moetIngevuldzijn(textfields[i])) {
                errorOn(errormessages[i], textfields[i], "Gelieve in te vullen");
            }
        }
        if (dpGeboorte.getValue() == null) {
            errorOn(lblM_Geboortedatum, null, "Gelieve een datum in te vullen");

        }
        if (dpInschrijving.getValue() == null) {
            errorOn(lblM_Inschrijvingsdatum, null, "Gelieve een datum in te vullen");
        }

        if (!(txtVasteTelefoon.getText().matches("") || txtVasteTelefoon.getText().matches("/") || txtVasteTelefoon.getText().matches("0\\d{8}") || txtVasteTelefoon.getText().matches("00\\d{10}"))) {
            errorOn(lblM_VasteTelefoon, txtVasteTelefoon, "Gelieve een geldig telefoonnr op te geven");
        }
        if (!(txtGsmnummer.getText().matches("0\\d{9}") || txtGsmnummer.getText().matches("00\\d{11}"))) {
            errorOn(lblM_Gsmnummer, txtGsmnummer, "Gelieve een geldig telefoonnr op te geven");
        }
        if (!txtPostCode.getText().matches("[1-9][0-9]{3}")) {
            errorOn(lblM_Postcode, txtPostCode, "Gelieve een geldige postcode op te geven");
        }
        if (!txtEmail.getText().matches("^([a-zA-Z0-9éèà]+[a-zA-Z0-9.-]*)@([a-zA-Z]+)[.]([a-z]+)([.][a-z]+)*$")) {
            errorOn(lblM_Email, txtEmail, "Geen geldig emailadres");
        }
        if (!(txtEmail_ouders.getText().matches("") || txtEmail_ouders.getText().matches("/") ||txtEmail_ouders.getText().matches("^([a-zA-Z0-9éèà]+[a-zA-Z0-9.-]*)@([a-zA-Z]+)[.]([a-z]+)([.][a-z]+)*$"))) {
            errorOn(lblM_Emailouder, txtEmail_ouders, "Geen geldig emailadres");
        }
        if (!txtHuisnummer.getText().matches("[0-9]*[a-zA-Z]*")) {
            errorOn(lblM_Huisnummer, txtHuisnummer, "Geen geldig huisnummer");
        }
    }

    private void maaknieuwlid() {
        current_lid = new Lid(txtVoornaam.getText(), txtAchternaam.getText(), txtWachtwoord.getText(), txtGsmnummer.getText(), txtVasteTelefoon.getText().isEmpty() ? "/" : txtVasteTelefoon.getText(),
                txtStraat.getText(), txtHuisnummer.getText(), txtBusnummer.getText().isEmpty() ? "/" : txtBusnummer.getText(), txtPostCode.getText(), txtGemeente.getText(),
                txtLand.getText(), txtEmail.getText(), txtEmail_ouders.getText().isEmpty() ? "/" : txtEmail_ouders.getText(), dpGeboorte.getValue(), dpInschrijving.getValue(), new ArrayList<>(),
                cboGeslacht.getSelectionModel().getSelectedItem(), cboGraad.getSelectionModel().getSelectedItem(), cboType.getSelectionModel().getSelectedItem());
        dc.voegLidToe(current_lid);
        errorMessage.setText("Lid werd toegevoegd");
        errorMessage.setVisible(true);

    }

    private void wijziglid() {
        dc.wijzigLid(current_lid.getId(),txtVoornaam.getText(), txtAchternaam.getText(), txtWachtwoord.getText(), txtGsmnummer.getText(), txtVasteTelefoon.getText().isEmpty() ? "/" : txtVasteTelefoon.getText(),
                txtStraat.getText(), txtHuisnummer.getText(), txtBusnummer.getText().isEmpty() ? "/" : txtBusnummer.getText(), txtPostCode.getText(), txtGemeente.getText(),
                txtLand.getText(), txtEmail.getText(), txtEmail_ouders.getText().isEmpty() ? "/" : txtEmail_ouders.getText(), dpGeboorte.getValue(), dpInschrijving.getValue(), new ArrayList<>(),
                cboGeslacht.getSelectionModel().getSelectedItem(), cboGraad.getSelectionModel().getSelectedItem(), cboType.getSelectionModel().getSelectedItem());
        
       
        errorMessage.setText("Wijzigingen zijn opgeslagen");
        errorMessage.setVisible(true);
        opvullen(current_lid);

    }

    @FXML
    private void annuleerwijziging(ActionEvent event
    ) {
        if (clearTextFields()) {
            current_lid = null;
            nieuwLid();
        }

    }

    private boolean alertNaWijzigen() {
        if (isgewijzigd()) {
            Alert a = new Alert(AlertType.CONFIRMATION);
            a.setTitle("OPGELET");
            a.setHeaderText("OPGELET");
            a.setContentText("Bent u zeker dat u de wijzigingen niet wil opslaan?");
            ButtonType tochOpslaan = new ButtonType("Toch Opslaan");
            ButtonType wijzigingenVerwijderen = new ButtonType("Wijzigingen Verwijderen");
            a.getButtonTypes().clear();

            a.getButtonTypes().addAll(tochOpslaan, wijzigingenVerwijderen);
            Optional<ButtonType> showAndWait = a.showAndWait();
            if (showAndWait.isPresent()) {
                return showAndWait.get() == tochOpslaan;
            }

        }
        return false;
    }

    private boolean clearTextFields() {
        boolean save = false;
        if (current_lid == null ? true : !(save = alertNaWijzigen())) {
            makeElementsWhiteLabelsInvisible();
            TextField[] textfields
                    = geefTextfields();
            Arrays.stream(textfields).forEach(txt -> {
                txt.clear();
            });
            cboType.getSelectionModel().selectFirst();
            cboGraad.getSelectionModel().selectFirst();
            cboGeslacht.getSelectionModel().selectFirst();
            dpGeboorte.setValue(LocalDate.of(2000, Month.JANUARY, 1));
            dpInschrijving.setValue(LocalDate.now());
            return true;

        }
        if (save) {
            bevestigWijziging(new ActionEvent());
            return false;
        }
        return true;

    }

    private void makeElementsWhiteLabelsInvisible() {
        TextField[] textfields = geefTextfields();
        Label[] errormessages = geefLabels();
        String whitestyle = "-fx-background-color:#ffffff";
        errorMessage.setVisible(false);

        Arrays.stream(textfields).forEach(txt -> {
            txt.setStyle(whitestyle);
        });
        Arrays.stream(errormessages).forEach(label -> {
            label.setVisible(false);
        });

        cboType.setStyle(whitestyle);
        cboGraad.setStyle(whitestyle);
        cboGeslacht.setStyle(whitestyle);

    }

    public void nieuwLid() {
        if (clearTextFields()) {
            nieuwlid = true;
            lblDetail.setText("Nieuw Lid toevoegen");
        }

    }

    public void setOverzichtSceneController(OverzichtSceneController osc
    ) {
        this.osc = osc;

    }

    private TextField[] geefTextfields() {
        TextField[] textfields = {txtVoornaam, txtAchternaam, txtWachtwoord, txtVasteTelefoon, txtStraat, txtHuisnummer, txtBusnummer, txtPostCode, txtGemeente, txtLand, txtEmail, txtEmail_ouders, txtGsmnummer};
        return textfields;

    }

    private Label[] geefLabels() {
        Label[] errormessages = {lblM_Voornaam, lblM_Familienaam, lblM_Wachtwoord, lblM_VasteTelefoon, lblM_Straatnaam, lblM_Huisnummer, lblM_Busnummer, lblM_Postcode, lblM_Stad, lblM_Land, lblM_Email, lblM_Emailouder, lblM_Gsmnummer, lblM_Inschrijvingsdatum, lblM_Geboortedatum};
        return errormessages;

    }

    private boolean isgewijzigd() {
        if (current_lid == null) {
            return false;
        }

        boolean wijzig = current_lid.getVoornaam().equals(txtVoornaam.getText()) && current_lid.getFamilienaam().equals(txtAchternaam.getText())
                && current_lid.getWachtwoord().equals(txtWachtwoord.getText()) && current_lid.getGsm().equals(txtGsmnummer.getText())
                && current_lid.getTelefoon_vast().equals(txtVasteTelefoon.getText()) && current_lid.getStraatnaam().equals(txtStraat.getText())
                && current_lid.getHuisnummer().equals(txtHuisnummer.getText()) && current_lid.getBusnummer().equals(txtBusnummer.getText())
                && current_lid.getPostcode().equals(txtPostCode.getText()) && current_lid.getStad().equals(txtGemeente.getText())
                && current_lid.getLand().equals(txtLand.getText()) && current_lid.getEmail().equals(txtEmail.getText())
                && current_lid.getEmail_ouders().equals(txtEmail_ouders.getText()) && current_lid.getGraad().equals(cboGraad.getSelectionModel().getSelectedItem())
                && current_lid.getType().equals(cboType.getSelectionModel().getSelectedItem()) && current_lid.getGeslacht().equals(cboGeslacht.getSelectionModel().getSelectedItem())
                && current_lid.getInschrijvingsdatum().equals(dpInschrijving.getValue())
                && current_lid.getGeboortedatum().equals(dpGeboorte.getValue());

        return !wijzig;
    }

    public void clearNaVerwijderen() {
        current_lid = null;
        if (clearTextFields()) {
            errorMessage.setText("Lid werd verwijderd");
            errorMessage.setVisible(true);
        }
    }

    private void errorOn(Label lbl, TextField txt, String errormessage) {
        veldenCompleet = false;
        String errorstyle = "-fx-text-box-border: #f77d5b ;-fx-focus-color: #f77d5b;";
        if (lbl != null) {
            lbl.setText(errormessage);
            lbl.setVisible(true);
        }
        if (txt != null) {
            txt.setStyle(errorstyle);
        }

    }

}

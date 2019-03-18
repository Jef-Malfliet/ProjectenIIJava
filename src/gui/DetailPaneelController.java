/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DTOLid;
import domein.DomeinController;
import domein.Geslacht;
import domein.Graad;
import domein.ILid;
import domein.Land;
import domein.LesType;
import domein.Postcode;
import domein.RolType;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
import persistentie.PostcodeMapper;
import static util.Validatie.*;

/**
 * FXML Controller class
 *
 * @author Nante
 */
public class DetailPaneelController extends VBox implements PropertyChangeListener {

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

    @FXML
    private TextField txtWachtwoord;
    @FXML
    private TextField txtGsmnummer;
    @FXML
    private TextField txtHuisnummer;
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
    private Label lblM_Lessen;
    @FXML
    private ComboBox<Geslacht> cboGeslacht;
    private boolean veldenCompleet;
    @FXML
    private ComboBox<LesType> cboLesType;
    //private TextField txtRijksregisternummer;
    @FXML
    private Label lblM_Rijkregisternummer;
    private ComboBox<String> cboGemeentes = new ComboBox();
    private boolean comboGemeentes = true;
    @FXML
    private ComboBox<Land> cboLand;
    @FXML
    private TextField txtRijksregisternummer0;
    @FXML
    private TextField txtRijksregisternummer1;
    @FXML
    private TextField txtRijksregisternummer2;
    @FXML
    private TextField txtRijksregisternummer3;
    @FXML
    private TextField txtRijksregisternummer4;
    @FXML
    private Button btnNieuwNIETLID;

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
        vulComboboxen();
        nieuwLidPaneel();
        btnNieuwLid.setOnMouseClicked(e -> {
            nieuwLidPaneel();
        });
        
      
        btnVerwijderLid.setOnMouseClicked(e -> {
            if (alertVerwijderenLid()) {
                dc.verwijderCurrentLid();
            }

        });

        txtPostCode.setOnKeyReleased(e -> {
            txtPostCode.commitValue();
            controleerOpgemeentes();
        });
        voegNietVerplichteVeldenToe();
        dpGeboorte.setEditable(false);
        dpInschrijving.setEditable(false);
        cboGemeentes.setOnAction(e -> {
            changeGemeenteTextField();
        });
        cboLand.setOnAction(e -> {
            comboGemeentes = !cboLand.getSelectionModel().getSelectedItem().equals(Land.Anders);
            controleerOpgemeentes();
        });
//        Button b = new Button();
//        b.setText("USE CBO");
//        b.setOnAction(e -> {
//            comboGemeentes = !comboGemeentes;
//            controleerOpgemeentes();
//        });
        //((HBox) txtGemeente.getParent()).getChildren().add(2, b);
    }

    private void vulComboboxen() {
        List<Graad> graden = Arrays.asList(Graad.values());
        List<RolType> roltypes = Arrays.asList(RolType.values());
        List<LesType> lestypes = Arrays.asList(LesType.values());
        cboGraad.setItems(FXCollections.observableArrayList(removeLastItem(graden)));
        cboType.setItems(FXCollections.observableArrayList(removeLastItem(roltypes)));
        cboLesType.setItems(FXCollections.observableArrayList(removeLastItem(lestypes)));
        cboLand.setItems(FXCollections.observableArrayList(Arrays.asList(Land.values())));
        cboGeslacht.setItems(FXCollections.observableArrayList(Arrays.asList(Geslacht.values())));

    }
    
    private <T> List<T> removeLastItem (List<T> lijst){
        return lijst.subList(0, lijst.size()-1);
    }

    private void changeGemeenteTextField() {
        String selectedItem = cboGemeentes.getSelectionModel().getSelectedItem();
        if (!isNullOrEmpty(selectedItem)) {
            txtGemeente.setText(selectedItem);
        }
    }

    private void controleerOpgemeentes() {
        HBox hbox = (HBox) txtGemeente.getParent();
        if (hbox == null) {
            hbox = (HBox) cboGemeentes.getParent();
        }
        if (comboGemeentes) {

            cboGemeentes.setMaxWidth(USE_PREF_SIZE);
            Postcode postcode = PostcodeMapper.getGemeentesBelgië(txtPostCode.getText(), cboLand.getSelectionModel().getSelectedItem());
            if (isNull(postcode)) {
                errorOn(lblM_Postcode, txtPostCode, "Postcode bestaat niet");
            } else {
                if (postcode.getLand().equals(cboLand.getSelectionModel().getSelectedItem())) {
                    errorOf(lblM_Postcode, txtPostCode);
                    if (!isNull(postcode)) {
                        if (hbox.getChildren().contains(txtGemeente)) {
                            hbox.getChildren().remove(txtGemeente);
                            hbox.getChildren().add(1, cboGemeentes);
                        }
                        cboGemeentes.setItems(FXCollections.observableArrayList(postcode.getGemeentes()));
                        String gemeente = txtGemeente.getText();
                        if (postcode.getGemeentes().indexOf(gemeente) != -1) {
                            cboGemeentes.getSelectionModel().select(gemeente);
                        } else {
                            cboGemeentes.getSelectionModel().selectFirst();
                        }

                    }
                }
            }
        } else {
            errorOf(lblM_Postcode, txtPostCode);
            if (hbox.getChildren().contains(cboGemeentes)) {
                hbox.getChildren().remove(cboGemeentes);
                hbox.getChildren().add(1, txtGemeente);
            }
        }

    }

    private void voegNietVerplichteVeldenToe() {
        niet_verplicht.add(txtVasteTelefoon);
        niet_verplicht.add(txtEmail_ouders);
        niet_verplicht.add(txtBusnummer);
    }

    private void opvullenVanFields(ILid lid) {
        lblDetail.setText("Lid wijzigen");
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
        cboLand.getSelectionModel().select(lid.getLand());
        vulRijksregisternummer(lid.getRijksregisternummer());
        txtEmail.setText(lid.getEmail());
        dpGeboorte.setValue(lid.getGeboortedatum());
        dpInschrijving.setValue(lid.getInschrijvingsdatum());
        txtEmail_ouders.setText(lid.getEmail_ouders());
        cboGraad.getSelectionModel().select(lid.getGraad());
        cboType.getSelectionModel().select(lid.getType());
        cboGeslacht.getSelectionModel().select(lid.getGeslacht());
        cboLesType.getSelectionModel().select(lid.getLessen());
        nieuwlid = false;
    }

    private boolean fillLid(ILid lid) {

        if (clearTextFields()) {
            errorMessage.setVisible(false);
            opvullenVanFields(lid);
            controleerOpgemeentes();
            return true;
        }
        return false;

    }

    private boolean moetIngevuldzijn(TextField textfield) {
        if (niet_verplicht.contains(textfield)) {
            return false;
        }
        return textfield == null || textfield.getText().isEmpty();
    }

    @FXML
    private void bevestigWijziging(ActionEvent event) {
        makeElementsWhiteLabelsInvisible();
        veldenCompleet = true;
        controleerOpgemeentes();
        valideerVelden();
        if (veldenCompleet) {
            if (nieuwlid) {
                maaknieuwlid();
                dc.verwijderSelectieLid();

                clearTextFields();
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
        if (isNull(dpGeboorte.getValue())) {
            errorOn(lblM_Geboortedatum, null, "Gelieve een datum in te vullen");

        }
        if (isNull(dpInschrijving.getValue())) {
            errorOn(lblM_Inschrijvingsdatum, null, "Gelieve een datum in te vullen");
        }

        if (!rijksregisternummerIsCorrect(geefRijksregisternummer())) {
            errorOn(lblM_Rijkregisternummer, null, "Geen geldig rijkregisternummer");
        }
        if (!(isLeeg(txtVasteTelefoon.getText()) || isValidLeeg(txtVasteTelefoon.getText()) || isVasteTelefoonNummer(txtVasteTelefoon.getText()))) {
            errorOn(lblM_VasteTelefoon, txtVasteTelefoon, "Gelieve een geldig telefoonnr op te geven");
        }
        if (!(isGsmNummer(txtGsmnummer.getText()))) {
            errorOn(lblM_Gsmnummer, txtGsmnummer, "Gelieve een geldig telefoonnr op te geven");
        }
        if (!isPostcode(txtPostCode.getText())) {
            errorOn(lblM_Postcode, txtPostCode, "Gelieve een geldige postcode op te geven");
        }
        if (!isGeldigEmailAdres(txtEmail.getText())) {
            errorOn(lblM_Email, txtEmail, "Geen geldig emailadres");
        }
        if (!(isLeeg(txtEmail_ouders.getText()) || isValidLeeg(txtEmail_ouders.getText()) || isGeldigEmailAdres(txtEmail_ouders.getText()))) {
            errorOn(lblM_Emailouder, txtEmail_ouders, "Geen geldig emailadres");
        }
        if (!isHuisnummer(txtHuisnummer.getText())) {
            errorOn(lblM_Huisnummer, txtHuisnummer, "Geen geldig huisnummer");
        }
        if (isInFuture(dpGeboorte.getValue())) {
            errorOn(lblM_Geboortedatum, null, "Geboortedatum ligt in de toekomst");
        }
    }

    private void maaknieuwlid() {
        DTOLid dto = new DTOLid(txtVoornaam.getText(), txtAchternaam.getText(), txtWachtwoord.getText(), txtGsmnummer.getText(), txtVasteTelefoon.getText().isEmpty() ? "/" : txtVasteTelefoon.getText(),
                txtStraat.getText(), txtHuisnummer.getText(), txtBusnummer.getText().isEmpty() ? "/" : txtBusnummer.getText(), txtPostCode.getText(), txtGemeente.getText(),
                cboLand.getSelectionModel().getSelectedItem(), geefRijksregisternummer(), txtEmail.getText(), txtEmail_ouders.getText().isEmpty() ? "/" : txtEmail_ouders.getText(), dpGeboorte.getValue(), dpInschrijving.getValue(), new ArrayList<>(),
                cboGeslacht.getSelectionModel().getSelectedItem(), cboGraad.getSelectionModel().getSelectedItem(), cboType.getSelectionModel().getSelectedItem(), cboLesType.getSelectionModel().getSelectedItem());

        dc.voegLidToe(dto);
        errorMessage.setText("Lid werd toegevoegd");
        errorMessage.setVisible(true);
    }

    private void wijziglid() {
        dc.wijzigLid(txtVoornaam.getText(), txtAchternaam.getText(), txtWachtwoord.getText(), txtGsmnummer.getText(), txtVasteTelefoon.getText().isEmpty() ? "/" : txtVasteTelefoon.getText(),
                txtStraat.getText(), txtHuisnummer.getText(), txtBusnummer.getText().isEmpty() ? "/" : txtBusnummer.getText(), txtPostCode.getText(), txtGemeente.getText(),
                cboLand.getSelectionModel().getSelectedItem(), geefRijksregisternummer(), txtEmail.getText(), txtEmail_ouders.getText().isEmpty() ? "/" : txtEmail_ouders.getText(), dpGeboorte.getValue(), dpInschrijving.getValue(), new ArrayList<>(),
                cboGeslacht.getSelectionModel().getSelectedItem(), cboGraad.getSelectionModel().getSelectedItem(), cboType.getSelectionModel().getSelectedItem(), cboLesType.getSelectionModel().getSelectedItem());

        errorMessage.setText("Wijzigingen zijn opgeslagen");
        errorMessage.setVisible(true);
        opvullenVanFields(dc.getCurrentLid());

    }

    @FXML
    private void annuleerwijziging(ActionEvent event) {
        if (clearTextFields()) {
            dc.verwijderSelectieLid();
            nieuwLidPaneel();
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
        if (dc.geenLidGeslecteerd() ? true : !(save = alertNaWijzigen())) {
            makeElementsWhiteLabelsInvisible();
            TextField[] textfields
                    = geefTextfields();
            Arrays.stream(textfields).forEach(txt -> {
                txt.clear();
            });
            cboType.getSelectionModel().selectFirst();
            cboGraad.getSelectionModel().selectFirst();
            cboGeslacht.getSelectionModel().selectFirst();
            cboLesType.getSelectionModel().selectFirst();
            cboLand.getSelectionModel().selectFirst();
            dpGeboorte.setValue(LocalDate.of(2000, Month.JANUARY, 1));
            dpInschrijving.setValue(LocalDate.now());
            comboGemeentes = false;
            controleerOpgemeentes();
            cboGemeentes.getSelectionModel().clearSelection();
            cboGemeentes.setPrefWidth(231);
            cboGemeentes.setPrefHeight(28);
            comboGemeentes = true;
            return true;

        }
        if (save) {
            bevestigWijziging(new ActionEvent());
            return false;
        }
        return true;

    }

    private void errorOf(Label lbl, TextField txt) {
        veldenCompleet = true;
        String whitestyle = "-fx-background-color:#ffffff";
        if (lbl != null) {
            lbl.setVisible(false);
        }
        if (txt != null) {
            txt.setStyle(whitestyle);
        }
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
        cboLesType.setStyle(whitestyle);
        cboGemeentes.setStyle(whitestyle);
        cboLand.setStyle(whitestyle);

    }

    public void nieuwLidPaneel() {
        if (clearTextFields()) {
            nieuwlid = true;
            lblDetail.setText("Nieuw Lid toevoegen");
        }

    }


    private TextField[] geefTextfields() {
        TextField[] textfields = {txtVoornaam, txtAchternaam, txtWachtwoord, txtVasteTelefoon, txtStraat, txtHuisnummer, txtBusnummer, txtPostCode, txtGemeente, txtEmail, txtEmail_ouders, txtGsmnummer, txtRijksregisternummer0, txtRijksregisternummer1, txtRijksregisternummer2, txtRijksregisternummer3, txtRijksregisternummer3, txtRijksregisternummer4};
        return textfields;

    }

    private Label[] geefLabels() {
        Label[] errormessages = {lblM_Voornaam, lblM_Familienaam, lblM_Wachtwoord, lblM_VasteTelefoon, lblM_Straatnaam, lblM_Huisnummer, lblM_Busnummer, lblM_Postcode, lblM_Stad, lblM_Email, lblM_Emailouder, lblM_Gsmnummer, lblM_Inschrijvingsdatum, lblM_Geboortedatum, lblM_Rijkregisternummer};
        return errormessages;

    }

    private boolean isgewijzigd() {
        ILid current_lid = dc.getCurrentLid();
        if (dc.geenLidGeslecteerd()) {
            return false;
        }

        boolean wijzig = current_lid.getVoornaam().equals(txtVoornaam.getText()) && current_lid.getFamilienaam().equals(txtAchternaam.getText())
                && current_lid.getWachtwoord().equals(txtWachtwoord.getText()) && current_lid.getGsm().equals(txtGsmnummer.getText())
                && current_lid.getTelefoon_vast().equals(txtVasteTelefoon.getText()) && current_lid.getStraatnaam().equals(txtStraat.getText())
                && current_lid.getHuisnummer().equals(txtHuisnummer.getText()) && current_lid.getBusnummer().equals(txtBusnummer.getText())
                && current_lid.getPostcode().equals(txtPostCode.getText()) && current_lid.getStad().equals(txtGemeente.getText())
                && current_lid.getLand().equals(cboLand.getSelectionModel().getSelectedItem()) && current_lid.getRijksregisternummer().equals(geefRijksregisternummer()) && current_lid.getEmail().equals(txtEmail.getText())
                && current_lid.getEmail_ouders().equals(txtEmail_ouders.getText()) && current_lid.getGraad().equals(cboGraad.getSelectionModel().getSelectedItem())
                && current_lid.getType().equals(cboType.getSelectionModel().getSelectedItem()) && current_lid.getGeslacht().equals(cboGeslacht.getSelectionModel().getSelectedItem())
                && current_lid.getInschrijvingsdatum().equals(dpInschrijving.getValue())
                && current_lid.getGeboortedatum().equals(dpGeboorte.getValue()) && current_lid.getLessen().equals(cboLesType.getSelectionModel().getSelectedItem());

        return !wijzig;
    }

    public void clearNaVerwijderen() {
        dc.verwijderSelectieLid();
        if (clearTextFields()) {
            errorMessage.setText("Lid werd verwijderd");
            errorMessage.setVisible(true);
            nieuwLidPaneel();
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ILid lid = (ILid) evt.getNewValue();
        if (lid != null) {
            fillLid(lid);
        }
    }

    private void vulRijksregisternummer(String rijksregisternummer) {
        String[] split = rijksregisternummer.split("");
        txtRijksregisternummer0.setText(split[0] + split[1]);
        txtRijksregisternummer1.setText(split[3] + split[4]);
        txtRijksregisternummer2.setText(split[6] + split[7]);
        txtRijksregisternummer3.setText(split[9] + split[10] + split[11]);
        txtRijksregisternummer4.setText(split[13] + split[14]);

    }

    private String geefRijksregisternummer() {
        return String.format("%s.%s.%s-%s.%s", txtRijksregisternummer0.getText(), txtRijksregisternummer1.getText(), txtRijksregisternummer2.getText(), txtRijksregisternummer3.getText(), txtRijksregisternummer4.getText());

    }

    private boolean alertVerwijderenLid() {
       ILid currentLid = dc.getCurrentLid();
       Alert a = new Alert(AlertType.CONFIRMATION);
            a.setTitle("OPGELET");
            a.setHeaderText("OPGELET");
            a.setContentText(String.format("Bent u zeker dat u het lid %s wil verwijderen?",currentLid.getVoornaam() + currentLid.getFamilienaam()));
            ButtonType verwijder = new ButtonType("Verwijder");
            ButtonType annuleer = new ButtonType("Annuleer");
            a.getButtonTypes().clear();

            a.getButtonTypes().addAll(verwijder, annuleer);
            Optional<ButtonType> showAndWait = a.showAndWait();
            if (showAndWait.isPresent()) {
                return showAndWait.get() == verwijder;
            }
            return false;
    }


}

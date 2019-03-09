/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DTOActiviteit;
import domein.DomeinController;
import domein.IActiviteit;
import domein.ILid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import util.FullScreenResolution;
import static util.Validatie.*;

/**
 * FXML Controller class
 *
 * @author Jef
 */
public class ActiviteitDetailPaneelController extends VBox {

    private double sceneWidth = FullScreenResolution.getWidth() / 10 * 4.25;
    private double sceneHeight = FullScreenResolution.getHeight();

    @FXML
    private Label lblTitel;
    @FXML
    private Label lblEinddatum;
    @FXML
    private DatePicker dpEinddatum;
    @FXML
    private Label lblEinddatumFout;
    @FXML
    private Label lblStage;
    @FXML
    private CheckBox cbStage;
    @FXML
    private Button btnNieuweActiviteit;
    @FXML
    private Button btnVerwijderActiviteit;

    private final DomeinController dc;
    @FXML
    private Label lblNaam;
    @FXML
    private Label lblNaamFout;
    @FXML
    private TextField tfNaam;
    @FXML
    private Label lblMaxAanwezigen;
    @FXML
    private Tab tDetails;
    @FXML
    private Tab tInschrijven;
    @FXML
    private Label lblAllePersonen;
    @FXML
    private TableView<ILid> tblAlleLeden;
    @FXML
    private TableColumn<ILid, String> tcVoornaamA;
    @FXML
    private TableColumn<ILid, String> tcAchternaamA;
    @FXML
    private TableColumn<ILid, String> tcGraadA;
    @FXML
    private TableColumn<ILid, String> tcTypeA;
    @FXML
    private Button btnInschrijven;
    @FXML
    private Button btnUitschrijven;
    @FXML
    private Label lblIngeschreven;
    @FXML
    private TableView<ILid> tblIngeschreven;
    @FXML
    private TableColumn<ILid, String> tcVoornaamI;
    @FXML
    private TableColumn<ILid, String> tcAchternaamI;
    @FXML
    private TableColumn<ILid, String> tcGraadI;
    @FXML
    private TableColumn<ILid, String> tcTypeI;

    private ActiviteitOverzichtSceneController aosc;
    @FXML
    private Label lblMessage;
    @FXML
    private TextField tfMaxAanwezigen;
    @FXML
    private DatePicker dpBegindatum;
    @FXML
    private Label lblBegindatum;
    @FXML
    private Label lblBegindatumFout;

    private boolean veldenCompleet;

    private boolean nieuwActiviteit;

    private List<TextField> niet_verplicht = new ArrayList<>();
    @FXML
    private Label lblMaxAanwezigenFout;
    @FXML
    private Button btnAnnuleerWijziging;
    @FXML
    private Button btnBevestigWijziging;

    public ActiviteitDetailPaneelController(DomeinController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ActiviteitDetailPaneel.fxml"));
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
        lblNaamFout.setVisible(false);
        lblBegindatumFout.setVisible(false);
        lblEinddatumFout.setVisible(false);
        lblMaxAanwezigenFout.setVisible(false);

        lblTitel.setPrefWidth(sceneWidth);

        tblAlleLeden.setPrefSize(sceneWidth / 2 - 30, sceneHeight);
        tblIngeschreven.setPrefSize(sceneWidth / 2 - 30, sceneHeight);

        tcVoornaamA.prefWidthProperty().bind(tblAlleLeden.widthProperty().divide(4));
        tcAchternaamA.prefWidthProperty().bind(tblAlleLeden.widthProperty().divide(4));
        tcGraadA.prefWidthProperty().bind(tblAlleLeden.widthProperty().divide(4));
        tcTypeA.prefWidthProperty().bind(tblAlleLeden.widthProperty().divide(4));

        tcVoornaamI.prefWidthProperty().bind(tblIngeschreven.widthProperty().divide(4));
        tcAchternaamI.prefWidthProperty().bind(tblIngeschreven.widthProperty().divide(4));
        tcGraadI.prefWidthProperty().bind(tblIngeschreven.widthProperty().divide(4));
        tcTypeI.prefWidthProperty().bind(tblIngeschreven.widthProperty().divide(4));

        btnInschrijven.prefWidthProperty().bind(tblAlleLeden.widthProperty().divide(2));
        btnUitschrijven.prefWidthProperty().bind(tblIngeschreven.widthProperty().divide(2));

        btnVerwijderActiviteit.setOnMouseClicked(e -> {
            aosc.verwijderGeselecteerdeActiviteit();
        });

        btnNieuweActiviteit.setOnMouseClicked(e -> {
            nieuwActiviteitPaneel();
        });
        btnAnnuleerWijziging.setOnMouseClicked(e -> {
            if (clearTextFields()) {
                dc.verwijderSelectieActiviteit();
                nieuwActiviteitPaneel();
            }
        });

        voegNietVerplichteVeldenToe();

        tInschrijven.setDisable(true);
    }

    public boolean fillActiviteit(IActiviteit activiteit) {
        if (clearTextFields()) {
            tfNaam.clear();
            dpBegindatum.getEditor().clear();
            dpEinddatum.getEditor().clear();
            cbStage.setSelected(false);

            tfNaam.setText(activiteit.getNaam());
            dpBegindatum.setValue(activiteit.getBeginDatum());
            dpEinddatum.setValue(activiteit.getEindDatum());
            cbStage.setSelected(activiteit.isStage());
            tfMaxAanwezigen.setText(String.format("%d", activiteit.getMaxAanwezigen()));

            tInschrijven.setDisable(false);
            tblIngeschreven.setItems(FXCollections.observableArrayList(dc.geefIngeschrevenLeden(activiteit.getId())));
            tcVoornaamI.setCellValueFactory(cellData -> cellData.getValue().getVoornaamProperty());
            tcAchternaamI.setCellValueFactory(cellData -> cellData.getValue().getFamilienaamProperty());
            tcGraadI.setCellValueFactory(cellData -> cellData.getValue().getGraadProperty());
            tcTypeI.setCellValueFactory(celldata -> celldata.getValue().getTypeProperty());

            tblAlleLeden.setItems(geefAlleLedenNietIngeschreven(activiteit));
            tcVoornaamA.setCellValueFactory(cellData -> cellData.getValue().getVoornaamProperty());
            tcAchternaamA.setCellValueFactory(cellData -> cellData.getValue().getFamilienaamProperty());
            tcGraadA.setCellValueFactory(cellData -> cellData.getValue().getGraadProperty());
            tcTypeA.setCellValueFactory(celldata -> celldata.getValue().getTypeProperty());
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
            if (nieuwActiviteit) {
                maakNieuweActiviteit();
            } else {
                if (isGewijzigd()) {
                    wijzigActiviteit();
                } else {
                    lblMessage.setText("Er zijn geen wijzigingen");
                    lblMessage.setVisible(true);
                }
            }
        } else {
            lblMessage.setText("Niet alle velden zijn correct");
            lblMessage.setVisible(true);
        }
    }

    public void setOverzichtSceneController(ActiviteitOverzichtSceneController aosc) {
        this.aosc = aosc;

    }

    public void clearNaVerwijderen() {
        dc.verwijderSelectieActiviteit();
        if (clearTextFields()) {
            lblMessage.setText("Activiteit werd verwijderd");
            lblMessage.setVisible(true);
            nieuwActiviteitPaneel();
        }

    }

    private boolean clearTextFields() {
        boolean save = false;
        if (dc.geenActiviteitGeselecteerd() ? true : !(save = alertNaWijzigingen())) {
            makeElementsWhiteLabelsInvisible();
            TextField[] textfields
                    = geefTextfields();
            Arrays.stream(textfields).forEach(txt -> {
                txt.clear();
            });
            dpBegindatum.setValue(LocalDate.now());
            dpEinddatum.setValue(LocalDate.now());
            return true;
        }

        if (save) {
            bevestigWijziging(new ActionEvent());
            return false;
        }
        return true;
    }

    private void nieuwActiviteitPaneel() {
        if (clearTextFields()) {
            nieuwActiviteit = true;
            lblTitel.setText("Nieuwe Activiteit toevoegen");
        }
    }

    private boolean alertNaWijzigingen() {
        if (isGewijzigd()) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
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

    private boolean isGewijzigd() {
        IActiviteit current_activiteit = dc.getCurrentActiviteit();
        if (dc.geenActiviteitGeselecteerd()) {
            return false;
        }
        int maxAawezigentemp;
        try {
            maxAawezigentemp = Integer.parseInt(tfMaxAanwezigen.getText());
        } catch (NumberFormatException ex) {
            maxAawezigentemp = 100;
        }

        boolean wijzig = current_activiteit.getNaam().equals(tfNaam.getText()) && current_activiteit.getBeginDatum().equals(dpBegindatum.getValue())
                && current_activiteit.getEindDatum().equals(dpEinddatum.getValue()) && current_activiteit.isStage() == cbStage.isSelected()
                && current_activiteit.getMaxAanwezigen() == maxAawezigentemp;
        return !wijzig;
    }

    private void makeElementsWhiteLabelsInvisible() {
        Label[] errormessages = geefLabels();
        lblMessage.setVisible(false);
        Arrays.stream(errormessages).forEach(label -> {
            label.setVisible(false);
        });
    }

    private TextField[] geefTextfields() {
        TextField[] textfields = {tfNaam, tfMaxAanwezigen};
        return textfields;
    }

    private Label[] geefLabels() {
        Label[] errormessages = {lblNaamFout, lblBegindatumFout, lblEinddatumFout};
        return errormessages;
    }

    private void valideerVelden() {
        TextField[] textfields = geefTextfields();
        Label[] errormessages = geefLabels();

        lblMessage.setVisible(false);
        for (int i = 0; i < textfields.length; i++) {
            if (moetIngevuldzijn(textfields[i])) {
                errorOn(errormessages[i], textfields[i], "Gelieve in te vullen");
            }
        }

        if (isNullOrEmpty(tfNaam.getText())) {
            errorOn(lblNaamFout, null, "Gelieve een naam in te vullen");
        }
        if (isNull(dpBegindatum.getValue())) {
            errorOn(lblBegindatumFout, null, "Gelieve een begindatum in te vullen");
        }
        if (isNull(dpEinddatum.getValue())) {
            errorOn(lblEinddatumFout, null, "Gelieve een einddatum in te vullen");
        }
        if (isNullOrEmpty(tfMaxAanwezigen.getText())) {
            tfMaxAanwezigen.setText("100");
        }
        if (!isANumber(tfMaxAanwezigen.getText())) {
            errorOn(lblMaxAanwezigenFout, null, "Gelieve een getal in te vullen");
        }
        if (!isInFuture(dpBegindatum.getValue())) {
            errorOn(lblBegindatumFout, null, "Gelieve vandaag of een datum in de toekomst te geven");
        }
        if (!isInFuture(dpEinddatum.getValue())) {
            errorOn(lblEinddatumFout, null, "Gelieve vandaag of een datum in de toekomst te geven");
        }
        if (dpBegindatum.getValue().compareTo(dpEinddatum.getValue()) < 0) {
            errorOn(lblEinddatumFout, null, "Gelieve een datum die op of na de begindatum ligt te geven");
        }
    }

    private void maakNieuweActiviteit() {
        DTOActiviteit dto = new DTOActiviteit(tfNaam.getText(), dpBegindatum.getValue(), dpEinddatum.getValue(), cbStage.isSelected(), Integer.parseInt(tfMaxAanwezigen.getText()));
        dc.voegActiviteitToe(dto);
        lblMessage.setText("Activiteit werd toegevoegd");
        lblMessage.setVisible(true);
    }

    private void wijzigActiviteit() {
        dc.wijzigActiviteit(tfNaam.getText(), dpBegindatum.getValue(),
                dpEinddatum.getValue(), cbStage.isSelected(), Integer.parseInt(tfMaxAanwezigen.getText()));

        lblMessage.setText("Wijzigingen zijn opgeslagen");
        lblMessage.setVisible(true);
        opvullenVanFields(dc.getCurrentActiviteit());
    }

    private boolean moetIngevuldzijn(TextField textfield) {
        if (niet_verplicht.contains(textfield)) {
            return false;
        }
        return textfield == null || textfield.getText().isEmpty();
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

    private void opvullenVanFields(IActiviteit activiteit) {
        lblTitel.setText("Activiteit wijzigen");
        tfNaam.setText(activiteit.getNaam());
        dpBegindatum.setValue(activiteit.getBeginDatum());
        dpEinddatum.setValue(activiteit.getEindDatum());
        tfMaxAanwezigen.setText(String.format("%d", activiteit.getMaxAanwezigen()));
        cbStage.setSelected(activiteit.isStage());
        nieuwActiviteit = false;
    }

    private void voegNietVerplichteVeldenToe() {
        niet_verplicht.add(tfMaxAanwezigen);
    }

    public void setAosc(ActiviteitOverzichtSceneController aosc) {
        this.aosc = aosc;
    }

    private ObservableList<ILid> geefAlleLedenNietIngeschreven(IActiviteit activiteit) {
        List<ILid> alleLeden = dc.getLeden();
        List<ILid> ingeschrevenLeden = dc.geefIngeschrevenLeden(activiteit.getId());

        return FXCollections.observableArrayList(alleLeden.stream().filter(l -> {
            return !ingeschrevenLeden.contains((ILid) l);
        }).collect(Collectors.toList()));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Activiteit;
import domein.ActiviteitType;
import domein.DomeinController;
import domein.Exportable;
import domein.ExportableActiviteit;
import domein.ExportableKampioenschap;
import domein.ExportableLidAanwezigheden;
import domein.ExportableLidInschrijvingen;
import domein.ExportableOefening;
import domein.Graad;
import domein.IActiviteit;
import domein.IKampioenschap;
import domein.ILid;
import domein.IOefening;
import domein.Kampioenschap;
import domein.LesType;
import domein.Lid;
import domein.Oefening;
import domein.OverzichtType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import util.FullScreenResolution;

/**
 * FXML Controller class
 *
 * @author Mout
 */
public class OverzichtOpvraagSceneController extends HBox {

    @FXML
    private ComboBox<OverzichtType> cboType;

    private final DomeinController dc;
    @FXML
    private TextField txtBesNaam;

    private String path = "";
    private double sceneWidth = FullScreenResolution.getWidth() / 10 * 4.25;
    private double sceneHeight = FullScreenResolution.getHeight();
    @FXML
    private Label lblOverzichtOpvragen;
    @FXML
    private Label lblOverzichtRaadplegen;
    @FXML
    private Label lblOverzicht;
    @FXML
    private Label lblBesNaam;

    private final List<Object> extraParameters;
    @FXML
    private Label lblExtraParameters;
    @FXML
    private HBox hBoxTableContainer;
    @FXML
    private VBox vBoxContainer;
    @FXML
    private HBox hBoxTopRow;
    @FXML
    private HBox hBoxUnderRow;
    @FXML
    private Button btnMaak;
    @FXML
    private Button btnLocation;
    @FXML
    private Button btnPreview;

    private ComboBox<LesType> cboFormule;
    private TextField txfLidNaam;
    private DatePicker datePicker;
    private TextField txfANaam;
    private ComboBox cbActiviteitType;
    private ComboBox<Graad> cboGraad;
    private TextField txfNaam;
    @FXML
    private Button btnClear;

    public OverzichtOpvraagSceneController(DomeinController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtOpvraagScene.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.dc = dc;
        buildGui();
        extraParameters = new ArrayList<>();
        cboType.valueProperty().addListener((ObservableValue<? extends OverzichtType> observable, OverzichtType oldValue, OverzichtType newValue) -> {
            System.out.println(newValue);
            makeExtraParamScreen(newValue);
        });

    }

    @FXML
    private <T extends Exportable> void maakOverzicht(ActionEvent event) {
        OverzichtType type = cboType.getSelectionModel().getSelectedItem();
        if (type == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error: geen overzichtstype");
            alert.setContentText("Geen overzichtstype gekozen");
            alert.showAndWait();
        } else {
            fillParameters(type);
            List<T> overzicht = dc.maakOverzichtList(type, extraParameters);
            placeTable(type, FXCollections.observableArrayList(overzicht));
        }
    }

    private void maakOverzicht(OverzichtType type, ObservableList list) {
        placeTable(type, list);
    }

    @FXML
    private void openDirectoryChooser(ActionEvent event) {
        DirectoryChooser dir = new DirectoryChooser();
        File selectedDir = dir.showDialog(this.getScene().getWindow());
        if (selectedDir == null) {
            path += System.getProperty("user.home");
            path += "/Documents/";
        } else {
            path = selectedDir.getPath();
        }

        //dit is een lijst van alle leden
        //naam afhankelijk van de lijst later
        String besNaam = txtBesNaam.getText();
        if (besNaam == null || besNaam.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Geen naam gevonden voor het bestand.");
            alert.showAndWait();
        } else {
            path += "/" + besNaam + ".xls" + "/";
        }
    }

    private void buildGui() {
        cboType.setItems(FXCollections.observableArrayList(Arrays.asList(OverzichtType.values())));
        setMaxScreen();

        datePicker = new DatePicker();
        txfLidNaam = new TextField();
        cboFormule = new ComboBox();
        txfANaam = new TextField();
        cbActiviteitType = new ComboBox();
        cboGraad = new ComboBox();
        txfNaam = new TextField();
    }

    private void setMaxScreen() {
        lblOverzichtOpvragen.setMinWidth(sceneWidth);
        lblOverzichtRaadplegen.setMinWidth(sceneWidth);

        cboType.setPrefWidth(sceneWidth / 4.5);
        lblOverzicht.setPrefWidth(sceneWidth / 5);

        lblBesNaam.setPrefWidth(sceneWidth / 5);
        txtBesNaam.setPrefWidth(sceneWidth / 5);

        btnLocation.setPrefWidth(sceneWidth / 5);
        btnPreview.setPrefWidth(sceneWidth / 4.5);
        btnMaak.setPrefWidth(sceneWidth / 5);
        btnClear.setPrefWidth(sceneWidth / 4.5);

        lblExtraParameters.setPrefWidth(sceneWidth / 2);
    }

    private void makeExtraParamScreen(OverzichtType type) {

        Label lblDatum = new Label("Op datum");
        lblDatum.setPrefWidth(sceneWidth / 4.5);
        datePicker.setPrefWidth(sceneWidth / 4.2);
        VBox vBoxDatum = new VBox();
        VBox.setMargin(vBoxDatum, new Insets(10));
        vBoxDatum.getChildren().addAll(lblDatum, datePicker);

        Label lblLidNaam = new Label("Op lid");
        lblLidNaam.setPrefWidth(sceneWidth / 4.5);
        txfLidNaam.setMaxWidth(195);
        VBox vBoxLid = new VBox();
        VBox.setMargin(vBoxLid, new Insets(10));
        vBoxLid.getChildren().addAll(lblLidNaam, txfLidNaam);

        Label lblFormule = new Label("Op formule");
        lblFormule.setPrefWidth(sceneWidth / 4.5);
        cboFormule.setPrefWidth(sceneWidth / 4.5);
        cboFormule.setPromptText("Kies een formule");
        cboFormule.setItems(FXCollections.observableArrayList(Arrays.asList(LesType.values())));
        cboFormule.getSelectionModel().selectLast();
        VBox vBoxFormule = new VBox();
        VBox.setMargin(vBoxFormule, new Insets(10));
        vBoxFormule.getChildren().addAll(lblFormule, cboFormule);

        Label lblNoExtra = new Label("Geen extra parameters");
        cboGraad.getSelectionModel().selectLast();

        switch (type) {
            case AANWEZIGHEID:
                vBoxContainer.getChildren().clear();
                extraParameters.clear();

                hBoxTopRow.getChildren().clear();
                hBoxTopRow.getChildren().addAll(vBoxLid, vBoxDatum);

                hBoxUnderRow.getChildren().clear();
                hBoxUnderRow.getChildren().addAll(vBoxFormule);

                vBoxContainer.getChildren().addAll(hBoxTopRow, hBoxUnderRow);
                hBoxTableContainer.getChildren().clear();
                break;
            case ACTIVITEIT:
                vBoxContainer.getChildren().clear();
                extraParameters.clear();

                hBoxTopRow.getChildren().clear();

                Label lblANaam = new Label("Acitiviteitsnaam");
                lblANaam.setPrefWidth(sceneWidth / 4.5);

                txfANaam.setPrefWidth(sceneWidth / 4.5);
                VBox vbANaam = new VBox();
                VBox.setMargin(vbANaam, new Insets(10));
                vbANaam.getChildren().addAll(lblANaam, txfANaam);

                Label lblType = new Label("Type");
                cbActiviteitType.setItems(FXCollections.observableArrayList(Arrays.asList(ActiviteitType.values())));
                cbActiviteitType.getSelectionModel().selectFirst();
                VBox vbType = new VBox();
                VBox.setMargin(vbType, new Insets(10));
                vbType.getChildren().addAll(lblType, cbActiviteitType);

                hBoxTopRow.getChildren().addAll(vbANaam, vbType);

                vBoxContainer.getChildren().addAll(hBoxTopRow);
                hBoxTableContainer.getChildren().clear();
                break;
            case CLUBKAMPIOENSCHAP:
                vBoxContainer.getChildren().clear();
                extraParameters.clear();

                hBoxTopRow.getChildren().clear();

                hBoxTopRow.getChildren().addAll(lblNoExtra);

                vBoxContainer.getChildren().addAll(hBoxTopRow);
                hBoxTableContainer.getChildren().clear();
                break;
            case INSCHRIJVING:
                vBoxContainer.getChildren().clear();
                extraParameters.clear();

                hBoxTopRow.getChildren().clear();
                hBoxTopRow.getChildren().addAll(vBoxLid, vBoxDatum);

                hBoxUnderRow.getChildren().clear();
                hBoxUnderRow.getChildren().addAll(vBoxFormule);

                vBoxContainer.getChildren().addAll(hBoxTopRow, hBoxUnderRow);
                hBoxTableContainer.getChildren().clear();
                break;
            case LESMATERIAAL:
                vBoxContainer.getChildren().clear();
                extraParameters.clear();

                hBoxTopRow.getChildren().clear();
                Label lblGraad = new Label("Graad");
                cboGraad.setPromptText("Kies een graad");
                cboGraad.setItems(FXCollections.observableArrayList(Arrays.asList(Graad.values())));
                cboGraad.setPrefWidth(sceneWidth / 4.2);
                VBox vbGraad = new VBox();
                VBox.setMargin(vbGraad, new Insets(10));
                vbGraad.getChildren().addAll(Arrays.asList(lblGraad, cboGraad));
                hBoxTopRow.getChildren().addAll(Arrays.asList(vbGraad));

                hBoxUnderRow.getChildren().clear();
                Label lblNaam = new Label("Naam");
                txfNaam.setPrefWidth(sceneWidth / 4.2);
                VBox vbNaam = new VBox();
                VBox.setMargin(vbNaam, new Insets(10));
                vbNaam.getChildren().addAll(lblNaam, txfNaam);
                hBoxUnderRow.getChildren().addAll(Arrays.asList(vbNaam));

                vBoxContainer.getChildren().addAll(hBoxTopRow, hBoxUnderRow);
                hBoxTableContainer.getChildren().clear();
                break;
        }
    }

    private void placeTable(OverzichtType type, ObservableList list) {
        Label lblAanwezigheden = new Label("Aanwezigheden");
        ListView<String> lstAanwezigheden = new ListView<>();
        lstAanwezigheden.setPrefHeight(sceneHeight - 100);
        lstAanwezigheden.setMaxHeight(sceneHeight - 100);

        VBox vbAanwezigheden = new VBox();
        vbAanwezigheden.setSpacing(10);
        vbAanwezigheden.getChildren().addAll(lblAanwezigheden, lstAanwezigheden);
        switch (type) {
            case ACTIVITEIT:
                hBoxTableContainer.getChildren().clear();
                TableView<IActiviteit> tblActiviteiten = new TableView<>();
                tblActiviteiten.setItems(list);

                TableColumn<IActiviteit, String> aNaamcol = new TableColumn<>();
                aNaamcol.setText("Activiteitnaam");
                aNaamcol.setCellValueFactory(cellData
                        -> cellData.getValue().getNaamProperty());
                TableColumn<IActiviteit, String> aStartCol = new TableColumn<>();
                aStartCol.setText("Start datum");
                aStartCol.setCellValueFactory(cellData
                        -> cellData.getValue().getBeginDatumProperty());
                TableColumn<IActiviteit, String> aEndCol = new TableColumn<>();
                aEndCol.setText("Eind datum");
                aEndCol.setCellValueFactory(cellData
                        -> cellData.getValue().getEindDatumProperty());

                TableColumn<IActiviteit, String> StageCol = new TableColumn<>();
                StageCol.setText("Type");
                StageCol.setCellValueFactory(cellData
                        -> cellData.getValue().getActiviteitTypeProperty());

                tblActiviteiten.setPrefWidth(sceneWidth);
                tblActiviteiten.setPrefHeight(sceneHeight - 100);

                // 3 kolommen, dus 1/3 van de tableview.
                aNaamcol.prefWidthProperty().bind(tblActiviteiten.widthProperty().divide(4));
                aStartCol.prefWidthProperty().bind(tblActiviteiten.widthProperty().divide(4));
                aEndCol.prefWidthProperty().bind(tblActiviteiten.widthProperty().divide(4));
                StageCol.prefWidthProperty().bind(tblActiviteiten.widthProperty().divide(4));

                tblActiviteiten.getColumns().addAll(aNaamcol, aStartCol, aEndCol, StageCol);
                hBoxTableContainer.getChildren().addAll(tblActiviteiten);
                break;
            case CLUBKAMPIOENSCHAP:
                hBoxTableContainer.getChildren().clear();
                TableView<IKampioenschap> tblKampioenschap = new TableView<>();
                tblKampioenschap.setItems(list);

                TableColumn<IKampioenschap, String> nameCol = new TableColumn<>();
                nameCol.setText("Naam");
                nameCol.setCellValueFactory(cellData -> cellData.getValue().getNaamProperty());

                TableColumn<IKampioenschap, String> datumCol = new TableColumn<>();
                datumCol.setText("Datum");
                datumCol.setCellValueFactory(cellData
                        -> cellData.getValue().getDatumProperty());

                TableColumn<IKampioenschap, String> leeftijdCCol = new TableColumn<>();
                leeftijdCCol.setText("Leeftijdcategorie");
                leeftijdCCol.setCellValueFactory(cellData
                        -> cellData.getValue().getLeeftijdCategorieProperty());

                nameCol.prefWidthProperty().bind(tblKampioenschap.widthProperty().divide(3));
                datumCol.prefWidthProperty().bind(tblKampioenschap.widthProperty().divide(3));
                leeftijdCCol.prefWidthProperty().bind(tblKampioenschap.widthProperty().divide(3));
                tblKampioenschap.getColumns().addAll(nameCol, datumCol,leeftijdCCol);

                tblKampioenschap.setPrefWidth(sceneWidth * 2 / 3.1);
                tblKampioenschap.setPrefHeight(sceneHeight - 100);
                tblKampioenschap.setMaxHeight(sceneHeight - 100);

                lstAanwezigheden.setPrefWidth(sceneWidth / 3);

                tblKampioenschap.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                    if (newValue != null) {
                        System.out.println(newValue);
                        List<String> aanwezigen = newValue.geefAanwezigen().stream().map(lid -> lid.getVoornaam() + " " + lid.getFamilienaam()).collect(Collectors.toList());
                        lstAanwezigheden.setItems(FXCollections.observableArrayList(aanwezigen));
                    }
                });

                tblKampioenschap.getSelectionModel().selectFirst();

                HBox hbKampioenschap = new HBox();
                hbKampioenschap.setSpacing(10);
                hbKampioenschap.getChildren().addAll(tblKampioenschap, vbAanwezigheden);

                hBoxTableContainer.getChildren().addAll(hbKampioenschap);
                break;
            case AANWEZIGHEID:
                hBoxTableContainer.getChildren().clear();
                TableView<ILid> tblActiviteit = new TableView<>();
                tblActiviteit.setItems(list);

                TableColumn<ILid, String> colANaam = new TableColumn<>();
                colANaam.setText("Naam");
                colANaam.setCellValueFactory(cellData
                        -> cellData.getValue().getVoornaamProperty().concat(" ").concat(cellData.getValue().getFamilienaamProperty())
                );

                TableColumn<ILid, String> colAFormule = new TableColumn<>();
                colAFormule.setText("Lesformule");
                colAFormule.setCellValueFactory(cellData
                        -> cellData.getValue().getLessenProperty()
                );

                tblActiviteit.setPrefWidth(sceneWidth * 2 / 3.1);
                tblActiviteit.setPrefHeight(sceneHeight - 100);
                tblActiviteit.setMaxHeight(sceneHeight - 100);

                colANaam.prefWidthProperty().bind(tblActiviteit.widthProperty().divide(2));
                colAFormule.prefWidthProperty().bind(tblActiviteit.widthProperty().divide(2));
                tblActiviteit.getColumns().addAll(colANaam, colAFormule);

                lstAanwezigheden.setPrefWidth(sceneWidth / 3);

                tblActiviteit.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                    if (newValue != null) {
                        System.out.println(newValue);
                        List<String> aanwezighedenPerLid = newValue.getAanwezigheden().stream().map(d -> d.toString()).collect(Collectors.toList());
                        lstAanwezigheden.setItems(FXCollections.observableArrayList(aanwezighedenPerLid));
                    }
                });

                tblActiviteit.getSelectionModel().selectFirst();

                HBox hbActiviteit = new HBox();
                hbActiviteit.setSpacing(10);
                hbActiviteit.getChildren().addAll(tblActiviteit, vbAanwezigheden);

                hBoxTableContainer.getChildren().addAll(hbActiviteit);
                break;
            case INSCHRIJVING:
                hBoxTableContainer.getChildren().clear();
                TableView<ILid> tblLidInschrijvingen = new TableView<>();
                tblLidInschrijvingen.setItems(list);
                ((SortedList) dc.getLeden()).comparatorProperty().bind(tblLidInschrijvingen.comparatorProperty());

                TableColumn<ILid, String> colNaam = new TableColumn<>();
                colNaam.setText("Naam");
                colNaam.setCellValueFactory(cellData
                        -> cellData.getValue().getVoornaamProperty().concat(" ").concat(cellData.getValue().getFamilienaamProperty())
                );

                TableColumn<ILid, String> colDatum = new TableColumn<>();
                colDatum.setText("Inschrijfdatum");
                colDatum.setCellValueFactory(cellData
                        -> cellData.getValue().getInschrijvingsDatumProperty());

                TableColumn<ILid, String> colFormule = new TableColumn<>();
                colFormule.setText("Lesformule");
                colFormule.setCellValueFactory(cellData
                        -> cellData.getValue().getLessenProperty()
                );

                tblLidInschrijvingen.setPrefWidth(sceneWidth);
                tblLidInschrijvingen.setPrefHeight(sceneHeight - 10);

                // 3 kolommen, dus 1/3 van de tableview.
                colNaam.prefWidthProperty().bind(tblLidInschrijvingen.widthProperty().divide(3));
                colDatum.prefWidthProperty().bind(tblLidInschrijvingen.widthProperty().divide(3));
                colFormule.prefWidthProperty().bind(tblLidInschrijvingen.widthProperty().divide(3));

                tblLidInschrijvingen.getColumns().addAll(colNaam, colDatum, colFormule);
                hBoxTableContainer.getChildren().addAll(tblLidInschrijvingen);
                break;
            case LESMATERIAAL:
                hBoxTableContainer.getChildren().clear();

                TableView<IOefening> tblLesMateriaal = new TableView<>();
                tblLesMateriaal.setItems(list);

                TableColumn<IOefening, String> colLNaam = new TableColumn<>();
                colLNaam.setText("Naam");
                colLNaam.setCellValueFactory(cellData -> cellData.getValue().getNaamProperty());

                TableColumn<IOefening, String> colLGraad = new TableColumn<>();
                colLGraad.setText("Graad");
                colLGraad.setCellValueFactory(cellData -> cellData.getValue().getGraadProperty());

                TableColumn<IOefening, String> colLURL = new TableColumn<>();
                colLURL.setText("Vide URL");
                colLURL.setCellValueFactory(cellData -> cellData.getValue().getVideoProperty());

                TableColumn<IOefening, String> colLPad = new TableColumn<>();
                colLPad.setText("Afbeelding pad");
                colLPad.setCellValueFactory(cellData -> cellData.getValue().getImageProperty());

                colLNaam.prefWidthProperty().bind(tblLesMateriaal.widthProperty().divide(4));
                colLGraad.prefWidthProperty().bind(tblLesMateriaal.widthProperty().divide(4));
                colLURL.prefWidthProperty().bind(tblLesMateriaal.widthProperty().divide(4));
                colLPad.prefWidthProperty().bind(tblLesMateriaal.widthProperty().divide(4));

                tblLesMateriaal.setPrefWidth(sceneWidth);
                tblLesMateriaal.setPrefHeight(sceneHeight - 100);

                tblLesMateriaal.getColumns().addAll(colLNaam, colLGraad, colLURL, colLPad);
                hBoxTableContainer.getChildren().addAll(tblLesMateriaal);
                break;
        }
    }

    @FXML
    private <T extends Exportable> void maakDocument(MouseEvent event) {
        OverzichtType type = cboType.getSelectionModel().getSelectedItem();
        fillParameters(type);
        List<T> overzicht = dc.maakOverzichtList(type, extraParameters);
        dc.maakOverzicht(overzicht, path);
        maakOverzicht(type, FXCollections.observableArrayList(overzicht));
    }

    private void fillParameters(OverzichtType type) {
        extraParameters.clear();
        switch (type) {
            case AANWEZIGHEID:
                Lid.setExportable(new ExportableLidAanwezigheden());
                extraParameters.addAll(Arrays.asList(datePicker.getValue(), txfLidNaam.getText(), cboFormule.getSelectionModel().getSelectedItem()));
                break;
            case INSCHRIJVING:
                Lid.setExportable(new ExportableLidInschrijvingen());
                extraParameters.addAll(Arrays.asList(datePicker.getValue(), txfLidNaam.getText(), cboFormule.getSelectionModel().getSelectedItem()));
                break;
            case ACTIVITEIT:
                Activiteit.setExportable(new ExportableActiviteit());
                extraParameters.addAll(Arrays.asList(cbActiviteitType.getSelectionModel().getSelectedItem(), txfANaam.getText()));
                break;
            case CLUBKAMPIOENSCHAP:
                Kampioenschap.setExportable(new ExportableKampioenschap());
                break;
            case LESMATERIAAL:
                Oefening.setExportable(new ExportableOefening());
                extraParameters.addAll(Arrays.asList(cboGraad.getSelectionModel().getSelectedItem(), txfNaam.getText()));
                break;
        }
    }

    @FXML
    private void maakFiltersLeeg(ActionEvent event) {

    }

}

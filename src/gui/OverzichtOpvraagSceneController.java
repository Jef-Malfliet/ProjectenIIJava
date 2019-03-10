/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Exportable;
import domein.FicheType;
import domein.IActiviteit;
import domein.ILid;
import domein.LesType;
import domein.OverzichtType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

    private List<Object> extraParameters;
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
    DatePicker datePicker;

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
        //txfLidNaam.setPrefWidth((sceneWidth / 2) / 2.1);
        txfLidNaam.setMaxWidth(195);
        VBox vBoxLid = new VBox();
        VBox.setMargin(vBoxLid, new Insets(10));
        vBoxLid.getChildren().addAll(lblLidNaam, txfLidNaam);

        Label lblFormule = new Label("Op formule");
        lblFormule.setPrefWidth(sceneWidth / 4.5);
        cboFormule.setPrefWidth(sceneWidth / 4.5);
        cboFormule.setPromptText("Kies een formule");
        cboFormule.setItems(FXCollections.observableArrayList(Arrays.asList(LesType.values())));
        VBox vBoxFormule = new VBox();
        VBox.setMargin(vBoxFormule, new Insets(10));
        vBoxFormule.getChildren().addAll(lblFormule, cboFormule);

        Label lblLidFiche = new Label("Genereer een extra fiche per lid");
        lblLidFiche.setPrefWidth(sceneWidth / 4.5);
        //CheckBox cb = new CheckBox();

        Label lblNoExtra = new Label("Geen extra parameters");

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
                hBoxTopRow.getChildren().addAll(lblNoExtra);

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

                extraParameters.addAll(Arrays.asList(datePicker.getValue(), txfLidNaam.getText(), cboFormule.getSelectionModel().getSelectedItem()));
                vBoxContainer.getChildren().addAll(hBoxTopRow, hBoxUnderRow);
                hBoxTableContainer.getChildren().clear();
                break;
            case LESMATERIAAL:
                vBoxContainer.getChildren().clear();
                extraParameters.clear();

                hBoxTopRow.getChildren().clear();
                hBoxTopRow.getChildren().addAll(lblNoExtra);

                vBoxContainer.getChildren().addAll(hBoxTopRow);
                hBoxTableContainer.getChildren().clear();
                break;
        }
    }

    private void placeTable(OverzichtType type, ObservableList list) {
        switch (type) {
            case ACTIVITEIT:
                hBoxTableContainer.getChildren().clear();
                TableView<IActiviteit> tblActiviteiten = new TableView<>();
                tblActiviteiten.setItems(dc.getActiviteiten());

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
                StageCol.setText("Stage");
                StageCol.setCellValueFactory(cellData
                        -> cellData.getValue().getStageProperty());

                tblActiviteiten.setPrefWidth(sceneWidth);
                tblActiviteiten.setPrefHeight(sceneHeight);

                // 3 kolommen, dus 1/3 van de tableview.
                aNaamcol.prefWidthProperty().bind(tblActiviteiten.widthProperty().divide(3));
                aStartCol.prefWidthProperty().bind(tblActiviteiten.widthProperty().divide(3));
                aEndCol.prefWidthProperty().bind(tblActiviteiten.widthProperty().divide(3));

                tblActiviteiten.getColumns().addAll(aNaamcol, aStartCol, aEndCol);
                hBoxTableContainer.getChildren().addAll(tblActiviteiten);
                break;
            case CLUBKAMPIOENSCHAP:
                break;
            case AANWEZIGHEID:
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
                tblLidInschrijvingen.setPrefHeight(sceneHeight);

                // 3 kolommen, dus 1/3 van de tableview.
                colNaam.prefWidthProperty().bind(tblLidInschrijvingen.widthProperty().divide(3));
                colDatum.prefWidthProperty().bind(tblLidInschrijvingen.widthProperty().divide(3));
                colFormule.prefWidthProperty().bind(tblLidInschrijvingen.widthProperty().divide(3));

                tblLidInschrijvingen.getColumns().addAll(colNaam, colDatum, colFormule);
                hBoxTableContainer.getChildren().addAll(tblLidInschrijvingen);
                break;
            case LESMATERIAAL:
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
            case INSCHRIJVING:
                extraParameters.addAll(Arrays.asList(datePicker.getValue(), txfLidNaam.getText(), cboFormule.getSelectionModel().getSelectedItem()));
                break;
            case ACTIVITEIT:
                break;
            case CLUBKAMPIOENSCHAP:
                break;
            case LESMATERIAAL:
                break;
        }
    }

}

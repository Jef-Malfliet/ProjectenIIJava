/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.FicheType;
import domein.ILid;
import domein.LesType;
import domein.Overzicht;
import domein.OverzichtType;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private Button btnSearch;
    @FXML
    private Button btnSlaOp;
    @FXML
    private HBox hBoxContainer;
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

        cboType.valueProperty().addListener((ObservableValue<? extends OverzichtType> observable, OverzichtType oldValue, OverzichtType newValue) -> {
            System.out.println(newValue);
            makeExtraParamScreen(newValue);
        });

        extraParameters = new ArrayList<>();
    }

    @FXML
    private void maakOverzicht(ActionEvent event) {
        String besNaam = txtBesNaam.getText();
        dc.maakOverzicht(cboType.getSelectionModel().getSelectedItem(), besNaam, path, extraParameters);
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

        //ExportFiles.toExcel(dc.getLeden(), 25, 20, path);
    }

    private void buildGui() {
        cboType.setItems(FXCollections.observableArrayList(Arrays.asList(OverzichtType.values())));

        setMaxScreen();
    }

    private void setMaxScreen() {
        lblOverzichtOpvragen.setMinWidth(sceneWidth);
        lblOverzichtRaadplegen.setMinWidth(sceneWidth);

        cboType.setPrefWidth(sceneWidth / 4.5);
        lblOverzicht.setPrefWidth(sceneWidth / 4.5);

        lblBesNaam.setPrefWidth(sceneWidth / 4.5);
        txtBesNaam.setPrefWidth(sceneWidth / 4.5);

        btnSlaOp.setPrefWidth(sceneWidth / 4.5);
        btnSearch.setPrefWidth(sceneWidth / 4.5);

        lblExtraParameters.setPrefWidth(sceneWidth / 2);
    }

    private void makeExtraParamScreen(OverzichtType type) {

        Label lblDatum = new Label("Op datum");
        DatePicker datePicker = new DatePicker();
        lblDatum.setPrefWidth((sceneWidth / 2) / 2.1);
        datePicker.setPrefWidth((sceneWidth / 2) / 2.1);
        VBox vBoxDatum = new VBox();
        VBox.setMargin(vBoxDatum, new Insets(10));
        vBoxDatum.getChildren().addAll(lblDatum, datePicker);

        Label lblLidNaam = new Label("Op lid");
        TextField txfLidNaam = new TextField();
        lblLidNaam.setPrefWidth((sceneWidth / 2) / 2.1);
        txfLidNaam.setPrefWidth((sceneWidth / 2) / 2.1);
        VBox vBoxLid = new VBox();
        VBox.setMargin(vBoxLid, new Insets(10));
        vBoxLid.getChildren().addAll(lblLidNaam, txfLidNaam);

        Label lblFormule = new Label("Op formule");
        ComboBox cboFormule = new ComboBox();
        lblFormule.setPrefWidth(((sceneWidth / 2) / 2.1));
        cboFormule.setPrefWidth((sceneWidth / 2) / 2.1);
        cboFormule.setPromptText("Kies een formule");
        cboFormule.setItems(FXCollections.observableArrayList(Arrays.asList(LesType.values())));
        VBox vBoxFormule = new VBox();
        VBox.setMargin(vBoxFormule, new Insets(10));
        vBoxFormule.getChildren().addAll(lblFormule, cboFormule);

        Label lblLidFiche = new Label("Genereer een extra fiche per lid");
        lblLidFiche.setPrefWidth((sceneWidth / 2) / 2.1);
        CheckBox cb = new CheckBox();

        switch (type) {
            case AANWEZIGHEID:
                hBoxContainer.getChildren().clear();
                extraParameters.clear();
                extraParameters.addAll(Arrays.asList(datePicker, txfLidNaam, cboFormule));
                hBoxContainer.getChildren().addAll(vBoxLid, vBoxDatum, vBoxFormule);
                hBoxTableContainer.getChildren().clear();
                break;
            case ACTIVITEIT:
                hBoxContainer.getChildren().clear();
                extraParameters.clear();

                HBox hBoxFiche = new HBox();
                HBox.setMargin(hBoxFiche, new Insets(10));
                hBoxFiche.getChildren().addAll(cb, lblLidFiche);

                extraParameters.addAll(Arrays.asList(cb));
                hBoxContainer.getChildren().addAll(hBoxFiche);
                hBoxTableContainer.getChildren().clear();
                break;
            case CLUBKAMPIOENSCHAP:
                hBoxContainer.getChildren().clear();
                extraParameters.clear();
                hBoxTableContainer.getChildren().clear();
                break;
            case INSCHRIJVING:
                hBoxContainer.getChildren().clear();
                extraParameters.clear();

                ComboBox cboFicheType = new ComboBox();
                cboFicheType.setPromptText("Genereer een fiche");
                cboFicheType.setItems(FXCollections.observableArrayList(Arrays.asList(FicheType.values())));
                cboFicheType.setPrefWidth((sceneWidth / 2) / 2.1);

                HBox hBoxIFiche = new HBox();
                HBox.setMargin(hBoxIFiche, new Insets(5));
                hBoxIFiche.getChildren().addAll(cb, cboFicheType);

                VBox vBoxIFiche = new VBox();
                VBox.setMargin(vBoxIFiche, new Insets(10));
                vBoxIFiche.getChildren().addAll(lblLidFiche, hBoxIFiche);

                extraParameters.addAll(Arrays.asList(datePicker, txfLidNaam, cboFormule, cboFicheType));
                hBoxContainer.getChildren().addAll(vBoxLid, vBoxDatum, vBoxFormule, vBoxIFiche);

                hBoxTableContainer.getChildren().clear();

                TableView<ILid> tblLidInschrijvingen = new TableView<>();

                TableColumn<ILid, String> colNaam = new TableColumn<>();
                colNaam.setText("Naam");
                colNaam.setCellValueFactory(cellData
                        -> cellData.getValue().getVoornaamProperty().concat(" ").concat(cellData.getValue().getFamilienaamProperty())
                );
                TableColumn<ILid, String> colDatum = new TableColumn<>();
                colDatum.setText("Datum");
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
                hBoxContainer.getChildren().clear();
                extraParameters.clear();
                hBoxTableContainer.getChildren().clear();
                break;
        }
    }

}

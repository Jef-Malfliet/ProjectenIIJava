/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.LesType;
import domein.Overzicht;
import domein.OverzichtType;
import java.io.File;
import java.io.IOException;
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
    private TableView<Overzicht> tblOverzicht;
    @FXML
    private TableColumn<Overzicht, String> overzichtCol;
    @FXML
    private TableColumn<Overzicht, String> typeCol;
    @FXML
    private TableColumn<Overzicht, String> datumCol;
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

        overzichtCol.setCellValueFactory(cellData -> cellData.getValue().getOverzichtNaamProperty());
        typeCol.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        datumCol.setCellValueFactory(cellData -> cellData.getValue().getDatumProperty());

        tblOverzicht.setItems(FXCollections.unmodifiableObservableList(FXCollections.observableArrayList(dc.getOverzicht())));
        setMaxScreen();
    }

    private void setMaxScreen() {
        lblOverzichtOpvragen.setPrefWidth(sceneWidth);
        lblOverzichtRaadplegen.setPrefWidth(sceneWidth);
        tblOverzicht.setPrefWidth(sceneWidth);
        tblOverzicht.setPrefHeight(sceneHeight);
        // 3 kolommen, dus 1/3 van de tableview.
        overzichtCol.prefWidthProperty().bind(tblOverzicht.widthProperty().divide(3));
        typeCol.prefWidthProperty().bind(tblOverzicht.widthProperty().divide(3));
        datumCol.prefWidthProperty().bind(tblOverzicht.widthProperty().divide(3));

        cboType.setPrefWidth(sceneWidth);
        lblOverzicht.setPrefWidth(sceneWidth);

        lblBesNaam.setPrefWidth(sceneWidth);
        txtBesNaam.setPrefWidth(sceneWidth);

        btnSlaOp.setPrefWidth(sceneWidth);
        btnSearch.setPrefWidth(sceneWidth);
    }

    private void makeExtraParamScreen(OverzichtType type) {
        switch (type) {
            case AANWEZIGHEID:
                hBoxContainer.getChildren().clear();
                extraParameters.clear();

                Label lblDatum = new Label("Op datum");
                DatePicker datePicker = new DatePicker();
                lblDatum.setPrefWidth((sceneWidth / 2) / 2);
                datePicker.setPrefWidth((sceneWidth / 2) / 2);
                VBox vBoxDatum = new VBox();
                VBox.setMargin(vBoxDatum, new Insets(10));
                vBoxDatum.getChildren().addAll(lblDatum, datePicker);

                Label lblLidNaam = new Label("Op lid");
                TextField txfLidNaam = new TextField();
                lblLidNaam.setPrefWidth((sceneWidth / 2) / 2);
                txfLidNaam.setPrefWidth((sceneWidth / 2) / 2);
                VBox vBoxLid = new VBox();
                VBox.setMargin(vBoxLid, new Insets(10));
                vBoxLid.getChildren().addAll(lblLidNaam, txfLidNaam);

                Label lblFormule = new Label("op formule");
                ComboBox cboFormule = new ComboBox();
                //cboFormule.getItems().addAll(LesType.values());
                lblFormule.setPrefWidth(((sceneWidth / 2) / 2));
                cboFormule.setPrefWidth((sceneWidth / 2) / 2);
                cboFormule.setPromptText("Kies een formule");
                cboFormule.setItems(FXCollections.observableArrayList(Arrays.asList(LesType.values())));
                VBox vBoxFormule = new VBox();
                VBox.setMargin(vBoxFormule, new Insets(10));
                vBoxFormule.getChildren().addAll(lblFormule, cboFormule);

                extraParameters.addAll(Arrays.asList(datePicker, txfLidNaam, cboFormule));
                hBoxContainer.getChildren().addAll(vBoxLid, vBoxDatum, vBoxFormule);
                break;
            case ACTIVITEIT:
                hBoxContainer.getChildren().clear();
                extraParameters.clear();

                Label lblLidFische = new Label("Genereer een extra fische");
                CheckBox cb = new CheckBox();
                HBox hBoxFische = new HBox();
                hBoxFische.getChildren().addAll(cb, lblLidFische);
                HBox.setMargin(hBoxFische, new Insets(10));

                extraParameters.addAll(Arrays.asList(cb));
                hBoxContainer.getChildren().addAll(hBoxFische);
                break;
            case CLUBKAMPIOENSCHAP:
                hBoxContainer.getChildren().clear();
                extraParameters.clear();
                break;
            case INSCHRIJVING:
                hBoxContainer.getChildren().clear();
                extraParameters.clear();
                break;
            case LESMATERIAAL:
                hBoxContainer.getChildren().clear();
                extraParameters.clear();
                break;
        }
    }

}

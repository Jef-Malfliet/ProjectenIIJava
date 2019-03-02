/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Overzicht;
import domein.OverzichtType;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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
    private double sceneWidth = FullScreenResolution.getWidth()/10*4.25;
    private double sceneHeight = FullScreenResolution.getHeight();
    @FXML
    private Label lblOverzichtOpvragen;
    @FXML
    private Label lblOverzichtRaadplegen;
    @FXML
    private TextField txfNaamLid;

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
    }

    @FXML
    private void maakOverzicht(ActionEvent event) {
        dc.maakOverzicht(cboType.getSelectionModel().getSelectedItem(), path);
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
         if(besNaam == null || besNaam.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Error");
             alert.setContentText("Geen naam gevonden voor het bestand.");
             alert.showAndWait();
         }else {
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

        
    }

}

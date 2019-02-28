/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Lid;
import domein.SorteerType;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import util.FullScreenResolution;

/**
 * FXML Controller class
 *
 * @author IndyV
 */
public class OverzichtSceneController extends VBox implements PropertyChangeListener {

    private DomeinController dc;
    //OverzichtSceneController was 4/10 van het scherm.
    private double sceneWidth = FullScreenResolution.getWidth()/10*4.25;
    private double sceneHeight = FullScreenResolution.getHeight();
    
    @FXML
    private TableView<Lid> tableOverview = new TableView<>();
    @FXML
    private TableColumn<Lid, String> colBand = new TableColumn<>();
    @FXML
    private TableColumn<Lid, String> colType = new TableColumn<>();

    private final DetailPaneelController dpc;
    @FXML
    private ComboBox<SorteerType> cboFilterOptie = new ComboBox<>();
    @FXML
    private TextField txtZoek;
    @FXML
    private Label lblLedenBeheren;
    @FXML
    private TableColumn<Lid, String> colVoorNaam = new TableColumn<>();
    @FXML
    private TableColumn<Lid, String> colAchterNaam = new TableColumn<>();

    public OverzichtSceneController(DomeinController dc, DetailPaneelController dpc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtScene.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.dc = dc;
        this.dpc = dpc;
        buildGui();
    }

    private void buildGui() {
        tableOverview.getSelectionModel().selectedItemProperty().
                addListener((observableValue, oldLid, newLid)
                        -> {
                    if (newLid != null) {
                        dpc.fillLid(newLid);
                    }
                }); 

        tableOverview.setItems(dc.getLeden());
        colVoorNaam.setCellValueFactory(cellData -> cellData.getValue().getVoornaamProperty());
        colAchterNaam.setCellValueFactory(cellData -> cellData.getValue().getFamilienaamProperty());
        colBand.setCellValueFactory(cellData -> cellData.getValue().getGraadProperty());
        colType.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        cboFilterOptie.setItems(FXCollections.observableArrayList(SorteerType.values()));
        cboFilterOptie.getSelectionModel().selectFirst();
        setMaxScreen();
        ((SortedList)dc.getLeden()).comparatorProperty().bind(tableOverview.comparatorProperty());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        update();
    }

    private void setMaxScreen() {
        lblLedenBeheren.setPrefWidth(sceneWidth);
        tableOverview.setPrefWidth(sceneWidth);
        tableOverview.setPrefHeight(sceneHeight);
        // 3 kolommen, dus 1/3 van de tableview.
        colVoorNaam.prefWidthProperty().bind(tableOverview.widthProperty().divide(4));
        colAchterNaam.prefWidthProperty().bind(tableOverview.widthProperty().divide(4));
        colBand.prefWidthProperty().bind(tableOverview.widthProperty().divide(4));
        colType.prefWidthProperty().bind(tableOverview.widthProperty().divide(4));

        
    }

    private void update() {
        tableOverview.setItems(dc.getLeden());
        tableOverview.refresh();

    }

    public DetailPaneelController getDpc() {
        return dpc;
    }

    public void verwijdergeselecteerdLid() {
        Lid lid = tableOverview.getSelectionModel().selectedItemProperty().get();
        tableOverview.getSelectionModel().clearSelection();
        dpc.clearTextFields();
        if (lid != null) {
            dc.verwijderLid(lid);
            update();
        }

    }

    @FXML
    private void filter(KeyEvent event) {
        SorteerType type = cboFilterOptie.getSelectionModel().getSelectedItem();
        String van = txtZoek.getText();
        if (type == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error: geen sorteertype");
            alert.setHeaderText("Error: geen sorteertype meegegeven");
            alert.setContentText("Gelieve een sorteertype mee te geven");
        } else {
            dc.filter(cboFilterOptie.getSelectionModel().getSelectedItem(), txtZoek.getText());
        }
    }
}

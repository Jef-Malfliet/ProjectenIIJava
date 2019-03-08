/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.ILid;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
    private double sceneWidth = FullScreenResolution.getWidth() / 10 * 4.25;
    private double sceneHeight = FullScreenResolution.getHeight();

    @FXML
    private TableView<ILid> tableOverview = new TableView<>();
    @FXML
    private TableColumn<ILid, String> colBand = new TableColumn<>();
    @FXML
    private TableColumn<ILid, String> colType = new TableColumn<>();

    private final DetailPaneelController dpc;
    @FXML
    private Label lblLedenBeheren;
    @FXML
    private TableColumn<ILid, String> colVoorNaam = new TableColumn<>();
    @FXML
    private TableColumn<ILid, String> colAchterNaam = new TableColumn<>();
    @FXML
    private TextField txfVnFilter;
    @FXML
    private TextField txfFnFilter;
    @FXML
    private TextField txfGFilter;
    @FXML
    private TextField txfTFilter;
    @FXML
    private Button btnFilter;
    @FXML
    private Label lblVnFilter;
    @FXML
    private Label lblFnFilter;
    @FXML
    private Label lblGFilter;
    @FXML
    private Label lblTFilter;
    @FXML
    private Button btnReset;

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

                        if (dpc.fillLid(newLid)) {
                            dc.setCurrentLid(newLid);
                        }
                    }
                });

        tableOverview.setItems(dc.getLeden());
        colVoorNaam.setCellValueFactory(cellData -> cellData.getValue().getVoornaamProperty());
        colAchterNaam.setCellValueFactory(cellData -> cellData.getValue().getFamilienaamProperty());
        colBand.setCellValueFactory(cellData -> cellData.getValue().getGraadProperty());
        colType.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
//        cboFilterOptie.setItems(FXCollections.observableArrayList(SorteerType.values()));
//        cboFilterOptie.getSelectionModel().selectFirst();
        setMaxScreen();
        ((SortedList) dc.getLeden()).comparatorProperty().bind(tableOverview.comparatorProperty());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        update();
    }

    private void setMaxScreen() {
        lblLedenBeheren.setPrefWidth(sceneWidth);
        tableOverview.setPrefWidth(sceneWidth);
        tableOverview.setPrefHeight(sceneHeight);
        // 4 kolommen, dus 1/4 van de tableview.
        colVoorNaam.prefWidthProperty().bind(tableOverview.widthProperty().divide(4));
        colAchterNaam.prefWidthProperty().bind(tableOverview.widthProperty().divide(4));
        colBand.prefWidthProperty().bind(tableOverview.widthProperty().divide(4));
        colType.prefWidthProperty().bind(tableOverview.widthProperty().divide(4));

        lblFnFilter.setPrefWidth(sceneWidth);
        lblVnFilter.setPrefWidth(sceneWidth);
        lblGFilter.setPrefWidth(sceneWidth);
        lblTFilter.setPrefWidth(sceneWidth);

        btnFilter.setPrefWidth(sceneWidth);
        btnReset.setPrefWidth(sceneWidth);
    }

    private void update() {
        tableOverview.setItems(dc.getLeden());
        tableOverview.refresh();
    }

    public DetailPaneelController getDpc() {
        return dpc;
    }

    public void verwijdergeselecteerdLid() {
        ILid lid = tableOverview.getSelectionModel().selectedItemProperty().get();
        tableOverview.getSelectionModel().clearSelection();

        if (lid != null) {
            dc.verwijderCurrentLid();
            update();
        }
        dpc.clearNaVerwijderen();

    }

    @FXML
    private void filter(MouseEvent event) {
        String voornaamFilter = txfVnFilter.getText();
        String familienaamFilter = txfFnFilter.getText();
        String graadFilter = txfGFilter.getText();
        String typeFilter = txfTFilter.getText();

        dc.filter(voornaamFilter, familienaamFilter, graadFilter, typeFilter);
    }

    @FXML
    private void reset(MouseEvent event) {
        txfFnFilter.clear();
        txfGFilter.clear();
        txfTFilter.clear();
        txfVnFilter.clear();
        dc.filter("", "", "", "");
    }
}


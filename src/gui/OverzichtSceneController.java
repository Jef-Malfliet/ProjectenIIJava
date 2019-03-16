/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Graad;
import domein.ILid;
import domein.RolType;
import java.io.IOException;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class OverzichtSceneController extends VBox {

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
    @FXML
    private ComboBox<Graad> cboGFilter;
    @FXML
    private ComboBox<RolType> cboTFilter;

    public OverzichtSceneController(DomeinController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtScene.fxml"));
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
        tableOverview.getSelectionModel().selectedItemProperty().
                addListener((observableValue, oldLid, newLid)
                        -> {
                    if (newLid != null) {
                        dc.setCurrentLid(newLid);
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
        cboGFilter.setItems(FXCollections.observableArrayList(Arrays.asList(Graad.values())));
        cboTFilter.setItems(FXCollections.observableArrayList(Arrays.asList(RolType.values())));
        cboGFilter.getSelectionModel().selectLast();
        cboTFilter.getSelectionModel().selectLast();
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

    public void verwijdergeselecteerdLid() {
        ILid lid = tableOverview.getSelectionModel().selectedItemProperty().get();
        tableOverview.getSelectionModel().clearSelection();
        if (lid != null) {
            dc.verwijderCurrentLid();
        }

    }

    @FXML
    private void filter(MouseEvent event) {
        String voornaamFilter = txfVnFilter.getText();
        String familienaamFilter = txfFnFilter.getText();
        Graad g = cboGFilter.getSelectionModel().getSelectedItem();
        RolType r = cboTFilter.getSelectionModel().getSelectedItem();
        String graadFilter = g == null ? "" : g.toString();
        String typeFilter = r == null ? "" : r.toString();

        dc.filter(voornaamFilter, familienaamFilter, graadFilter, typeFilter);
    }

    @FXML
    private void reset(MouseEvent event) {
        txfFnFilter.clear();
        cboGFilter.getSelectionModel().clearSelection();
        cboTFilter.getSelectionModel().clearSelection();
        txfVnFilter.clear();
        dc.filter("", "", Graad.ALLES.toString(), RolType.ALLES.toString());
        cboGFilter.getSelectionModel().selectLast();
        cboTFilter.getSelectionModel().selectLast();
    }
}

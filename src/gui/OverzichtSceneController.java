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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author IndyV
 */
public class OverzichtSceneController extends VBox implements PropertyChangeListener {

    private DomeinController dc;

    @FXML
    private TableView<Lid> tableOverview = new TableView<>();
    @FXML
    private TableColumn<Lid, String> colName = new TableColumn<>();
    @FXML
    private TableColumn<Lid, String> colBand = new TableColumn<>();
    private final DetailPaneelController dpc;
    @FXML
    private ComboBox<SorteerType> cboFilterOptie = new ComboBox<>();
    @FXML
    private TextField txtVan;
    @FXML
    private TextField txtTot;

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
        colName.setCellValueFactory(cellData -> cellData.getValue().getVoornaamProperty());
        colBand.setCellValueFactory(cellData -> cellData.getValue().getGraadProperty());
        cboFilterOptie.setItems(FXCollections.observableArrayList(SorteerType.values()));
        cboFilterOptie.getSelectionModel().selectFirst();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        update();

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
        if (lid != null) {
            dc.verwijderLid(lid);
            update();
        }

    }

    @FXML
    private void filter(KeyEvent event) {
        SorteerType type = cboFilterOptie.getSelectionModel().getSelectedItem();
        String tot = txtTot.getText();
        String van = txtVan.getText();
        if (!(tot == null || tot.isEmpty() || van == null || van.isEmpty()) && type == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error: geen sorteertype");
            alert.setHeaderText("Error: geen sorteertype meegegeven");
            alert.setContentText("Gelieve een sorteertype mee te geven");
        } else {
            dc.filter(cboFilterOptie.getSelectionModel().getSelectedItem(), txtVan.getText(), txtTot.getText());
        }

    }


    @FXML
    private void filterChange(MouseEvent event) {
        txtTot.clear();
        txtVan.clear();
        dc.filter(null,null,null);
    }


}

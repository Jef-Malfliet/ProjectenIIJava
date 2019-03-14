/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Graad;
import domein.IOefening;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import util.FullScreenResolution;

/**
 * FXML Controller class
 *
 * @author IndyV
 */
public class LesmateriaalOverzichtSceneController extends VBox{

    private double sceneWidth = FullScreenResolution.getWidth() / 10 * 4.25;
    private double sceneHeight = FullScreenResolution.getHeight();

    private DomeinController dc;
    @FXML
    private TableView<IOefening> tvOverzichtLesmateriaal;
    @FXML
    private TableColumn<IOefening, String> tcNaam;
    @FXML
    private TableColumn<IOefening, String> tcGraad;
    @FXML
    private TableColumn<IOefening, String> tcURL;
    @FXML
    private TextField txfNaam;
    @FXML
    private Button btnFilter;
    @FXML
    private ComboBox<Graad> cbFilterGraad;
    @FXML
    private Button btnMaakLeeg;

    public LesmateriaalOverzichtSceneController(DomeinController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LesmateriaalOverzichtScene.fxml"));
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
        setMaxScreen();
        tvOverzichtLesmateriaal.setItems(dc.getLesmateriaal());
        tcNaam.setCellValueFactory(cellData -> cellData.getValue().getNaamProperty());
        tcGraad.setCellValueFactory(cellData -> cellData.getValue().getGraadProperty());
        tcURL.setCellValueFactory(cellData -> cellData.getValue().getVideoProperty());

        tvOverzichtLesmateriaal.getSelectionModel().selectedItemProperty().addListener((observable, oldOef, newOef) -> {
            if (newOef != null) {
                dc.setCurrent_oefening(newOef);
            }
        });

        cbFilterGraad.setItems(FXCollections.observableArrayList(Graad.values()));

    }

    private void setMaxScreen() {
        tvOverzichtLesmateriaal.setPrefSize(sceneWidth, sceneHeight);
        tcNaam.setPrefWidth(sceneWidth / 3);
        tcGraad.setPrefWidth(sceneWidth / 3);
        tcURL.setPrefWidth(sceneWidth / 3);
    }


    @FXML
    private void filter(ActionEvent event) {
        String naam = txfNaam.getText();
        String graad="";
        if(cbFilterGraad.getSelectionModel().getSelectedItem()!=null){
            graad = cbFilterGraad.getSelectionModel().getSelectedItem().toString();
        }
        dc.filterOefening(naam, graad);
    }

    @FXML
    private void geenFilter(ActionEvent event) {
        txfNaam.clear();
        cbFilterGraad.getSelectionModel().clearSelection();
        dc.filterOefening("", "");
    }

}

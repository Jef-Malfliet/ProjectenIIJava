/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.IOefening;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
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
public class LesmateriaalOverzichtSceneController extends VBox implements PropertyChangeListener {

    private double sceneWidth = FullScreenResolution.getWidth() / 10 * 4.25;
    private double sceneHeight = FullScreenResolution.getHeight();

    private LesmateriaalDetailPaneelController ldpc;
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
    private TextField txfGraad;
    @FXML
    private Button btnFilter;


    public LesmateriaalOverzichtSceneController(DomeinController dc, LesmateriaalDetailPaneelController ldpc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LesmateriaalOverzichtScene.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.dc = dc;
        this.ldpc = ldpc;
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
                ldpc.fillDetailsMetGeselecteerdeOefening(newOef);
                ldpc.setCurrentOefening(newOef);
            }
        });
        

    }

    private void setMaxScreen() {
        tvOverzichtLesmateriaal.setPrefSize(sceneWidth, sceneHeight);
        tcNaam.setPrefWidth(sceneWidth / 3);
        tcGraad.setPrefWidth(sceneWidth / 3);
        tcURL.setPrefWidth(sceneWidth / 3);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        update();
    }

    private void update() {
        tvOverzichtLesmateriaal.setItems(dc.getLesmateriaal());
        tvOverzichtLesmateriaal.refresh();
        tvOverzichtLesmateriaal.getSelectionModel().clearSelection();
    }

    public void verwijderOefening() {
        IOefening oef = tvOverzichtLesmateriaal.getSelectionModel().selectedItemProperty().get();
        tvOverzichtLesmateriaal.getSelectionModel().clearSelection();
        ldpc.clearAll();
        if (oef != null) {
            dc.verwijderLesMateriaal(oef.getId());
        }
    }

    @FXML
    private void filter(ActionEvent event) {
        String naam = txfNaam.getText();
        String graad = txfGraad.getText();
        dc.filterOefening(naam, graad);
    }

}

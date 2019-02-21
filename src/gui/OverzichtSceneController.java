/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Lid;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author IndyV
 */
public class OverzichtSceneController extends VBox {

    private DomeinController dc;

    @FXML
    private TableView<Lid> tableOverview;
    @FXML
    private TableColumn<Lid, String> colName;
    @FXML
    private TableColumn<Lid, String> colBand;


    public OverzichtSceneController(DomeinController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtScene.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
        buildGui();
    }

    private void buildGui() {
        tableOverview.setItems(dc.getLeden());
        colName.setCellValueFactory(cellData->cellData.getValue().getNaamProperty());
        colBand.setCellValueFactory(cellData->cellData.getValue().getGraadProperty());
    }

}

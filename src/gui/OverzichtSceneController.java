/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Lid;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
public class OverzichtSceneController extends VBox implements PropertyChangeListener {

    private DomeinController dc;

    @FXML
    private TableView<Lid> tableOverview = new TableView<>();
    @FXML
    private TableColumn<Lid, String> colName = new TableColumn<>();
    @FXML
    private TableColumn<Lid, String> colBand = new TableColumn<>();
    private final DetailPaneelController dpc;

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
                        
                        int index = tableOverview.getSelectionModel().getSelectedIndex();
                        System.out.printf("%d %s\n", index, newLid);
                        
                        
                    }
                });

        tableOverview.setItems(dc.getLeden());
        colName.setCellValueFactory(cellData -> cellData.getValue().getVoornaamProperty());
        colBand.setCellValueFactory(cellData -> cellData.getValue().getGraadProperty());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
         tableOverview.setItems(dc.getLeden());
    }

}

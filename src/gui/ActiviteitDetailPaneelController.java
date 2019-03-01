/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Jef
 */
public class ActiviteitDetailPaneelController implements Initializable {

    @FXML
    private Label lblTitel;
    @FXML
    private Label lblStartdatum;
    @FXML
    private DatePicker dpStartdatum;
    @FXML
    private Label lblStartdatumFout;
    @FXML
    private Label lblEinddatum;
    @FXML
    private DatePicker dpEinddatum;
    @FXML
    private Label lblEinddatumFout;
    @FXML
    private Label lblStage;
    @FXML
    private CheckBox cbStage;
    @FXML
    private Label lblAlleLeden;
    @FXML
    private TableView<?> tvAlleLeden;
    @FXML
    private TableColumn<?, ?> tcVoornaamL;
    @FXML
    private TableColumn<?, ?> tcAchternaamL;
    @FXML
    private TableColumn<?, ?> tcGraadL;
    @FXML
    private Button btnInschrijven;
    @FXML
    private Button btnUitschrijven;
    @FXML
    private Label lblIngeschreven;
    @FXML
    private TableView<?> tvIngeschreven;
    @FXML
    private TableColumn<?, ?> tcVoonaamI;
    @FXML
    private TableColumn<?, ?> tcAchternaamI;
    @FXML
    private TableColumn<?, ?> tcGraadI;
    @FXML
    private Button btnNieuweActiviteit;
    @FXML
    private Button btnVerwijderActiviteit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void bevestigWijziging(ActionEvent event) {
    }

    @FXML
    private void annuleerwijziging(ActionEvent event) {
    }

    @FXML
    private void schrijfIn(ActionEvent event) {
    }

    @FXML
    private void schrijfUit(ActionEvent event) {
    }
    
}

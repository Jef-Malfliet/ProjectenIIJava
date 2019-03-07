/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.IActiviteit;
import domein.ILid;
import java.io.IOException;
import java.time.ZoneId;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import util.FullScreenResolution;

/**
 * FXML Controller class
 *
 * @author Jef
 */
public class ActiviteitDetailPaneelController extends VBox {

    private double sceneWidth = FullScreenResolution.getWidth() / 10 * 4.25;
    private double sceneHeight = FullScreenResolution.getHeight();

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
    private TableView<ILid> tvAlleLeden;
    @FXML
    private TableColumn<ILid, String> tcVoornaamL;
    @FXML
    private TableColumn<ILid, String> tcAchternaamL;
    @FXML
    private TableColumn<ILid, String> tcGraadL;
    @FXML
    private Button btnInschrijven;
    @FXML
    private Button btnUitschrijven;
    @FXML
    private Label lblIngeschreven;
    @FXML
    private TableView<ILid> tvIngeschreven;
    @FXML
    private TableColumn<ILid, String> tcVoonaamI;
    @FXML
    private TableColumn<ILid, String> tcAchternaamI;
    @FXML
    private TableColumn<ILid, String> tcGraadI;
    @FXML
    private Button btnNieuweActiviteit;
    @FXML
    private Button btnVerwijderActiviteit;

    private final DomeinController dc;
    @FXML
    private Label lblNaam;
    @FXML
    private Label lblNaamFout;
    @FXML
    private TextField tfNaam;

    public ActiviteitDetailPaneelController(DomeinController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ActiviteitDetailPaneel.fxml"));
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

    private void buildGui() {
        lblNaamFout.setVisible(false);
        lblStartdatumFout.setVisible(false);
        lblEinddatumFout.setVisible(false);
        
    }

    public void fillActiviteit(IActiviteit activiteit) {
        tfNaam.clear();
        dpStartdatum.getEditor().clear();
        dpEinddatum.getEditor().clear();
        cbStage.setSelected(false);

        tfNaam.setText(activiteit.getNaam());
        dpStartdatum.setValue(activiteit.getStartDatum().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        dpEinddatum.setValue(activiteit.getEindDatum().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        cbStage.setSelected(activiteit.isStage());

        System.out.println(dc.geefIngeschrevenLeden(activiteit.getId()));

        tvIngeschreven.setItems(FXCollections.observableArrayList(dc.geefIngeschrevenLeden(activiteit.getId())));
        tcVoonaamI.setCellValueFactory(cellData -> cellData.getValue().getVoornaamProperty());
        tcAchternaamI.setCellValueFactory(cellData -> cellData.getValue().getFamilienaamProperty());
        tcGraadI.setCellValueFactory(cellData -> cellData.getValue().getGraadProperty());
    }

}

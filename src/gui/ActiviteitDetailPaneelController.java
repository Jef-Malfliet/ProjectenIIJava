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
import javafx.scene.control.Tab;
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
    @FXML
    private Label lblMaxAanwezigen;
    @FXML
    private TextField tfNaam1;
    @FXML
    private Label lblMaxAanwezigenFout;
    @FXML
    private Tab tDetails;
    @FXML
    private Tab tInschrijven;
    @FXML
    private Label lblAllePersonen;
    @FXML
    private TableView<ILid> tblAlleLeden;
    @FXML
    private TableColumn<ILid, String> tcVoornaamA;
    @FXML
    private TableColumn<ILid, String> tcAchternaamA;
    @FXML
    private TableColumn<ILid, String> tcGraadA;
    @FXML
    private TableColumn<ILid, String> tcTypeA;
    @FXML
    private Button btnInschrijven;
    @FXML
    private Button btnUitschrijven;
    @FXML
    private Label lblIngeschreven;
    @FXML
    private TableView<ILid> tblIngeschreven;
    @FXML
    private TableColumn<ILid, String> tcVoornaamI;
    @FXML
    private TableColumn<ILid, String> tcAchternaamI;
    @FXML
    private TableColumn<ILid, String> tcGraadI;
    @FXML
    private TableColumn<ILid, String> tcTypeI;

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

    private void buildGui() {
        lblNaamFout.setVisible(false);
        lblStartdatumFout.setVisible(false);
        lblEinddatumFout.setVisible(false);

        lblTitel.setPrefWidth(sceneWidth);

        tblAlleLeden.setPrefSize(sceneWidth, sceneHeight);
        tblIngeschreven.setPrefSize(sceneWidth, sceneHeight);

        tcVoornaamA.prefWidthProperty().bind(tblAlleLeden.widthProperty().divide(4));
        tcAchternaamA.prefWidthProperty().bind(tblAlleLeden.widthProperty().divide(4));
        tcGraadA.prefWidthProperty().bind(tblAlleLeden.widthProperty().divide(4));
        tcTypeA.prefWidthProperty().bind(tblAlleLeden.widthProperty().divide(4));

        tcVoornaamI.prefWidthProperty().bind(tblIngeschreven.widthProperty().divide(4));
        tcAchternaamI.prefWidthProperty().bind(tblIngeschreven.widthProperty().divide(4));
        tcGraadI.prefWidthProperty().bind(tblIngeschreven.widthProperty().divide(4));
        tcTypeI.prefWidthProperty().bind(tblIngeschreven.widthProperty().divide(4));
        
        btnInschrijven.prefWidthProperty().bind(tblAlleLeden.widthProperty().divide(2).add(-10));
        btnUitschrijven.prefWidthProperty().bind(tblAlleLeden.widthProperty().divide(2).add(-10));
    }

    public void fillActiviteit(IActiviteit activiteit) {
        tfNaam.clear();
        dpStartdatum.getEditor().clear();
        dpEinddatum.getEditor().clear();
        cbStage.setSelected(false);

        tfNaam.setText(activiteit.getNaam());
        dpStartdatum.setValue(activiteit.getBeginDatum());
        dpEinddatum.setValue(activiteit.getEindDatum());
        cbStage.setSelected(activiteit.isStage());

        tblIngeschreven.setItems(FXCollections.observableArrayList(dc.geefIngeschrevenLeden(activiteit.getId())));
        tcVoornaamI.setCellValueFactory(cellData -> cellData.getValue().getVoornaamProperty());
        tcAchternaamI.setCellValueFactory(cellData -> cellData.getValue().getFamilienaamProperty());
        tcGraadI.setCellValueFactory(cellData -> cellData.getValue().getGraadProperty());
    }

}

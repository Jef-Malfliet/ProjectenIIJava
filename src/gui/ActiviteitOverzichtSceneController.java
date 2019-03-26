/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.ActiviteitType;
import domein.DomeinController;
import domein.IActiviteit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
 * @author Jef
 */
public class ActiviteitOverzichtSceneController extends VBox {

    private double sceneWidth = FullScreenResolution.getWidth() / 10 * 4.25;
    private double sceneHeight = FullScreenResolution.getHeight();

    @FXML
    private Label lblActiviteitenBeheren;
    @FXML
    private TableView<IActiviteit> tvActiviteiten;
    @FXML
    private TableColumn<IActiviteit, String> tcNaam;
    @FXML
    private TableColumn<IActiviteit, String> tcBegindatum;
    @FXML
    private TableColumn<IActiviteit, String> tcEinddatum;
    @FXML
    private TableColumn<IActiviteit, String> tcActiviteitType;

    private final DomeinController dc;
    @FXML
    private Label lblNaamFilter;
    @FXML
    private TextField txfNaamFilter;
    @FXML
    private Label lblTypeFilter;
    @FXML
    private ComboBox<ActiviteitType> cboTypeFilter;
    @FXML
    private Button btnFilter;
    @FXML
    private Button btnReset;
    @FXML
    private Label lblJaarFilter;
    @FXML
    private ComboBox<String> cboJaarFilter;

    public ActiviteitOverzichtSceneController(DomeinController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ActiviteitOverzichtScene.fxml"));
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
        tvActiviteiten.getSelectionModel().selectedItemProperty().addListener((observable, oldAct, newAct) -> {
            if (newAct != null) {
                dc.setCurrentActiviteit(newAct);
            }
        });
        setMaxScreen();
        tvActiviteiten.setItems(dc.getActiviteiten());
        tcNaam.setCellValueFactory(cellData -> cellData.getValue().getNaamProperty());
        tcBegindatum.setCellValueFactory(cellData -> cellData.getValue().getBeginDatumProperty());
        tcEinddatum.setCellValueFactory(cellData -> cellData.getValue().getEindDatumProperty());
        tcActiviteitType.setCellValueFactory(cellData -> cellData.getValue().getActiviteitTypeProperty());

        Set<String> jaarSet = new HashSet<>();
        dc.getActiviteiten().stream().forEach(a -> {
            jaarSet.add(String.format("%d", a.getBeginDatum().getYear()));
        });
        
        List<String> jaarList = new ArrayList<>(jaarSet);
        jaarList.add("ALLES");
        ((SortedList) dc.getActiviteiten()).comparatorProperty().bind(tvActiviteiten.comparatorProperty());
        cboTypeFilter.setItems(FXCollections.observableArrayList(Arrays.asList(ActiviteitType.values())));
        cboTypeFilter.getSelectionModel().selectLast();
        cboJaarFilter.setItems(FXCollections.observableArrayList(jaarList));
        cboJaarFilter.getSelectionModel().selectLast();
    }

    private void setMaxScreen() {
        lblActiviteitenBeheren.setPrefWidth(sceneWidth);
        tvActiviteiten.setPrefWidth(sceneWidth);
        tvActiviteiten.setPrefHeight(sceneHeight);
        // 4 kolommen, dus 1/4 van de tableview.
        tcNaam.prefWidthProperty().bind(tvActiviteiten.widthProperty().divide(4));
        tcBegindatum.prefWidthProperty().bind(tvActiviteiten.widthProperty().divide(4));
        tcEinddatum.prefWidthProperty().bind(tvActiviteiten.widthProperty().divide(4));
        tcActiviteitType.prefWidthProperty().bind(tvActiviteiten.widthProperty().divide(4));
    }

    public void verwijderGeselecteerdeActiviteit() {
        IActiviteit activiteit = tvActiviteiten.getSelectionModel().selectedItemProperty().get();
        tvActiviteiten.getSelectionModel().clearSelection();
        if (activiteit != null) {
            dc.verwijderCurrentActiviteit();
        }
    }

    @FXML
    private void filter(MouseEvent event) {
        String naamFilter = txfNaamFilter.getText();
        String typeFilter = cboTypeFilter.getSelectionModel() != null ? cboTypeFilter.getSelectionModel().getSelectedItem().toString() : "";
        String datumFilter = cboJaarFilter.getSelectionModel() != null ? cboJaarFilter.getSelectionModel().getSelectedItem() : "ALLES";
        dc.filterActiviteit(naamFilter, typeFilter, datumFilter);
        tvActiviteiten.getSelectionModel().selectFirst();
        dc.setCurrentActiviteit(tvActiviteiten.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void reset(MouseEvent event) {
        txfNaamFilter.clear();
        cboTypeFilter.getSelectionModel().clearSelection();
        cboJaarFilter.getSelectionModel().clearSelection();
        dc.filterActiviteit("", ActiviteitType.ALLES.toString(), "ALLES");
        cboTypeFilter.getSelectionModel().selectLast();
        cboJaarFilter.getSelectionModel().selectLast();
    }
}

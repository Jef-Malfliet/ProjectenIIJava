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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
        tvActiviteiten.getSelectionModel().selectFirst();
        dc.setCurrentActiviteit((IActiviteit) tvActiviteiten.getSelectionModel().getSelectedItem());
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

}

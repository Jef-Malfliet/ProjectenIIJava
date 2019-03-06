/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.IActiviteit;
import java.io.IOException;
import java.util.Date;
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
    private TableColumn<IActiviteit, Date> tcStartdatum;
    @FXML
    private TableColumn<IActiviteit, Date> tcEinddatum;
    @FXML
    private TableColumn<IActiviteit, String> tcStage;

    private final DomeinController dc;
    private final ActiviteitDetailPaneelController adpc;

    public ActiviteitOverzichtSceneController(DomeinController dc, ActiviteitDetailPaneelController adpc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ActiviteitOverzichtScene.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.dc = dc;
        this.adpc = adpc;
        buildGui();
    }

    private void buildGui() {
        tvActiviteiten.getSelectionModel().selectedItemProperty().addListener((observable, oldAct, newAct) -> {
            if (newAct != null) {
                adpc.fillActiviteit(newAct);
            }
        });

        tvActiviteiten.setItems(dc.getActiviteiten());
    }

    private void setMaxScreen() {
        lblActiviteitenBeheren.setPrefWidth(sceneWidth);
        tvActiviteiten.setPrefWidth(sceneWidth);
        tvActiviteiten.setPrefHeight(sceneHeight);
        // 4 kolommen, dus 1/4 van de tableview.
        tcNaam.prefWidthProperty().bind(tvActiviteiten.widthProperty().divide(4));
        tcStartdatum.prefWidthProperty().bind(tvActiviteiten.widthProperty().divide(4));
        tcEinddatum.prefWidthProperty().bind(tvActiviteiten.widthProperty().divide(4));
        tcStage.prefWidthProperty().bind(tvActiviteiten.widthProperty().divide(4));

    }
}
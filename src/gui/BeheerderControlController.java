/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author IndyV
 */
public class BeheerderControlController extends VBox {

    //Nodige attributen voor de functionaliteiten van de knoppen.
    //Gegenereerde GUI Components vanuit Scenebuilder.
    @FXML
    private HBox btnOverzichtenRaadplegen;
    @FXML
    private HBox btnLedenBeheren;
    @FXML
    private HBox btnStateActiviteitBeheren;
    @FXML
    private HBox btnLesmateriaalBeheren;

    private DomeinController dc;
    private MainPanel mp;
    private OverzichtSceneController osc;
    private OverzichtOpvraagSceneController opsc;

    /**
     * Constructor beheerderControlController
     *
     * Initialiseert de GUI en zijn nodige dependencies.
     */
    public BeheerderControlController(DomeinController dc, OverzichtSceneController osc, MainPanel mp, OverzichtOpvraagSceneController opsc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BeheerderControl.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.osc = osc;
        this.dc = dc;
        this.mp = mp;
        this.opsc = opsc;
        buildGui();
    }

    /**
     * Genereert de visuele componenten en voegt de functionaliteiten toe.
     *
     */
    public void buildGui() {
        //Adding click events for buttons.
//        btnInschrijvenLid.setOnMouseClicked(e -> {
//            schrijfLidIn();
//        });
//        btnVerwijderenLid.setOnMouseClicked(e -> {
//            verwijderLid();
//        });
        changeBackgroundToRed(btnLedenBeheren);
        btnOverzichtenRaadplegen.setOnMouseClicked(e -> {
            overzichtRaadplegen();
            mp.setCenter(opsc);
        });

        btnLedenBeheren.setOnMouseClicked(e -> {
            ledenBeheren();
            mp.setCenter(osc);
            mp.setRight(osc.getDpc());
        });

        btnLesmateriaalBeheren.setOnMouseClicked(e -> {
            lesmateriaalBeheren();
        });

        btnStateActiviteitBeheren.setOnMouseClicked(e -> {
            activiteitenBeheren();
        });

    }

    /**
     * Wordt opgeroepen door btnInschrijvenLid.
     *
     * Deze functie schrijft een lid in en voegt het lid toe in de databank.
     */
//
//    private void verwijderLid() {
////        resetStyle();
////        changeBackgroundToRed(btnVerwijderenLid);
//        osc.verwijdergeselecteerdLid();
//    }
    private void overzichtRaadplegen() {
        resetStyle();
        changeBackgroundToRed(btnOverzichtenRaadplegen);
    }

    private void ledenBeheren() {
        resetStyle();
        changeBackgroundToRed(btnLedenBeheren);
    }

    private void activiteitenBeheren() {
        resetStyle();
        changeBackgroundToRed(btnStateActiviteitBeheren);
    }

    private void lesmateriaalBeheren() {
        resetStyle();
        changeBackgroundToRed(btnLesmateriaalBeheren);
    }

    private void resetStyle() {
        String style = "-fx-background-color:#484857";
//        btnInschrijvenLid.setStyle(style);
//        btnVerwijderenLid.setStyle(style);
        btnLedenBeheren.setStyle(style);
        btnLesmateriaalBeheren.setStyle(style);
        btnStateActiviteitBeheren.setStyle(style);
        btnOverzichtenRaadplegen.setStyle(style);
    }

    private void changeBackgroundToRed(HBox hbox) {
        hbox.setStyle("-fx-background-color: red");
    }
}

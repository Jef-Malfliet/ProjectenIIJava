/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private HBox btnInschrijvenLid;
    @FXML
    private HBox btnOverzichtenRaadplegen;
    @FXML
    private HBox btnLedenBeheren;
    @FXML
    private HBox btnStateActiviteitBeheren;
    @FXML
    private HBox btnLesmateriaalBeheren;

    private OverzichtSceneController oc;
    @FXML
    private HBox btnVerwijderenLid;

    private DomeinController dc;
    private MainPanel mp;

    /**
     * Constructor beheerderControlController
     *
     * Initialiseert de GUI en zijn nodige dependencies.
     */
    public BeheerderControlController(DomeinController dc, OverzichtSceneController oc, MainPanel mp) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BeheerderControl.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.oc = oc;
        this.dc = dc;
        this.mp = mp;
        buildGui();
    }

    /**
     * Genereert de visuele componenten en voegt de functionaliteiten toe.
     *
     */
    public void buildGui() {
        //Adding click events for buttons.
        btnInschrijvenLid.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                schrijfLidIn();
            }

        });
        btnVerwijderenLid.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                verwijderLid();
            }

        });
        btnOverzichtenRaadplegen.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                overzichtRaadplegen();
                OverzichtOpvraagSceneController opsc = new OverzichtOpvraagSceneController(dc);
                mp.setCenter(opsc);
            }
        });

    }

    /**
     * Wordt opgeroepen door btnInschrijvenLid.
     *
     * Deze functie schrijft een lid in en voegt het lid toe in de databank.
     */
    private void schrijfLidIn() {
        resetStyle();
        changeBackgroundToRed(btnInschrijvenLid);
        oc.getDpc().nieuwLid();

    }

    private void verwijderLid() {
        resetStyle();
        changeBackgroundToRed(btnVerwijderenLid);
        oc.verwijdergeselecteerdLid();
    }

    private void overzichtRaadplegen() {
        resetStyle();
        changeBackgroundToRed(btnOverzichtenRaadplegen);
    }

    private void resetStyle() {
        String style = "-fx-background-color:#484857";
        btnInschrijvenLid.setStyle(style);
        btnVerwijderenLid.setStyle(style);
        btnOverzichtenRaadplegen.setStyle(style);
    }

    private void changeBackgroundToRed(HBox hbox) {
        hbox.setStyle("-fx-background-color: red");
    }
}

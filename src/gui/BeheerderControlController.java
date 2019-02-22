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
import javafx.scene.input.MouseEvent;
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
    /**
     * Constructor beheerderControlController
     *
     * Initialiseert de GUI en zijn nodige dependencies.
     */
    public BeheerderControlController(DomeinController dc,OverzichtSceneController oc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BeheerderControl.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.oc = oc;
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
        
        
    }

    /**
     * Wordt opgeroepen door btnInschrijvenLid.
     *
     * Deze functie schrijft een lid in en voegt het lid toe in de databank.
     */
    private void schrijfLidIn() {
        btnInschrijvenLid.setStyle("-fx-background-color: red");
        oc.getDpc().nieuwLid();
        
    }
    private void verwijderLid() {
        btnInschrijvenLid.setStyle("-fx-background-color: red");
        oc.verwijdergeselecteerdLid();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Graad;
import domein.IOefening;
import domein.Oefening;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;
import util.FullScreenResolution;

/**
 * FXML Controller class
 *
 * @author IndyV
 */
public class LesmateriaalDetailPaneelController extends VBox {

    private double sceneWidth = FullScreenResolution.getWidth() / 10 * 4.25;
    private double sceneHeight = FullScreenResolution.getHeight();

    private DomeinController dc;
    private List<File> imageFiles;
    @FXML
    private ComboBox<Graad> cbMinimumgraad;
    @FXML
    private TextField txfNaam;
    @FXML
    private TextArea txaUitleg;
    @FXML
    private Button btnFoto;
    @FXML
    private TextField txfVideoURL;
    @FXML
    private Button btnMaakOefening;
    private WebView wvYoutube;
    @FXML
    private VBox vbImages;
    @FXML
    private Label lblTitel;
    @FXML
    private Button btnverwijderOefening;
    @FXML
    private Button btnBevestig;
    private boolean nieuweOefening;
    private LesmateriaalOverzichtSceneController losc;
    boolean geldig = false;

    public LesmateriaalDetailPaneelController(DomeinController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LesmateriaalDetailPaneel.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.dc = dc;
        imageFiles = new ArrayList<>();
        buildGui();
    }

    private void buildGui() {
        cbMinimumgraad.setItems(FXCollections.observableArrayList(Graad.values()));
    }

    @FXML
    private void maakOefening(ActionEvent event) {
        clearAll();
        nieuweOefening = true;
        lblTitel.setText("Maak een nieuwe oefening");
    }

    private boolean validate() {
        String naam = txfNaam.getText();
        if (naam == null || naam.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fout");
            alert.setContentText("Naam moet ingevuld zijn!");
            alert.showAndWait();
            return false;
        }
        Graad graad = cbMinimumgraad.getValue();
        if (graad == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fout");
            alert.setContentText("Graad moet ingevuld zijn!");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    @FXML
    private void kiesFoto(ActionEvent event) {

        //Image van de gebruiker nemen.
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Kies foto");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());
        imageFiles.add(selectedFile);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(selectedFile);
        } catch (IOException ex) {
            Logger.getLogger(LesmateriaalDetailPaneelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image image = SwingFXUtils.toFXImage(bi, null);
        fillImageInVBImages(image);
    }

    public void fillOefening(IOefening newOef) {
        clearAll();
        nieuweOefening = false;
        lblTitel.setText("Wijzig oefening");
        cbMinimumgraad.setValue(newOef.getGraad());
        txfNaam.setText(newOef.getNaam());
        txaUitleg.setText(newOef.getUitleg());
        txfVideoURL.setText(newOef.getVideo());
        if (newOef.getImages() != null) {
            for (Image image : newOef.getImages()) {
                fillImageInVBImages(image);
            }
        }
    }

    private void fillImageInVBImages(Image image) {
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        vbImages.getChildren().add(imageView);
    }

    public void clearAll() {
        cbMinimumgraad.setValue(null);
        txfNaam.clear();
        txaUitleg.clear();
        txfVideoURL.clear();
        vbImages.getChildren().clear();
    }

    @FXML
    private void verwijderOefening(ActionEvent event) {
        if (!nieuweOefening) {
            losc.verwijderOefening();
        } else {
            clearAll();
        }
    }

    public void setLesmateriaalOverzicht(LesmateriaalOverzichtSceneController losc) {
        this.losc = losc;
    }

    @FXML
    private void bevestig(ActionEvent event) {

        Graad graad = cbMinimumgraad.getValue();
        String naam = txfNaam.getText();
        String uitleg = txaUitleg.getText();
        String urlVideo = txfVideoURL.getText();
        Oefening temp = null;
        try {
            temp = new Oefening(graad, naam);
            geldig = true;
        } catch (IllegalArgumentException e) {
            validate();
        }
        if (geldig) {
            if (uitleg != null) {
                temp.addUitleg(uitleg);
            }
            if (urlVideo != null) {
                temp.addVideo(urlVideo);
            }
            if (imageFiles != null && !imageFiles.isEmpty()) {
                for (File file : imageFiles) {
                    temp.addImage(file);
                }
            }

            if (nieuweOefening) {
                System.out.println("ADDED");
                dc.addLesMateriaal(temp);
                
            } else {
                System.out.println("CHANGED");
                dc.wijzigLesMateriaal(temp);
            }
        }
        
        clearAll();

    }
}

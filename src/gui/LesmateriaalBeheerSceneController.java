/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Graad;
import domein.Oefening;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class LesmateriaalBeheerSceneController extends VBox {

    private double sceneWidth = FullScreenResolution.getWidth() / 10 * 4.25;
    private double sceneHeight = FullScreenResolution.getHeight();

    private DomeinController dc;
    private File imageFile;
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
    @FXML
    private Label lblWarning;
    @FXML
    private ImageView ivPhoto;
    @FXML
    private Button btnHaalOp;
    @FXML
    private WebView wvYoutube;

    public LesmateriaalBeheerSceneController(DomeinController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LesmateriaalBeheerScene.fxml"));
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
        cbMinimumgraad.setItems(FXCollections.observableArrayList(Graad.values()));
        setMaxScreen();
    }

    @FXML
    private void maakOefening(ActionEvent event) {
        validate();
        Graad graad = cbMinimumgraad.getValue();
        String naam = txfNaam.getText();
        String uitleg = txaUitleg.getText();
        String urlVideo = txfVideoURL.getText();
        File imageFile = this.imageFile;
        Oefening oefening = new Oefening(graad, naam);
        if (uitleg != null) {
            oefening.addUitleg(uitleg);
        }
        if (urlVideo != null) {
            oefening.addVideo(urlVideo);
        }
        if (imageFile != null) {
            oefening.addImage(imageFile);
        }

        dc.addLesMateriaal(oefening);
    }

    private void validate() {
        String naam = txfNaam.getText();
        if (naam == null) {
            lblWarning.setText("Naam mag niet leeg zijn");
        }
    }

    @FXML
    private void kiesFoto(ActionEvent event) {

        //Image van de gebruiker nemen.
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Kies foto");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());
        this.imageFile = selectedFile;
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(selectedFile);
        } catch (IOException ex) {
            Logger.getLogger(LesmateriaalBeheerSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image image = SwingFXUtils.toFXImage(bi, null);
    }

    @FXML
    private void haalImageOp(ActionEvent event) {
        Oefening oef = dc.getOefening(1L);
        Image image = oef.getImage();
        this.ivPhoto.setImage(image);

        WebEngine webEngine = wvYoutube.getEngine();
        wvYoutube.setPrefSize(840, 690);
        webEngine.load("youtube.com/watch_popup?v=GKiHB5AzihE");

    }

    private void setMaxScreen() {

    }
}

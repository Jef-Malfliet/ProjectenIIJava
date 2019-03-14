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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.FullScreenResolution;

/**
 * FXML Controller class
 *
 * @author IndyV
 */
public class LesmateriaalDetailPaneelController extends VBox implements PropertyChangeListener {

    private DomeinController dc;
    private List<String> imagePaths;
    boolean geldig = false;
    VBox box = new VBox();

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
    private ScrollPane vbImages;
    @FXML
    private Label lblTitel;
    @FXML
    private WebView youtube;
    @FXML
    private Button btnVerwijderOef;
    @FXML
    private Button btnNieuweOefening;
    @FXML
    private Button btnBevestig;

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
        imagePaths = new ArrayList<>();
        buildGui();
        clearAll();
    }

    private void buildGui() {
        cbMinimumgraad.setItems(FXCollections.observableArrayList(Graad.values()));
        box = new VBox();
        youtube.setPrefSize(FullScreenResolution.getWidth() / 10 * 4.25, FullScreenResolution.getHeight());
    }

    @FXML
    private void maakOefening(ActionEvent event) {
        clearAll();
        dc.setCurrent_oefening(null);
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

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Kies foto");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());
        String imagepath = null;
        try {
            imagepath = selectedFile.toURI().toURL().toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(LesmateriaalDetailPaneelController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fout");
            alert.setContentText("Geen foto gekozen.");
            alert.showAndWait();
        }
        if (imagepath != null) {
            toonGeselecteerdeFoto(imagepath);
        } else {
            System.out.println("Geen foto gekozen!");
        }
    }

    private void toonGeselecteerdeFoto(String imagepath) {
        Image image = new Image(imagepath);
        this.imagePaths.add(imagepath);
        fillBoxWithImages(box, imagepath);
        vbImages.setContent(box);
    }

    public void fillDetailsMetGeselecteerdeOefening(IOefening newOef) {
        clearAll();
        lblTitel.setText("Wijzig oefening");
        cbMinimumgraad.setValue(newOef.getGraad());
        txfNaam.setText(newOef.getNaam());
        txaUitleg.setText(newOef.getUitleg());
        txfVideoURL.setText(newOef.getVideo());
        youtube.getEngine().load(newOef.getVideo());
        if (newOef.getImages()!= null) {
            for (String imagePath : newOef.getImages()) {
                fillBoxWithImages(box, imagePath);
            }
            vbImages.setContent(box);
        }

    }

    private void fillBoxWithImages(VBox box, String imagePath) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        box.getChildren().add(imageView);
    }

    public void clearAll() {
        cbMinimumgraad.setValue(null);
        imagePaths.clear();
        txfNaam.clear();
        txaUitleg.clear();
        txfVideoURL.clear();
        box.getChildren().clear();
        vbImages.setContent(null);
        youtube.getEngine().loadContent("");
        lblTitel.setText("Maak een nieuwe oefening aan.");
    }

    @FXML
    private void verwijderOefening(ActionEvent event) {
        IOefening oef = dc.getCurrent_oefening();
        dc.verwijderLesMateriaal(oef.getId());
        dc.setCurrent_oefening(null);
        clearAll();
    }


    @FXML
    private void bevestig(ActionEvent event) {

        Graad graad = cbMinimumgraad.getValue();
        String naam = txfNaam.getText();
        String uitleg = txaUitleg.getText();
        String urlVideo = txfVideoURL.getText();
        Oefening nieuweWaardes = null;

        try {
            nieuweWaardes = new Oefening(graad, naam);
            geldig = true;
        } catch (IllegalArgumentException e) {
            validate();
        }
        if (geldig && nieuweWaardes != null) {
            if (uitleg != null) {
                nieuweWaardes.setUitleg(uitleg);
            }
            if (urlVideo != null && !urlVideo.isEmpty()) {
                try {
                    nieuweWaardes.setVideo(urlVideo);
                } catch (IllegalArgumentException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Fout");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }
            if (!imagePaths.isEmpty()) {
                List<String> paths = new ArrayList<>();
                for (String path : imagePaths) {
                    if (path != null) {
                        paths.add(path);
                    }
                }
                nieuweWaardes.setImages(paths);
            }

            if (dc.getCurrent_oefening() == null) {
                addNieuwLesmateriaal(nieuweWaardes);

            } else {
                wijzigLesmateriaal(nieuweWaardes);
            }
        }
        
        clearAll();

    }

    private void addNieuwLesmateriaal(Oefening nieuweWaardes) {
        dc.addLesMateriaal(nieuweWaardes);
        clearAll();
    }

    private void wijzigLesmateriaal(Oefening nieuweWaardes) {
        dc.wijzigLesMateriaal(nieuweWaardes, dc.getCurrent_oefening());
        clearAll();
    }

    public void setCurrentOefening(IOefening oefening) {
        dc.setCurrent_oefening(oefening);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        update();
    }

    private void update() {
        if (dc.getCurrent_oefening() != null) {
            fillDetailsMetGeselecteerdeOefening(dc.getCurrent_oefening());
        }
    }
}

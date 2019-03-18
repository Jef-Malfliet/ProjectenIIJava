/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Activiteit;
import domein.ActiviteitType;
import domein.DomeinController;
import domein.Exportable;
import domein.ExportableActiviteit;
import domein.ExportableKampioenschap;
import domein.ExportableLidAanwezigheden;
import domein.ExportableLidInschrijvingen;
import domein.ExportableOefening;
import domein.Graad;
import domein.IActiviteit;
import domein.IKampioenschap;
import domein.ILid;
import domein.IOefening;
import domein.Kampioenschap;
import domein.LeeftijdCategorie;
import domein.LesType;
import domein.Lid;
import domein.Oefening;
import domein.SorteerType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import util.FullScreenResolution;

/**
 * FXML Controller class
 *
 * @author Mout
 */
public class OverzichtOpvraagSceneController extends HBox {

    @FXML
    private ComboBox<SorteerType> cboType;

    private final DomeinController dc;
    @FXML
    private TextField txtBesNaam;

    private String path = "";
    private double sceneWidth = FullScreenResolution.getWidth() / 10 * 4.25;
    private double sceneHeight = FullScreenResolution.getHeight();
    @FXML
    private Label lblOverzichtOpvragen;
    @FXML
    private Label lblOverzichtRaadplegen;
    @FXML
    private Label lblOverzicht;
    @FXML
    private Label lblBesNaam;

    private final List<String> extraParameters;
    @FXML
    private Label lblExtraParameters;
    @FXML
    private HBox hBoxTableContainer;
    @FXML
    private VBox vBoxContainer;
    @FXML
    private HBox hBoxTopRow;
    @FXML
    private HBox hBoxUnderRow;
    @FXML
    private Button btnMaak;
    @FXML
    private Button btnLocation;
    @FXML
    private Button btnPreview;

    private ComboBox<LesType> cboFormule;
    private TextField txfNaam;
    private ComboBox<ActiviteitType> cboActiviteitType;
    private ComboBox<Graad> cboGraad;
    private DatePicker dpStartDatum;
    private DatePicker dpEindDatum;
    private ComboBox<LeeftijdCategorie> cboLeeftijd;
    @FXML
    private Button btnClear;

    public OverzichtOpvraagSceneController(DomeinController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtOpvraagScene.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        this.dc = dc;
        buildGui();
        extraParameters = new ArrayList<>();
        cboType.valueProperty().addListener((ObservableValue<? extends SorteerType> observable, SorteerType oldValue, SorteerType newValue) -> {
            System.out.println(newValue);
            makeExtraParamScreen(newValue);
        });

    }

    @FXML
    private <T extends Exportable> void maakOverzicht(ActionEvent event) {
        SorteerType type = cboType.getSelectionModel().getSelectedItem();
        if (type == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error: geen overzichtstype");
            alert.setContentText("Geen overzichtstype gekozen");
            alert.showAndWait();
        } else {
            fillParameters(type);
            List<T> overzicht = dc.maakOverzichtList(type, extraParameters);
            placeTable(type, FXCollections.observableArrayList(overzicht));
        }
    }

    private void maakOverzicht(SorteerType type, ObservableList list) {
        placeTable(type, list);
    }

    @FXML
    private void openDirectoryChooser(ActionEvent event) {
        DirectoryChooser dir = new DirectoryChooser();
        File selectedDir = dir.showDialog(this.getScene().getWindow());
        if (selectedDir == null) {
            path += System.getProperty("user.home");
            path += "/Documents/";
        } else {
            path = selectedDir.getPath();
        }

        String besNaam = txtBesNaam.getText();
        if (besNaam == null || besNaam.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Geen naam gevonden voor het bestand.");
            alert.showAndWait();
        } else {
            path += "/" + besNaam + ".xls" + "/";
        }
    }

    private void buildGui() {
        cboType.setItems(FXCollections.observableArrayList(Arrays.asList(SorteerType.values()).subList(1, SorteerType.values().length)));
        cboFormule = new ComboBox();
        cboFormule.setItems(FXCollections.observableArrayList(Arrays.asList(LesType.values())));
        cboFormule.getSelectionModel().selectLast();
        cboActiviteitType = new ComboBox();
        cboActiviteitType.setItems(FXCollections.observableArrayList(Arrays.asList(ActiviteitType.values())));
        cboActiviteitType.getSelectionModel().selectLast();
        cboGraad = new ComboBox();
        cboGraad.setItems(FXCollections.observableArrayList(Arrays.asList(Graad.values())));
        cboGraad.getSelectionModel().selectLast();
        txfNaam = new TextField();
        dpStartDatum = new DatePicker();
        dpEindDatum = new DatePicker();
        cboLeeftijd = new ComboBox();
        cboLeeftijd.setItems(FXCollections.observableArrayList(Arrays.asList(LeeftijdCategorie.values())));
        cboLeeftijd.getSelectionModel().selectLast();
        setMaxScreen();

    }

    private void setMaxScreen() {
        lblOverzichtOpvragen.setMinWidth(sceneWidth);
        lblOverzichtRaadplegen.setMinWidth(sceneWidth);
        cboType.setPrefWidth(sceneWidth / 4.5);
        lblOverzicht.setPrefWidth(sceneWidth / 5);
        lblBesNaam.setPrefWidth(sceneWidth / 5);
        txtBesNaam.setPrefWidth(sceneWidth / 5);
        btnLocation.setPrefWidth(sceneWidth / 5);
        btnPreview.setPrefWidth(sceneWidth / 4.5);
        btnMaak.setPrefWidth(sceneWidth / 5);
        btnClear.setPrefWidth(sceneWidth / 4.5);
        lblExtraParameters.setPrefWidth(sceneWidth / 2);
        cboActiviteitType.setPrefWidth(sceneWidth / 4.5);
        dpEindDatum.setPrefWidth(sceneWidth / 4.5);
        dpStartDatum.setPrefWidth(sceneWidth / 4.5);
        cboFormule.setPrefWidth(sceneWidth / 4.5);
        cboLeeftijd.setPrefWidth(sceneWidth / 4.5);
        txfNaam.setMaxWidth(195);
    }

    private void makeExtraParamScreen(SorteerType type) {
        VBox vbLeft1 = new VBox();
        VBox.setMargin(vbLeft1, new Insets(10));

        VBox vbLeft2 = new VBox();
        VBox.setMargin(vbLeft2, new Insets(10));

        VBox vbRight1 = new VBox();
        VBox.setMargin(vbRight1, new Insets(10));

        VBox vbRight2 = new VBox();
        VBox.setMargin(vbRight1, new Insets(10));

        Label lblNaam = new Label();
        Label lblDatum1 = new Label();
        Label lblDatum2 = new Label();
        Label lblComboBox = new Label();

        lblNaam.setPrefWidth(sceneWidth / 4.5);
        lblDatum1.setPrefWidth(sceneWidth / 4.5);
        lblDatum2.setPrefWidth(sceneWidth / 4.5);
        lblComboBox.setPrefWidth(sceneWidth / 4.5);

        switch (type) {
            case AANWEZIGHEID:
                hBoxTopRow.getChildren().clear();
                hBoxUnderRow.getChildren().clear();
                vBoxContainer.getChildren().clear();

                vbLeft1.getChildren().clear();
                lblNaam.setText("Op lid");
                vbLeft1.getChildren().addAll(lblNaam, txfNaam);

                vbRight1.getChildren().clear();
                lblDatum1.setText("Op datum");
                vbRight1.getChildren().addAll(lblDatum1, dpStartDatum);

                vbLeft2.getChildren().clear();
                lblComboBox.setText("Op formule");
                vbLeft2.getChildren().addAll(lblComboBox, cboFormule);

                hBoxTopRow.getChildren().addAll(vbLeft1, vbRight1);
                hBoxUnderRow.getChildren().addAll(vbLeft2, vbRight2);
                vBoxContainer.getChildren().addAll(hBoxTopRow, hBoxUnderRow);
                break;
            case ACTIVITEIT:
                hBoxTopRow.getChildren().clear();
                hBoxUnderRow.getChildren().clear();
                vBoxContainer.getChildren().clear();

                vbLeft1.getChildren().clear();
                lblNaam.setText("Op activiteitnaam");
                vbLeft1.getChildren().addAll(lblNaam, txfNaam);

                vbRight1.getChildren().clear();
                lblDatum1.setText("Op startdatum");
                vbRight1.getChildren().addAll(lblDatum1, dpStartDatum);

                vbLeft2.getChildren().clear();
                lblComboBox.setText("Op type");
                vbLeft2.getChildren().addAll(lblComboBox, cboActiviteitType);

                vbRight2.getChildren().clear();
                lblDatum2.setText("Op einddatum");
                vbRight2.getChildren().addAll(lblDatum2, dpEindDatum);

                hBoxTopRow.getChildren().addAll(vbLeft1, vbRight1);
                hBoxUnderRow.getChildren().addAll(vbLeft2, vbRight2);
                vBoxContainer.getChildren().addAll(hBoxTopRow, hBoxUnderRow);
                break;
            case CLUBKAMPIOENSCHAP:
                hBoxTopRow.getChildren().clear();
                hBoxUnderRow.getChildren().clear();
                vBoxContainer.getChildren().clear();

                vbLeft1.getChildren().clear();
                lblNaam.setText("Op kampioenschapnaam");
                vbLeft1.getChildren().addAll(lblNaam, txfNaam);

                vbRight1.getChildren().clear();
                lblDatum1.setText("Op datum");
                vbRight1.getChildren().addAll(lblDatum1, dpStartDatum);

                vbLeft2.getChildren().clear();
                lblComboBox.setText("Op leeftijdscategorie");
                vbLeft2.getChildren().addAll(lblComboBox, cboLeeftijd);

                hBoxTopRow.getChildren().addAll(vbLeft1, vbRight1);
                hBoxUnderRow.getChildren().addAll(vbLeft2, vbRight2);
                vBoxContainer.getChildren().addAll(hBoxTopRow, hBoxUnderRow);
                break;
            case INSCHRIJVING:
                hBoxTopRow.getChildren().clear();
                hBoxUnderRow.getChildren().clear();
                vBoxContainer.getChildren().clear();

                vbLeft1.getChildren().clear();
                lblNaam.setText("Op lid");
                vbLeft1.getChildren().addAll(lblNaam, txfNaam);

                vbRight1.getChildren().clear();
                lblDatum1.setText("Op datum");
                vbRight1.getChildren().addAll(lblDatum1, dpStartDatum);

                vbLeft2.getChildren().clear();
                lblComboBox.setText("Op formule");
                vbLeft2.getChildren().addAll(lblComboBox, cboFormule);

                hBoxTopRow.getChildren().addAll(vbLeft1, vbRight1);
                hBoxUnderRow.getChildren().addAll(vbLeft2, vbRight2);
                vBoxContainer.getChildren().addAll(hBoxTopRow, hBoxUnderRow);
                break;
            case LESMATERIAAL:
                hBoxTopRow.getChildren().clear();
                hBoxUnderRow.getChildren().clear();
                vBoxContainer.getChildren().clear();

                vbLeft1.getChildren().clear();
                lblNaam.setText("Op oefeningnaam");
                vbLeft1.getChildren().addAll(lblNaam, txfNaam);

                vbRight1.getChildren().clear();
                lblComboBox.setText("Op graad");
                vbRight1.getChildren().addAll(lblComboBox, cboFormule);

                hBoxTopRow.getChildren().addAll(vbLeft1, vbRight1);
                hBoxUnderRow.getChildren().addAll(vbLeft2, vbRight2);
                vBoxContainer.getChildren().addAll(hBoxTopRow, hBoxUnderRow);
                break;
        }
    }

    private void placeTable(SorteerType type, ObservableList list) {
        Label lblAanwezigheden = new Label("Aanwezigheden");
        ListView<String> lstAanwezigheden = new ListView<>();
        lstAanwezigheden.setPrefHeight(sceneHeight - 100);
        lstAanwezigheden.setMaxHeight(sceneHeight - 100);

        VBox vbAanwezigheden = new VBox();
        vbAanwezigheden.setSpacing(10);
        vbAanwezigheden.getChildren().addAll(lblAanwezigheden, lstAanwezigheden);
        switch (type) {
            case ACTIVITEIT:
                hBoxTableContainer.getChildren().clear();
                TableView<IActiviteit> tblActiviteiten = new TableView<>();
                tblActiviteiten.setItems(list);

                TableColumn<IActiviteit, String> aNaamcol = new TableColumn<>();
                aNaamcol.setText("Activiteitnaam");
                aNaamcol.setCellValueFactory(cellData
                        -> cellData.getValue().getNaamProperty());
                TableColumn<IActiviteit, String> aStartCol = new TableColumn<>();
                aStartCol.setText("Start datum");
                aStartCol.setCellValueFactory(cellData
                        -> cellData.getValue().getBeginDatumProperty());
                TableColumn<IActiviteit, String> aEndCol = new TableColumn<>();
                aEndCol.setText("Eind datum");
                aEndCol.setCellValueFactory(cellData
                        -> cellData.getValue().getEindDatumProperty());

                TableColumn<IActiviteit, String> StageCol = new TableColumn<>();
                StageCol.setText("Type");
                StageCol.setCellValueFactory(cellData
                        -> cellData.getValue().getActiviteitTypeProperty());

                tblActiviteiten.setPrefWidth(sceneWidth);
                tblActiviteiten.setPrefHeight(sceneHeight - 100);

                aNaamcol.prefWidthProperty().bind(tblActiviteiten.widthProperty().divide(4));
                aStartCol.prefWidthProperty().bind(tblActiviteiten.widthProperty().divide(4));
                aEndCol.prefWidthProperty().bind(tblActiviteiten.widthProperty().divide(4));
                StageCol.prefWidthProperty().bind(tblActiviteiten.widthProperty().divide(4));

                tblActiviteiten.getColumns().addAll(aNaamcol, aStartCol, aEndCol, StageCol);
                hBoxTableContainer.getChildren().addAll(tblActiviteiten);
                break;
            case CLUBKAMPIOENSCHAP:
                hBoxTableContainer.getChildren().clear();
                TableView<IKampioenschap> tblKampioenschap = new TableView<>();
                tblKampioenschap.setItems(list);

                TableColumn<IKampioenschap, String> nameCol = new TableColumn<>();
                nameCol.setText("Naam");
                nameCol.setCellValueFactory(cellData -> cellData.getValue().getNaamProperty());

                TableColumn<IKampioenschap, String> datumCol = new TableColumn<>();
                datumCol.setText("Datum");
                datumCol.setCellValueFactory(cellData
                        -> cellData.getValue().getDatumProperty());

                TableColumn<IKampioenschap, String> leeftijdCCol = new TableColumn<>();
                leeftijdCCol.setText("Leeftijdcategorie");
                leeftijdCCol.setCellValueFactory(cellData
                        -> cellData.getValue().getLeeftijdCategorieProperty());

                nameCol.prefWidthProperty().bind(tblKampioenschap.widthProperty().divide(3));
                datumCol.prefWidthProperty().bind(tblKampioenschap.widthProperty().divide(3));
                leeftijdCCol.prefWidthProperty().bind(tblKampioenschap.widthProperty().divide(3));
                tblKampioenschap.getColumns().addAll(nameCol, datumCol, leeftijdCCol);

                tblKampioenschap.setPrefWidth(sceneWidth * 2 / 3.1);
                tblKampioenschap.setPrefHeight(sceneHeight - 100);
                tblKampioenschap.setMaxHeight(sceneHeight - 100);

                lstAanwezigheden.setPrefWidth(sceneWidth / 3);

                tblKampioenschap.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                    if (newValue != null) {
                        System.out.println(newValue);
                        List<String> aanwezigen = newValue.geefAanwezigen().stream().map(lid -> lid.getVoornaam() + " " + lid.getFamilienaam()).collect(Collectors.toList());
                        lstAanwezigheden.setItems(FXCollections.observableArrayList(aanwezigen));
                    }
                });

                tblKampioenschap.getSelectionModel().selectFirst();

                HBox hbKampioenschap = new HBox();
                hbKampioenschap.setSpacing(10);
                hbKampioenschap.getChildren().addAll(tblKampioenschap, vbAanwezigheden);

                hBoxTableContainer.getChildren().addAll(hbKampioenschap);
                break;
            case AANWEZIGHEID:
                hBoxTableContainer.getChildren().clear();
                TableView<ILid> tblActiviteit = new TableView<>();
                tblActiviteit.setItems(list);

                TableColumn<ILid, String> colANaam = new TableColumn<>();
                colANaam.setText("Naam");
                colANaam.setCellValueFactory(cellData
                        -> cellData.getValue().getVoornaamProperty().concat(" ").concat(cellData.getValue().getFamilienaamProperty())
                );

                TableColumn<ILid, String> colAFormule = new TableColumn<>();
                colAFormule.setText("Lesformule");
                colAFormule.setCellValueFactory(cellData
                        -> cellData.getValue().getLessenProperty()
                );

                tblActiviteit.setPrefWidth(sceneWidth * 2 / 3.1);
                tblActiviteit.setPrefHeight(sceneHeight - 100);
                tblActiviteit.setMaxHeight(sceneHeight - 100);

                colANaam.prefWidthProperty().bind(tblActiviteit.widthProperty().divide(2));
                colAFormule.prefWidthProperty().bind(tblActiviteit.widthProperty().divide(2));
                tblActiviteit.getColumns().addAll(colANaam, colAFormule);

                lstAanwezigheden.setPrefWidth(sceneWidth / 3);

                tblActiviteit.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                    if (newValue != null) {
                        System.out.println(newValue);
                        List<String> aanwezighedenPerLid = newValue.getAanwezigheden().stream().map(d -> d.toString()).collect(Collectors.toList());
                        lstAanwezigheden.setItems(FXCollections.observableArrayList(aanwezighedenPerLid));
                    }
                });

                tblActiviteit.getSelectionModel().selectFirst();

                HBox hbActiviteit = new HBox();
                hbActiviteit.setSpacing(10);
                hbActiviteit.getChildren().addAll(tblActiviteit, vbAanwezigheden);

                hBoxTableContainer.getChildren().addAll(hbActiviteit);
                break;
            case INSCHRIJVING:
                hBoxTableContainer.getChildren().clear();
                TableView<ILid> tblLidInschrijvingen = new TableView<>();
                tblLidInschrijvingen.setItems(list);
                ((SortedList) dc.getLeden()).comparatorProperty().bind(tblLidInschrijvingen.comparatorProperty());

                TableColumn<ILid, String> colNaam = new TableColumn<>();
                colNaam.setText("Naam");
                colNaam.setCellValueFactory(cellData
                        -> cellData.getValue().getVoornaamProperty().concat(" ").concat(cellData.getValue().getFamilienaamProperty())
                );

                TableColumn<ILid, String> colDatum = new TableColumn<>();
                colDatum.setText("Inschrijfdatum");
                colDatum.setCellValueFactory(cellData
                        -> cellData.getValue().getInschrijvingsDatumProperty());

                TableColumn<ILid, String> colFormule = new TableColumn<>();
                colFormule.setText("Lesformule");
                colFormule.setCellValueFactory(cellData
                        -> cellData.getValue().getLessenProperty()
                );

                tblLidInschrijvingen.setPrefWidth(sceneWidth);
                tblLidInschrijvingen.setPrefHeight(sceneHeight - 10);

                colNaam.prefWidthProperty().bind(tblLidInschrijvingen.widthProperty().divide(3));
                colDatum.prefWidthProperty().bind(tblLidInschrijvingen.widthProperty().divide(3));
                colFormule.prefWidthProperty().bind(tblLidInschrijvingen.widthProperty().divide(3));

                tblLidInschrijvingen.getColumns().addAll(colNaam, colDatum, colFormule);
                hBoxTableContainer.getChildren().addAll(tblLidInschrijvingen);
                break;
            case LESMATERIAAL:
                hBoxTableContainer.getChildren().clear();

                TableView<IOefening> tblLesMateriaal = new TableView<>();
                tblLesMateriaal.setItems(list);

                TableColumn<IOefening, String> colLNaam = new TableColumn<>();
                colLNaam.setText("Naam");
                colLNaam.setCellValueFactory(cellData -> cellData.getValue().getNaamProperty());

                TableColumn<IOefening, String> colLGraad = new TableColumn<>();
                colLGraad.setText("Graad");
                colLGraad.setCellValueFactory(cellData -> cellData.getValue().getGraadProperty());

                TableColumn<IOefening, String> colLURL = new TableColumn<>();
                colLURL.setText("Vide URL");
                colLURL.setCellValueFactory(cellData -> cellData.getValue().getVideoProperty());

                TableColumn<IOefening, String> colLPad = new TableColumn<>();
                colLPad.setText("Afbeelding pad");
                colLPad.setCellValueFactory(cellData -> cellData.getValue().getImageProperty());

                colLNaam.prefWidthProperty().bind(tblLesMateriaal.widthProperty().divide(4));
                colLGraad.prefWidthProperty().bind(tblLesMateriaal.widthProperty().divide(4));
                colLURL.prefWidthProperty().bind(tblLesMateriaal.widthProperty().divide(4));
                colLPad.prefWidthProperty().bind(tblLesMateriaal.widthProperty().divide(4));

                tblLesMateriaal.setPrefWidth(sceneWidth);
                tblLesMateriaal.setPrefHeight(sceneHeight - 100);

                tblLesMateriaal.getColumns().addAll(colLNaam, colLGraad, colLURL, colLPad);
                hBoxTableContainer.getChildren().addAll(tblLesMateriaal);
                break;
        }
    }

    @FXML
    private <T extends Exportable> void maakDocument(MouseEvent event) {
        SorteerType type = cboType.getSelectionModel().getSelectedItem();
        fillParameters(type);
        List<T> overzicht = dc.maakOverzichtList(type, extraParameters);
        dc.maakOverzicht(overzicht, path);
        maakOverzicht(type, FXCollections.observableArrayList(overzicht));
    }

    private void fillParameters(SorteerType type) {
        extraParameters.clear();
        switch (type) {
            case AANWEZIGHEID:
                Lid.setExportable(new ExportableLidAanwezigheden());
                String aanwezigheidsDatum = dpStartDatum.getValue() != null ? dpStartDatum.getValue().toString() : "";
                String aFormule = cboFormule.getSelectionModel().getSelectedItem() != null ? cboFormule.getSelectionModel().getSelectedItem().toString() : "";
                extraParameters.addAll(Arrays.asList(aanwezigheidsDatum, txfNaam.getText(), aFormule));
                break;
            case INSCHRIJVING:
                Lid.setExportable(new ExportableLidInschrijvingen());
                String inschrijvingsDatum = dpStartDatum.getValue() != null ? dpStartDatum.getValue().toString() : "";
                String iFormule = cboFormule.getSelectionModel().getSelectedItem() != null ? cboFormule.getSelectionModel().getSelectedItem().toString() : "";
                extraParameters.addAll(Arrays.asList(inschrijvingsDatum, txfNaam.getText(), iFormule));
                break;
            case ACTIVITEIT:
                Activiteit.setExportable(new ExportableActiviteit());
                String typeActiviteit = cboActiviteitType.getSelectionModel().getSelectedItem() != null ? cboActiviteitType.getSelectionModel().getSelectedItem().toString() : "";
                String startDatum = dpStartDatum.getValue() != null ? dpStartDatum.getValue().toString() : "";
                String eindDatum = dpEindDatum.getValue() != null ? dpEindDatum.getValue().toString() : "";
                extraParameters.addAll(Arrays.asList(typeActiviteit, txfNaam.getText(), startDatum, eindDatum));
                break;
            case CLUBKAMPIOENSCHAP:
                Kampioenschap.setExportable(new ExportableKampioenschap());
                String leeftijdCategorie = cboLeeftijd.getSelectionModel().getSelectedItem() != null ? cboLeeftijd.getSelectionModel().getSelectedItem().toString() : "";
                String kampioenschapDatum = dpStartDatum.getValue() != null ? dpStartDatum.getValue().toString() : "";
                extraParameters.addAll(Arrays.asList(txfNaam.getText(), leeftijdCategorie, kampioenschapDatum));
                break;
            case LESMATERIAAL:
                Oefening.setExportable(new ExportableOefening());
                String graad = cboGraad.getSelectionModel().getSelectedItem() != null ? cboGraad.getSelectionModel().getSelectedItem().toString() : "";
                extraParameters.addAll(Arrays.asList(graad, txfNaam.getText()));
                break;
        }
    }

    @FXML
    private void maakFiltersLeeg(ActionEvent event) {
        txfNaam.clear();
        cboActiviteitType.getSelectionModel().clearAndSelect(ActiviteitType.values().length - 1);
        cboFormule.getSelectionModel().clearAndSelect(LesType.values().length - 1);
        cboGraad.getSelectionModel().clearAndSelect(Graad.values().length - 1);
        dpStartDatum.getEditor().clear();
        dpEindDatum.getEditor().clear();
        cboLeeftijd.getSelectionModel().clearAndSelect(LeeftijdCategorie.values().length - 1);
        SorteerType type = cboType.getSelectionModel().getSelectedItem();
        placeTable(type, FXCollections.observableArrayList(dc.maakOverzichtList(type, null)));
    }

}

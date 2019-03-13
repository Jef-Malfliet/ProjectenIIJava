package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import util.Validatie;

@Entity
public class Oefening implements Serializable, IOefening, Exportable<Oefening> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Graad graad;
    private String uitleg;
    @ElementCollection
    private List<String> images;
    private String video;
    private String naam;
    @Transient
    private SimpleStringProperty naamProperty = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty videoProperty = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty graadProperty = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty imageProperty = new SimpleStringProperty();
    private static Exportable<Oefening> exportable;

    /**
     *
     * @param graad
     * @param naam
     */
    public Oefening(Graad graad, String naam) {
        setGraad(graad);
        setNaam(naam);
        this.images = new ArrayList<>();
    }

    public Oefening() {
    }

    public void mergeOefening(Oefening nieuweWaarden) {
        setGraad(nieuweWaarden.getGraad());
        setNaam(nieuweWaarden.getNaam());
        setGraadProperty(nieuweWaarden.getGraadProperty());
        setNaamProperty(nieuweWaarden.getNaamProperty());
        setUitleg(nieuweWaarden.getUitleg());
        setVideo(nieuweWaarden.getVideo());
        setVideoProperty(nieuweWaarden.getVideoProperty());
        nieuweWaarden.getImagePaths().forEach(path -> addImagePath(path));
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setGraad(Graad graad) {
        if (graad != null) {
            this.graad = graad;
        } else {
            throw new IllegalArgumentException("Graad mag niet leeg zijn.");
        }
    }

    public void setUitleg(String uitleg) {
        this.uitleg = uitleg;
    }

    public void setVideo(String url) {
        if (url != null) {
            String ID = Validatie.getYoutubeId(url);
            System.out.println(ID);
            String URL = "https://www.youtube.com/embed/" + ID;
            this.video = URL;
        }
    }

    public void setNaamProperty(SimpleStringProperty naamProperty) {
        this.naamProperty = naamProperty;
    }

    public void setVideoProperty(SimpleStringProperty videoProperty) {
        this.videoProperty = videoProperty;
    }

    public void setNaam(String naam) {
        if (naam != null && !naam.isEmpty()) {
            this.naam = naam;
        } else {
            throw new IllegalArgumentException("Naam mag niet leeg zijn.");
        }
    }

    @Override
    public String getVideo() {
        return this.video;
    }

    @Override
    public SimpleStringProperty getNaamProperty() {
        return naamProperty;
    }

    @Override
    public SimpleStringProperty getVideoProperty() {
        return videoProperty;
    }

    @Override
    public SimpleStringProperty getGraadProperty() {
        return graadProperty;
    }

    @Override
    public String getUitleg() {
        return this.uitleg;
    }

    @Override
    public Graad getGraad() {
        return graad;
    }

    @Override
    public String getNaam() {
        return naam;
    }

    @Override
    public long getId() {
        return id;
    }

    public void fillSimpleString() {
        this.naamProperty.set(this.naam);
        this.graadProperty.set(this.graad.toString());
        this.videoProperty.set(this.video);
        this.imageProperty.setValue(getImagePaths().stream().collect(Collectors.joining("\n")));
    }

    private void setGraadProperty(SimpleStringProperty graadProperty) {
        this.graadProperty = graadProperty;
    }

    public void addImagePath(String path) {
        this.images.add(path);
    }

    @Override
    public List<String> getImagePaths() {
        return this.images;
    }

    public static Exportable<Oefening> getExportable() {
        return exportable;
    }

    public static void setExportable(Exportable<Oefening> exportable) {
        Oefening.exportable = exportable;
    }

    @Override
    public String excelFormat(Oefening object) {
        return exportable.excelFormat(this);
    }

    @Override
    public String excelheaders() {
        return exportable.excelheaders();
    }

    @Override
    public SimpleStringProperty getImageProperty() {
        return imageProperty;
    }

    private void setImageProperty(SimpleStringProperty imageProperty) {
        this.imageProperty = imageProperty;
    }
}

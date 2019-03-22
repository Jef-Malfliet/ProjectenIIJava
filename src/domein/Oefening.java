package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
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
@Access(AccessType.FIELD)
public class Oefening implements Serializable, IOefening, Exportable<Oefening> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Enumerated(EnumType.STRING)
    //private Graad graad;
    private String uitleg;

    @ElementCollection
    @Column(name = "Images")
    private List<String> images;
    //private String video;
    //private String naam;
    @Transient
    private SimpleStringProperty naamProperty = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty videoProperty = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty graadProperty = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty imageProperty = new SimpleStringProperty();
    private int aantalkeerBekeken = 0;
    @ElementCollection
    private List<String> comments;
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
        this.comments = new ArrayList<>();
    }

    public Oefening() {
    }

    public void mergeOefening(Oefening nieuweWaarden) {
        setGraad(nieuweWaarden.getGraad());
        setNaam(nieuweWaarden.getNaam());
        setUitleg(nieuweWaarden.getUitleg());
        setVideo(nieuweWaarden.getVideo());
        setImages(nieuweWaarden.getImages());
    }

    public void setGraad(Graad graad) {
        if (graad != null) {
            graadProperty.set(graad.toString());
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
            String URL = "https://www.youtube.com/embed/" + ID;
            this.videoProperty.set(URL);
        }
    }

    public void setNaam(String naam) {
        if (naam == null || naam.isEmpty()) {
            throw new IllegalArgumentException("Naam mag niet leeg zijn.");
        } else {
            naamProperty.set(naam);
        }
    }

    public void setImages(List<String> paths) {
        this.images = paths;
        this.imageProperty.set(this.images.stream().collect(Collectors.joining("\n")));

    }

    @Override
    @Access(AccessType.PROPERTY)
    @Column(name = "Video")
    public String getVideo() {
        return videoProperty.get();
    }

    @Override
    public String getUitleg() {
        return this.uitleg;
    }

    @Override
    @Access(AccessType.PROPERTY)
    @Column(name = "Graad")
    public Graad getGraad() {
        return Graad.valueOf(graadProperty.get());
    }

    @Override
    @Access(AccessType.PROPERTY)
    @Column(name = "Naam")
    public String getNaam() {
        return naamProperty.get();
    }

    @Override
    public SimpleStringProperty getImageProperty() {
        return imageProperty;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public List<String> getImages() {
        return FXCollections.observableArrayList(this.images);
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
    public SimpleStringProperty getNaamProperty() {
        return this.naamProperty;
    }

    @Override
    public SimpleStringProperty getVideoProperty() {
        return this.videoProperty;
    }

    @Override
    public SimpleStringProperty getGraadProperty() {
        return this.graadProperty;
    }

    @Override
    public int getAantalkeerBekeken() {
        return aantalkeerBekeken;
    }

    public void setAantalkeerBekeken(int aantalkeerBekeken) {
        this.aantalkeerBekeken = aantalkeerBekeken;
    }

    @Override
    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

}

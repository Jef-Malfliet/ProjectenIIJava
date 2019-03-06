package domein;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

@Entity
public class Oefening implements Serializable, IOefening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Graad graad;
    private String uitleg;
    @Lob
    @ElementCollection
    private List<byte[]> images;
    private String video;
    private String naam;
    @Transient
    private SimpleStringProperty naamProperty = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty videoProperty = new SimpleStringProperty();
    @Transient
    private SimpleStringProperty graadProperty = new SimpleStringProperty();

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

    public void setGraad(Graad graad) {
        if(graad!=null)
            this.graad = graad;
        else
            throw new IllegalArgumentException("Graad mag niet leeg zijn.");
    }

    public void setNaam(String naam) {
        if(naam!=null && naam!="")
            this.naam = naam;
        else
            throw new IllegalArgumentException("Naam mag niet leeg zijn.");
    }
    
    

    /**
     *
     * @param uitleg
     */
    public void addUitleg(String uitleg) {
        this.uitleg = uitleg;
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
    public long getId(){
        return id;
    }
    /**
     *
     * @param image
     */
    public void addImage(File image) {
        try {
            BufferedImage imagebufferd = ImageIO.read(image);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(imagebufferd, "jpg", outputStream);
            this.images.add(outputStream.toByteArray());
        } catch (IOException ex) {
            Logger.getLogger(Oefening.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Image> getImages() {
        List<Image> returnImages = new ArrayList<>();
        for (byte[] bytes : this.images) {
            InputStream input = new ByteArrayInputStream(bytes);
            BufferedImage bufferedImage = null;

            try {
                bufferedImage = ImageIO.read(input);
            } catch (IOException ex) {
                Logger.getLogger(Oefening.class.getName()).log(Level.SEVERE, null, ex);
            }
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                returnImages.add(image);
        }
        
        if(images.isEmpty()||images == null){
            return null;
        }else{
            return returnImages;
        }
    }

    /**
     *
     * @param url
     */
    public void addVideo(String url) {
        this.video = url;
    }

    @Override
    public String getVideo() {
        return this.video;
    }

    public void fillSimpleString() {
        this.naamProperty.set(this.naam);
        this.graadProperty.set(this.graad.toString());
        this.videoProperty.set(this.video);
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

}

package domein;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Oefening implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Graad graad;
    private String uitleg;
    @Lob
    private byte[] image;
    private String video;
    private String naam;

    /**
     *
     * @param graad
     * @param naam
     */
    public Oefening(Graad graad, String naam) {
        this.graad = graad;
        this.naam = naam;
    }

    public Oefening() {

    }

    /**
     *
     * @param uitleg
     */
    public void addUitleg(String uitleg) {
        this.uitleg = uitleg;
    }

    public String getUitleg() {
        return this.uitleg;
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
            this.image = outputStream.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(Oefening.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Image getImage() {
        byte[] opgeslagenBytes = this.image;
        InputStream in = new ByteArrayInputStream(opgeslagenBytes);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(in);
        } catch (IOException ex) {
            Logger.getLogger(Oefening.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        return image;
    }

    /**
     *
     * @param url
     */
    public void addVideo(String url) {
        this.video = url;
    }

    public String getVideo() {
        return this.video;
    }

}

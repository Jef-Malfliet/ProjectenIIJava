/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Graad;
import domein.ILid;
import domein.IOefening;
import domein.Lid;
import domein.Oefening;
import java.io.File;
import java.net.MalformedURLException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 *
 * @author IndyV
 */
public class OefeningMapper {

    private SecureRandom random = new SecureRandom();
    
    private final String[] benamingen = {
        "Age uke", "Soto uke", "Uchi uke", "Gedan barai", "Shuto uke", "Morote uke",
        "Kakiwake uke", "Otosohi uke", "Juji uke", "nagashi uke", "hasami uke",
        "haishu uke", "sukui uke", "ude uke", "kake uke", "oi tsuki", "gyaku tsuki",
        "kizame tsuki", "Nukite", "choku tsuki", "age tsukie", "kagi stuki", "mawashi tsuki",
        "heiko tushi", "ura tsuki", "shuto uchi", "uraken uchi", "tettsui uchi"
    };

    private final File[] fotos = {
        new File("download.jpg"),
        new File("download (1).jpg"),
        new File("download (2).jpg"),
        new File("download (3).jpg"),
        new File("download (4).jpg"),
        new File("foto1.jpg")
    };

    private final String[] youtube = {
        "https://www.youtube.com/watch?v=gSF6ZS1I0Pc",
        "https://www.youtube.com/watch?v=hY35pBOfSNk",
        "https://www.youtube.com/watch?v=ZM_8-c1EqOY",
        "https://www.youtube.com/watch?v=4CGYQjC6WOY",
        "https://www.youtube.com/watch?v=SntovGstTmk",
        "https://www.youtube.com/watch?v=N6TSwk5rLdM"

    };

    private final String[] uitleg = {
        "uitleg1",
        "uitleg2",
        "uitleg3",
        "uitleg4"
    };

    public Oefening maakOefening() {
        Oefening oef = new Oefening(randomGraad(), randomBenaming());
        List images = new ArrayList<>();
        SecureRandom random = new SecureRandom();
        int getal = random.nextInt(10);
        for (int i = 0; i < getal; i++) {
            images.add(randomFoto());
        }
        oef.setImages(images);
        oef.setVideo(randomYoutube());
        oef.setUitleg(randomUitleg());

        return oef;
    }

    private String randomBenaming() {
        int getal = random.nextInt(benamingen.length);
        return benamingen[getal];
    }

    private String randomFoto() {
        int getal = random.nextInt(fotos.length);
        try {
            return fotos[getal].toURI().toURL().toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(OefeningMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String randomYoutube() {
        int getal = random.nextInt(youtube.length);
        return youtube[getal];
    }

    private Graad randomGraad() {
        int getal = random.nextInt(Graad.values().length);
        return Graad.values()[getal];
    }

    private String randomUitleg() {
        int getal = random.nextInt(uitleg.length);
        return uitleg[getal];
    }

}

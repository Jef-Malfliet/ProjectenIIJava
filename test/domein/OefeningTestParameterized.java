/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author IndyV
 */
@RunWith(Parameterized.class)
public class OefeningTestParameterized {

    private final Graad graad;
    private final String naam;
    private final String videoURL;
    private final List<String> fotoPath;
    private final String uitleg;

    
    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
            //Geldig
            {Graad.BLAUW, "Token", "https://www.youtube.com/watch?v=zbJHNdyXy4A", "path", "Video van Token  - NO Service"},
            {Graad.BRUIN, "Billi Eilish", "https://www.youtube.com/watch?v=HUHC9tYz8ik", "fotopath", "Video van Billi Eilish - Bury a Friend"},
            {Graad.DAN4, "url1", "http://www.youtube.com/watch?v=dQw4w9WgXcQ&a=GxdCwVVULXctT2lYDEPllDR0LRTutYfW", "fotopath", "uitleg"},
            {Graad.GEEL, "url2", "http://www.youtube.com/watch?v=dQw4w9WgXcQ", "x", "x"},
            {Graad.WIT, "url3", "http://youtu.be/dQw4w9WgXcQ", "x", "x"},
            {Graad.ORANJE, "url3", "http://www.youtube.com/embed/dQw4w9WgXcQ", "x", "x"},
            {Graad.DAN10, "url4", "http://www.youtube.com/v/dQw4w9WgXcQ", "x", "x"},
            {Graad.DAN11, "url5", "http://www.youtube.com/e/dQw4w9WgXcQ", "x", "x"},
            {Graad.DAN3, "url6", "http://www.youtube.com/watch?feature=player_embedded&v=dQw4w9WgXcQ", "x", "x"},
        
        });
    }
    
    public OefeningTestParameterized(Graad graad, String naam, String URL, String path, String uitleg) {
        this.graad = graad;
        this.naam = naam;
        this.videoURL = URL;
        this.fotoPath=new ArrayList<>();
        this.fotoPath.add(path);
        this.uitleg = uitleg;
    }

    @Test
    public void maakOefening() {
        Oefening oef = new Oefening(graad, naam);
        oef.setUitleg(uitleg);
        oef.setImages(fotoPath);
        oef.setVideo(videoURL);
    }

}

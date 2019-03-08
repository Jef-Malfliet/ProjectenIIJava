/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import org.eclipse.persistence.jpa.jpql.Assert;
import org.junit.Test;

/**
 *
 * @author IndyV
 */
public class OefeningTest {
    
    @Test
    public void maakOefening_valid(){
        Oefening oef = new Oefening(Graad.BRUIN,"Test");
        Assert.isNotNull(oef, "Valid");
    }
    @Test(expected = IllegalArgumentException.class)
    public void maakOefening_legeNaam_failed(){
        Oefening oef = new Oefening(Graad.BLAUW,"");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void maakOefening_geenNaam_failed(){
        Oefening oef = new Oefening(Graad.BLAUW,null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void maakOefening_geenGraad_failed(){
        Oefening oef = new Oefening(null,"Naam");
    }
    
     @Test(expected = IllegalArgumentException.class)
    public void setVideo_failed(){
        Oefening oef = new Oefening(Graad.BRUIN,"Naam");
        String url = "www.chamilo.hogent.be";
        oef.setVideo(url);
    }
}

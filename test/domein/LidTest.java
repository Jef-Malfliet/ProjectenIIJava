/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Mout
 */
public class LidTest {

    private Lid huidigLid;

    @Before
    public void before() {
        huidigLid = new Lid("Bram","Vanoverbeke", Graad.BLAUW, "12/34567890", "bram.vanoverbeke@student.hogent.be", "Straat", 9300, "Aalst");
    }

    /**
     *
     */
    // ALLE ARGUMENTEN TESTEN
    @Test
    public void testConstructor() {
        Lid nieuwLid = new Lid("Tom","Clarys", Graad.BRUIN, "12/34567890", "tom.clarys@student.hogent.be", "Straat", 9240, "Zele");
        Assert.assertEquals("Tom", nieuwLid.getVoornaam());
        Assert.assertEquals(Graad.BRUIN, nieuwLid.getGraad());
        Assert.assertEquals(Type.LID, nieuwLid.getType());
    }
}

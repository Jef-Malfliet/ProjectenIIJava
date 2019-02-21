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
        huidigLid = new Lid("Bram Vanoverbeke", Graad.WIT);
    }

    /**
     *
     */
    @Test
    public void testConstructor() {
        Lid nieuwLid = new Lid("Tom Clarys", Graad.BRUIN);
        Assert.assertEquals("Tom Clarys", nieuwLid.getVoornaam());
        Assert.assertEquals(Graad.BRUIN, nieuwLid.getGraad());
        Assert.assertEquals(Type.LID, nieuwLid.getType());
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_EmptyName_ThrowsException() {
        Lid nieuwLid = new Lid("", Graad.GEEL);
    }

    /**
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_NullName_ThrowsException() {
        Lid nieuwLid = new Lid(null, Graad.ORANJE);
    }

}

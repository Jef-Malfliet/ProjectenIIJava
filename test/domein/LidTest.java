/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import org.junit.Assert;
import org.junit.Before;



/**
 *
 * @author Mout
 */
public class LidTest {
    
    private Lid huidigLid;
    
    @Before
    public void before(){
        huidigLid = new Lid("Bram Vanoverbeke",Graad.GRAAD1);
    }

    /**
     * 
     */
    public void testConstructor(){
        Lid nieuwLid = new Lid("Tom Clarys", Graad.GRAAD2);
        Assert.assertEquals("Tom Clarys",nieuwLid.getNaam());
        Assert.assertEquals(Graad.GRAAD2, nieuwLid.getGraad());
        Assert.assertEquals(Type.LID, nieuwLid.getType());
    }
    
}

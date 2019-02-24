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

    @Test
    public void testConstructor() {
        Lid nieuwLid = new Lid("Tom", "Clarys", Graad.BRUIN, "12/34567890", "tom.clarys@student.hogent.be", "Straat", 9240, "Zele");
        Assert.assertEquals("Tom", nieuwLid.getVoornaam());
        Assert.assertEquals(Graad.BRUIN, nieuwLid.getGraad());
        Assert.assertEquals(RolType.LID, nieuwLid.getType());
    }
}

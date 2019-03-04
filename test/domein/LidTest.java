/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Mout
 */
public class LidTest {

    @Test
    public void testConstructor() {
        Lid nieuwLid = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegen", "België","98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER,LesType.DI_DO);

        Assert.assertEquals("Mout", nieuwLid.getVoornaam());
        Assert.assertEquals("Pessemier", nieuwLid.getFamilienaam());
        Assert.assertEquals("mp12345", nieuwLid.getWachtwoord());
        Assert.assertEquals("0234567890",nieuwLid.getGsm());
        Assert.assertEquals("053248216",nieuwLid.getTelefoon_vast());
        Assert.assertEquals("Bertha De Dekenlaan",nieuwLid.getStraatnaam());
        Assert.assertEquals("14", nieuwLid.getHuisnummer());
        Assert.assertEquals("8", nieuwLid.getBusnummer());
        Assert.assertEquals("9320", nieuwLid.getPostcode());
        Assert.assertEquals("Erembodegen", nieuwLid.getStad());
        Assert.assertEquals("België", nieuwLid.getLand());
        Assert.assertEquals("mout.pessemier@student.hogent.be", nieuwLid.getEmail());
        Assert.assertEquals("ouders.mout@telenet.be",nieuwLid.getEmail_ouders());
        Assert.assertEquals(LocalDate.of(1999, 6, 14),nieuwLid.getGeboortedatum());
        Assert.assertEquals(LocalDate.of(2007, 11, 8), nieuwLid.getInschrijvingsdatum());
        Assert.assertEquals(new ArrayList<>(), nieuwLid.getAanwezigheden());
        Assert.assertEquals(Geslacht.MAN, nieuwLid.getGeslacht());
        Assert.assertEquals(Graad.DAN12, nieuwLid.getGraad());
        Assert.assertEquals(RolType.BEHEERDER, nieuwLid.getType());
    }
}

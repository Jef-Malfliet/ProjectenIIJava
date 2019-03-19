/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;
import java.util.ArrayList;
import org.eclipse.persistence.jpa.jpql.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jef
 */
public class ActiviteitTest {

    public Lid lid;

    @Before
    public void before() {
        lid = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegen", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.DI_DO);
    }

    @Test
    public void lidInschrijven_valid() {
        Activiteit act = new Activiteit("test", LocalDate.now(), LocalDate.now().plusDays(1l), 20, ActiviteitType.STAGE);
        act.lidInschrijven(lid);
        Assert.isTrue(act.getAanwezigen().contains(lid), "Valid");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lidInschrijven_null_invalid() {
        Activiteit act = new Activiteit("test", LocalDate.now(), LocalDate.now().plusDays(1l), 20, ActiviteitType.STAGE);
        act.lidInschrijven(null);
    }

    @Test
    public void lidUitschrijven_valid() {
        Activiteit act = new Activiteit("test", LocalDate.now(), LocalDate.now().plusDays(1l), 20, ActiviteitType.STAGE);
        act.getAanwezigen().add(lid);
        act.lidUitschrijven(lid);
        Assert.isTrue(!act.getAanwezigen().contains(lid), "Valid");
    }

    @Test(expected = IllegalArgumentException.class)
    public void lidUitschrijven_null_invalid() {
        Activiteit act = new Activiteit("test", LocalDate.now(), LocalDate.now().plusDays(1l), 20, ActiviteitType.STAGE);
        act.lidUitschrijven(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lidUitschrijven_BevatLidNiet_invalid() {
        Activiteit act = new Activiteit("test", LocalDate.now(), LocalDate.now().plusDays(1l), 20, ActiviteitType.STAGE);
        act.lidUitschrijven(lid);
    }

}

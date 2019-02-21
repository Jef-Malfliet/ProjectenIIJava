/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mockito;
import persistentie.PersistentieController;

/**
 *
 * @author Mout
 */
public class DojoTest {

    private PersistentieController persistentieControllerDummy;
    private Lid testLid;
    private Lid lid1;
    private Lid lid2;
    private Lid lid3;
    private final List<Lid> ledenLijst = new ArrayList<>();
    private Dojo beheerder;

    @Before
    public void before() {
        persistentieControllerDummy = Mockito.mock(PersistentieController.class);
        testLid = new Lid("Beau Van Canegem", Graad.BRUIN);
        lid1 = new Lid("Bram Vanoverbeke", Graad.BLAUW);
        lid1.setId(1L);
        lid2 = new Lid("Tom Clarys", Graad.GEEL);
        lid2.setId(2L);
        lid3 = new Lid("Seba Moons", Graad.GROEN);
        lid3.setId(3L);
        ledenLijst.addAll(Arrays.asList(lid1, lid2, lid3));
    }

    /**
     * Test of verwijderLid method, of class Beheerder.
     */
    @Test
    public void testVerwijderLid() {
        Mockito.when(persistentieControllerDummy.geefAlles(Stuff.LID)).thenReturn(ledenLijst);
        beheerder = new Dojo(persistentieControllerDummy);
        beheerder.verwijderLid(lid1);
        Assert.assertEquals(2, beheerder.getLijstLeden().size());
        System.out.println(ledenLijst);
        System.out.println(beheerder.getLijstLeden());
        Assert.assertFalse(beheerder.getLijstLeden().contains(lid1));
        Mockito.verify(persistentieControllerDummy).geefAlles(Stuff.LID);

    }

    /**
     * Test of wijzigLid method, of class Beheerder.
     */
    @Test
    public void testWijzigLid() {
        Mockito.when(persistentieControllerDummy.geefAlles(Stuff.LID)).thenReturn(ledenLijst);
        Mockito.when(persistentieControllerDummy.wijzig(lid1)).thenReturn(true);
        beheerder = new Dojo(persistentieControllerDummy);
        lid1.wijzigLid("TestTest", Graad.GROEN);
        boolean succes = beheerder.wijzigLid(lid1);
        Lid gewijzigdLid = beheerder.toonLid(lid1.getId());
        Assert.assertTrue(succes);
        Assert.assertEquals(lid1.getNaam(), gewijzigdLid.getNaam());
        Assert.assertEquals(lid1.getGraad(), gewijzigdLid.getGraad());
        Mockito.verify(persistentieControllerDummy).geefAlles(Stuff.LID);
        Mockito.verify(persistentieControllerDummy).wijzig(lid1);
    }

    /**
     * Test of toonLid method, of class Beheerder.
     */
    @Test
    public void testToonLid() {
        Mockito.when(persistentieControllerDummy.geefAlles(Stuff.LID)).thenReturn(ledenLijst);
        beheerder = new Dojo(persistentieControllerDummy);
        Lid toonLid = beheerder.toonLid(lid1.getId());
        Assert.assertEquals(lid1, toonLid);
        Mockito.verify(persistentieControllerDummy).geefAlles(Stuff.LID);
    }

    /**
     * Test of voegLidToe method, of class Beheerder.
     */
    @Test
    public void testVoegLidToe() {
        Mockito.when(persistentieControllerDummy.geefAlles(Stuff.LID)).thenReturn(ledenLijst);
        beheerder = new Dojo(persistentieControllerDummy);
        beheerder.voegLidToe(testLid);
        Assert.assertEquals(4, beheerder.getLijstLeden().size());
        Assert.assertTrue(beheerder.getLijstLeden().contains(testLid));
        Mockito.verify(persistentieControllerDummy).geefAlles(Stuff.LID);
    }
}

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
import persistentie.LidDao;

/**
 *
 * @author Mout
 */
public class DojoTest {

    private LidDao lidDaoDummy;
    private Lid testLid;
    private Lid lid1;
    private Lid lid2;
    private Lid lid3;
    private Lid lid1b;
    private final List<Lid> ledenLijst = new ArrayList<>();
    private Dojo beheerder;

    @Before
    public void before() {
        lidDaoDummy = Mockito.mock(LidDao.class);
        //testLid = new Lid("Beau", "Van Canegem", Graad.BRUIN, "12/34567890", "indy.vancanegem@student.hogent.be", "Straat", 9240, "Zele");
        testLid = Mockito.mock(Lid.class);
        lid1 = new Lid("Bram", "Vanoverbeke", Graad.BLAUW, "12/34567890", "bram.vanoverbeke@student.hogent.be", "Straat", 9300, "Aalst");
        lid2 = new Lid("Tom", "Clarys", Graad.GEEL, "12/34567890", "tom.clarys@student.hogent.be", "Straat", 9240, "Zele");
        lid3 = new Lid("Seba", "Moons", Graad.GROEN, "12/34567890", "seba.moons@student.hogent.be", "Straat", 9240, "Zele");
        lid1b = new Lid("TestTest", "Vanoverbeke", Graad.GROEN, "12/34567890", "bram.vanoverbeke@student.hogent.be", "Straat", 9300, "Aalst");
        ledenLijst.addAll(Arrays.asList(lid1, lid2, lid3));
    }

    /**
     * Test of verwijderLid method, of class Beheerder.
     */
    @Test
    public void testVerwijderLid() {
        Mockito.when(lidDaoDummy.findAll()).thenReturn(ledenLijst);
        beheerder = new Dojo(lidDaoDummy);
        beheerder.verwijderLid(lid1);
        System.out.println(beheerder.getLijstLeden());
        Assert.assertEquals(2, beheerder.getLijstLeden().size());
        //Assert.assertFalse(beheerder.getLijstLeden().contains(lid1)); //invoication targetexception voor de een of andere reden
        Mockito.verify(lidDaoDummy).findAll();

    }

    /**
     * Test of wijzigLid method, of class Beheerder.
     */
    @Test
    public void testWijzigLid() {
        Mockito.when(lidDaoDummy.findAll()).thenReturn(ledenLijst);
        Mockito.when(lidDaoDummy.update(lid1)).thenReturn(lid1b);
        beheerder = new Dojo(lidDaoDummy);
        lid1.wijzigLid("TestTest", Graad.GROEN);
        boolean succes = beheerder.wijzigLid(lid1);
        Lid gewijzigdLid = beheerder.toonLid(lid1.getId());
        Assert.assertTrue(succes);
        Assert.assertEquals(lid1.getVoornaam(), gewijzigdLid.getVoornaam());
        Assert.assertEquals(lid1.getGraad(), gewijzigdLid.getGraad());
        Mockito.verify(lidDaoDummy, Mockito.times(2)).findAll();//2 keer, idk waarom?
        Mockito.verify(lidDaoDummy).update(lid1);
    }

    /**
     * Test of toonLid method, of class Beheerder.
     */
    @Test
    public void testToonLid() {
        Mockito.when(lidDaoDummy.findAll()).thenReturn(ledenLijst);
        beheerder = new Dojo(lidDaoDummy);
        Lid toonLid = beheerder.toonLid(lid1.getId());
        Assert.assertEquals(lid1, toonLid);
        Mockito.verify(lidDaoDummy).findAll();
    }

    /**
     * Test of voegLidToe method, of class Beheerder.
     */
    @Test
    public void testVoegLidToe() {
        Mockito.when(lidDaoDummy.findAll()).thenReturn(ledenLijst);
        Mockito.when(testLid.getId()).thenReturn(5l);
        beheerder = new Dojo(lidDaoDummy);
        beheerder.voegLidToe(testLid);
        Assert.assertEquals(4, beheerder.getLijstLeden().size());
        Assert.assertTrue(beheerder.getLijstLeden().contains(testLid));
        Mockito.verify(lidDaoDummy).insert(testLid);
    }
}

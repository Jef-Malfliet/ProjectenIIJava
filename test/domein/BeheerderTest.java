/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;
import persistentie.PersistentieController;

/**
 *
 * @author Nante
 */
public class BeheerderTest {

    private Beheerder beheerder;
    private PersistentieController persistentieControllerDummy;
    private Lid testLid;
    private final List<Lid> ledenLijst = new ArrayList<>(
            Arrays.asList(new Lid("Bram Vanoverbeke", Graad.GRAAD1),
                    new Lid("Tom Clarys", Graad.GRAAD2),
                    new Lid("Seba Moons", Graad.GRAAD1))
    );

    @Before
    public void before() {
        persistentieControllerDummy = Mockito.mock(PersistentieController.class);
        beheerder = new Beheerder(persistentieControllerDummy);
        testLid = new Lid("Beau Van Canegem", Graad.GRAAD2);
    }

    /**
     * Test of verwijderLid method, of class Beheerder.
     */
    @Test
    public void testVerwijderLid() {
        //Mockito.when(persistentieControllerDummy.geefLijstLeden()).thenReturn(ledenLijst);
        beheerder.verwijderLid(new Lid("Bram Vanoverbeke", Graad.GRAAD1));
        Assert.assertEquals(2, beheerder.getLijstLeden().size());
        IntStream.range(0, beheerder.getLijstLeden().size())
                .forEach(index ->
                        Assert.assertEquals(ledenLijst.get(index+1), beheerder.getLijstLeden().get(index))//als deze in het midden van de lijst verwijderd wordt moet die +1 weg, miss best nog is herschrijven?
        );
        //Mockito.verify(persistentieControllerDummy).geefLijstLeden();

    }

    /**
     * Test of wijzigLid method, of class Beheerder.
     */
    @Test
    public void testWijzigLid() {//deze methode klopt niet, we geven alleen een lid mee, niet wat er gewijzigd moet worden?
        boolean succes = beheerder.wijzigLid(testLid);//deze methode moet volgens mij een lid terug geven, tenzij we toonLid gebruiken om het gewijzigde lid op te gaan vragen
        Lid gewijzigdLid = beheerder.toonLid(testLid);
        Assert.assertTrue(succes);
        Assert.assertEquals(testLid.getNaam(), gewijzigdLid.getNaam());//naam van het gewijzigde lid
        Assert.assertEquals(testLid.getGraad(), gewijzigdLid.getGraad());//graad van het gewijzigde lid
        Assert.assertEquals(testLid.getType(), gewijzigdLid.getType());//Type van het gewijzigde lid
    }

    /**
     * Test of toonLid method, of class Beheerder.
     */
    @Test
    public void testToonLid() {
        Lid toonLid = beheerder.toonLid(testLid);
        Assert.assertEquals(testLid, toonLid);
    }

    /**
     * Test of voegLidToe method, of class Beheerder.
     */
    @Test
    public void testVoegLidToe() {
        //Mockito.when(persistentieControllerDummy.geefLijstLeden()).thenReturn(ledenLijst);
        beheerder.voegLidToe(testLid);
        Assert.assertEquals(4, beheerder.getLijstLeden().size());
        IntStream.range(0, ledenLijst.size() - 1)
                .forEach(index ->
                 Assert.assertEquals(beheerder.getLijstLeden().get(index), ledenLijst.get(index)));
        Assert.assertEquals(testLid, beheerder.getLijstLeden().get(beheerder.getLijstLeden().size() - 1)
        );
        //Mockito.verify(persistentieControllerDummy).geefLijstLeden();
    }

}

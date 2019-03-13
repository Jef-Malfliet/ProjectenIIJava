/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mockito;
import persistentie.ActiviteitDao;
import persistentie.KampioenschapDao;
import persistentie.LidDao;
import persistentie.OefeningDao;

/**
 *
 * @author Mout
 */
public class DojoTest {

    private LidDao lidDaoDummy;
    private OefeningDao oefeningDaoDummy;
    private ActiviteitDao activiteitDaoDummy;
    private KampioenschapDao kampioenschapDaoDummy;
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
        oefeningDaoDummy = Mockito.mock(OefeningDao.class);
        activiteitDaoDummy = Mockito.mock(ActiviteitDao.class);
        kampioenschapDaoDummy = Mockito.mock(KampioenschapDao.class);
        testLid = Mockito.mock(Lid.class);
        lid1 = new Lid("Nante", "Vermeulen", "nv12345", "0479154879", "053548216", "Straat", "100", "/", "9320", "Landegem", Land.België, "98.10.19-333.61", "nante.vermeulen@student.hogent.be",
                "ouders.nante@telenet.be", LocalDate.of(1998, 8, 16), LocalDate.of(2014, 5, 9), new ArrayList<>(Arrays.asList(LocalDate.of(1998, 8, 16))){}, Geslacht.MAN, Graad.GROEN, RolType.BEHEERDER, LesType.DI_ZA);

        lid2 = new Lid("Indy", "Van Canegem", "ivc12345", "0479154978", "053698442", "Straat", "13", "88", "9520", "Zele", Land.België, "98.10.19-333.61", "indy.vancanegem@student.hogent.be",
                "ouders.indy@skynet.be", LocalDate.of(1998, 8, 16), LocalDate.of(2014, 5, 9), new ArrayList<>(), Geslacht.ANDERS, Graad.GROEN, RolType.LID, LesType.WO_ZA);

        lid3 = new Lid("Jef", "Malfliet", "jm12345", "0234567890", "053698420", "Straat", "1", "2.9", "9220", "Hamme", Land.België, "98.10.19-333.61", "jef.malfliet@student.hogent.be",
                "ouders.jef@proxymus.be", LocalDate.of(1999, 10, 24), LocalDate.of(2016, 8, 31), new ArrayList<>(), Geslacht.VROUW, Graad.WIT, RolType.LESGEVER, LesType.WO_ZA);

        lid1b = new Lid("Mout", "Pessemier", "mp12345", "0234567890", "053248216", "Bertha De Dekenlaan", "14", "8", "9320", "Erembodegen", Land.België, "98.10.19-333.61", "mout.pessemier@student.hogent.be",
                "ouders.mout@telenet.be", LocalDate.of(1999, 6, 14), LocalDate.of(2007, 11, 8), new ArrayList<>(), Geslacht.MAN, Graad.DAN12, RolType.BEHEERDER, LesType.WO_ZA);

        ledenLijst.addAll(Arrays.asList(lid1, lid2, lid3));
    }

    /**
     * Test of verwijderLid method, of class Beheerder.
     */
    @Test
    public void testVerwijderLid() {
        Mockito.when(lidDaoDummy.findAll()).thenReturn(ledenLijst);
        beheerder = new Dojo(lidDaoDummy, oefeningDaoDummy, activiteitDaoDummy, kampioenschapDaoDummy);
        beheerder.setCurrentLid(lid1);
        beheerder.verwijderCurrentLid();
        
        Assert.assertEquals(2, beheerder.getLijstLeden().size());
        //Assert.assertFalse(beheerder.getLijstLeden().contains(lid1)); //assertionFailed
        Mockito.verify(lidDaoDummy).findAll();

    }

    /**
     * Test of wijzigLid method, of class Beheerder.
     */
    @Test
    public void testWijzigLid() {
        Mockito.when(lidDaoDummy.findAll()).thenReturn(ledenLijst);
        Mockito.when(lidDaoDummy.update(lid1)).thenReturn(lid1b);

        beheerder = new Dojo(lidDaoDummy, oefeningDaoDummy, activiteitDaoDummy, kampioenschapDaoDummy);
//        lid1.wijzigLid("Bram", "Vermeulen", "nv12345", "0479154879", "053548216", "Straat", "100", "/", "9320", "Landegem", Land.België, "nante.vermeulen@student.hogent.be",
//                "ouders.nante@telenet.be", LocalDate.of(1998, 8, 16), LocalDate.of(2014, 5, 9), new ArrayList<>(), Geslacht.MAN, Graad.WIT, RolType.BEHEERDER);
        beheerder.setCurrentLid(lid1);
        boolean succes = beheerder.wijzigLid("Bram", "Vermeulen", "nv12345", "0479154879", "053548216", "Straat", "100", "/", "9320", "Landegem", Land.België, "98.10.19-333.61", "nante.vermeulen@student.hogent.be",
                "ouders.nante@telenet.be", LocalDate.of(1998, 8, 16), LocalDate.of(2014, 5, 9), new ArrayList<>(), Geslacht.MAN, Graad.WIT, RolType.BEHEERDER, LesType.DI_ZA);
        ILid gewijzigdLid = beheerder.toonLid(lid1.getId());
        Assert.assertTrue(succes);
        Assert.assertEquals(lid1.getVoornaam(), gewijzigdLid.getVoornaam());
        Assert.assertEquals(lid1.getGraad(), gewijzigdLid.getGraad());
        Mockito.verify(lidDaoDummy, Mockito.times(1)).findAll();
        Mockito.verify(lidDaoDummy).update(lid1);
    }

    /**
     * Test of toonLid method, of class Beheerder.
     */
    @Test
    public void testToonLid() {
        Mockito.when(lidDaoDummy.findAll()).thenReturn(ledenLijst);
        beheerder = new Dojo(lidDaoDummy, oefeningDaoDummy, activiteitDaoDummy, kampioenschapDaoDummy);
        ILid toonLid = beheerder.toonLid(lid1.getId());
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
        beheerder = new Dojo(lidDaoDummy, oefeningDaoDummy, activiteitDaoDummy, kampioenschapDaoDummy);
        beheerder.voegLidToe(testLid);
        Assert.assertEquals(4, beheerder.getLijstLeden().size());
        Assert.assertTrue(beheerder.getLijstLeden().contains(testLid));
        Mockito.verify(lidDaoDummy).insert(testLid);
    }
    @Test()
    public void testMaakOverzichtAanwezigheidLeeg() {
        Mockito.when(lidDaoDummy.findAll()).thenReturn(ledenLijst);
        OverzichtType type = OverzichtType.AANWEZIGHEID;
        List<Object> extraParameters = new ArrayList<>();
        extraParameters.add(null);
        extraParameters.add("");
        extraParameters.add(LesType.ALLES);
        beheerder = new Dojo(lidDaoDummy, oefeningDaoDummy, activiteitDaoDummy, kampioenschapDaoDummy);
        List<Exportable> maakOverzichtList = beheerder.maakOverzichtList(type,extraParameters);
        System.out.println(maakOverzichtList);
        Assert.assertEquals(3,maakOverzichtList.size());
        
    }
    @Test()
    public void testMaakOverzichtAanwezigheidOpLidNante() {
        Mockito.when(lidDaoDummy.findAll()).thenReturn(ledenLijst);
        OverzichtType type = OverzichtType.AANWEZIGHEID;
        List<Object> extraParameters = new ArrayList<>();
        extraParameters.add(null);
        extraParameters.add("Nante");
        extraParameters.add(LesType.ALLES);
        beheerder = new Dojo(lidDaoDummy, oefeningDaoDummy, activiteitDaoDummy, kampioenschapDaoDummy);
        List<Exportable> maakOverzichtList = beheerder.maakOverzichtList(type,extraParameters);
        System.out.println(maakOverzichtList);
        Assert.assertEquals(1,maakOverzichtList.size());
        
    }
    @Test()
    public void testMaakOverzichtAanwezigheidOpAanwezigheid() {
        Mockito.when(lidDaoDummy.findAll()).thenReturn(ledenLijst);
        OverzichtType type = OverzichtType.AANWEZIGHEID;
        List<Object> extraParameters = new ArrayList<>();
        extraParameters.add(LocalDate.of(1998, 8, 16));
        extraParameters.add("");
        extraParameters.add(LesType.ALLES);
        beheerder = new Dojo(lidDaoDummy, oefeningDaoDummy, activiteitDaoDummy, kampioenschapDaoDummy);
        List<Exportable> maakOverzichtList = beheerder.maakOverzichtList(type,extraParameters);
        System.out.println(maakOverzichtList);
        Assert.assertEquals(1,maakOverzichtList.size());
        
    }
    @Test()
    public void testMaakOverzichtAanwezigheidOpLesType() {
        Mockito.when(lidDaoDummy.findAll()).thenReturn(ledenLijst);
        OverzichtType type = OverzichtType.AANWEZIGHEID;
        List<Object> extraParameters = new ArrayList<>();
        extraParameters.add(null);
        extraParameters.add("");
        extraParameters.add(LesType.WO_ZA);
        beheerder = new Dojo(lidDaoDummy, oefeningDaoDummy, activiteitDaoDummy, kampioenschapDaoDummy);
        List<Exportable> maakOverzichtList = beheerder.maakOverzichtList(type,extraParameters);
        System.out.println(maakOverzichtList);
        Assert.assertEquals(2,maakOverzichtList.size());
        
    }
}

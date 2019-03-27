/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import persistentie.ActiviteitDao;
import persistentie.KampioenschapDao;
import persistentie.LidDao;
import persistentie.OefeningDao;

/**
 *
 * @author Nante
 */
@RunWith(Parameterized.class)
public class DojoMaakOverzichtListTestParameterized {

    private LidDao lidDaoDummy;
    private OefeningDao oefeningDaoDummy;
    private ActiviteitDao activiteitDaoDummy;
    private KampioenschapDao kampioenschapDaoDummy;
    private Lid lid1;
    private Lid lid2;
    private Lid lid3;
    private final List<Lid> ledenLijst = new ArrayList<>();
    private final List<Activiteit> activiteitenLijst = new ArrayList<>();
    private final List<Oefening> oefeningenLijst = new ArrayList<>();
    private final List<Kampioenschap> kampioenschapLijst = new ArrayList<>();
    private Dojo beheerder;
    private Activiteit act1;
    private Activiteit act2;
    private Oefening oef1;
    private Oefening oef2;
    private Oefening oef3;
    private Kampioenschap kampioenschap1;
    private Kampioenschap kampioenschap2;
    private Kampioenschap kampioenschap3;
    @Before
    public void before() {
        lidDaoDummy = Mockito.mock(LidDao.class);
        oefeningDaoDummy = Mockito.mock(OefeningDao.class);
        activiteitDaoDummy = Mockito.mock(ActiviteitDao.class);
        kampioenschapDaoDummy = Mockito.mock(KampioenschapDao.class);
        lid1 = new Lid("Nante", "Vermeulen", "nv12345", "0479154879", "053548216", "Straat", "100", "/", "9320", "Landegem", Land.België, "98.10.19-333.61", "nante.vermeulen@student.hogent.be",
                "ouders.nante@telenet.be", LocalDate.of(1998, 8, 16), LocalDate.of(2014, 5, 9), new ArrayList<>(Arrays.asList(LocalDate.of(2012, 8, 16))) {
        }, Geslacht.MAN, Graad.GROEN, RolType.BEHEERDER, LesType.DI_ZA);

        lid2 = new Lid("Indy", "Van Canegem", "ivc12345", "0479154978", "053698442", "Straat", "13", "88", "9520", "Zele", Land.België, "98.10.19-333.61", "indy.vancanegem@student.hogent.be",
                "ouders.indy@skynet.be", LocalDate.of(1998, 8, 16), LocalDate.of(2014, 5, 9), new ArrayList<>(), Geslacht.ANDERS, Graad.GROEN, RolType.LID, LesType.WO_ZA);

        lid3 = new Lid("Jef", "Malfliet", "jm12345", "0234567890", "053698420", "Straat", "1", "2.9", "9220", "Hamme", Land.België, "98.10.19-333.61", "jef.malfliet@student.hogent.be",
                "ouders.jef@proxymus.be", LocalDate.of(1999, 10, 24), LocalDate.of(2016, 8, 31), new ArrayList<>(), Geslacht.VROUW, Graad.WIT, RolType.LESGEVER, LesType.WO_ZA);

        act1 = new Activiteit("Uitstap", LocalDate.of(2020, Month.FEBRUARY, 11), LocalDate.of(2020, Month.FEBRUARY, 11), 50, ActiviteitType.UITSTAP);
        act2 = new Activiteit("Weekend", LocalDate.of(2019, Month.APRIL, 5), LocalDate.of(2019, Month.APRIL, 7), 100, ActiviteitType.STAGE);

        oef1 = new Oefening(Graad.GROEN, "Test1");
        oef2 = new Oefening(Graad.DAN1, "Test2");
        oef3 = new Oefening(Graad.BLAUW, "Test3");
        
        kampioenschap1 = new Kampioenschap("kampioenschap1",LocalDate.of(2019,Month.DECEMBER,12),LeeftijdCategorie.BOVEN_15);
        kampioenschap2 = new Kampioenschap("kampioenschap2",LocalDate.of(2019,Month.DECEMBER,20),LeeftijdCategorie.ONDER_15);
        kampioenschap3 = new Kampioenschap("kampioenschap3",LocalDate.of(2019,Month.DECEMBER,30),LeeftijdCategorie.BOVEN_15);
        ledenLijst.addAll(Arrays.asList(lid1, lid2, lid3));
        activiteitenLijst.addAll(Arrays.asList(act1, act2));
        oefeningenLijst.addAll(Arrays.asList(oef1, oef2, oef3));
        kampioenschapLijst.addAll(Arrays.asList(kampioenschap1,kampioenschap2,kampioenschap3));
    }
    private SorteerType type;
    private List<String> extraParameters = new ArrayList<>();
    private int aantal;


    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
            {SorteerType.AANWEZIGHEID, new String[]{"", "",""}, 3},
            {SorteerType.AANWEZIGHEID, new String[]{"", "", LesType.ALLES.toString()}, 3},
            {SorteerType.AANWEZIGHEID, new String[]{"", "Nante", LesType.ALLES.toString()}, 1},
            {SorteerType.AANWEZIGHEID, new String[]{LocalDate.of(2012, 8, 16).toString(), "", LesType.ALLES.toString()}, 1},
            {SorteerType.AANWEZIGHEID, new String[]{"", "", LesType.WO_ZA.toString()}, 2},
            
            {SorteerType.INSCHRIJVING, new String[]{"", "", LesType.ALLES.toString()}, 3},
            {SorteerType.INSCHRIJVING, new String[]{"", "Indy", LesType.ALLES.toString()}, 1},
            {SorteerType.INSCHRIJVING, new String[]{LocalDate.of(2014, 5, 9).toString(), "", LesType.ALLES.toString()}, 2},
            {SorteerType.INSCHRIJVING, new String[]{"", "", LesType.DI_ZA.toString()}, 1},
            
            {SorteerType.ACTIVITEIT, new String[]{"",ActiviteitType.ALLES.toString(),""}, 2},
            {SorteerType.ACTIVITEIT, new String[]{"",ActiviteitType.STAGE.toString(),""}, 1},
            {SorteerType.ACTIVITEIT, new String[]{"",ActiviteitType.UITSTAP.toString(),""}, 1},
            {SorteerType.ACTIVITEIT, new String[]{"",ActiviteitType.ALLES.toString(), "2019"}, 1},
            {SorteerType.ACTIVITEIT, new String[]{"",ActiviteitType.ALLES.toString(), "2020"}, 1},
            {SorteerType.ACTIVITEIT, new String[]{"",ActiviteitType.ALLES.toString(),"2021"}, 0},
            {SorteerType.ACTIVITEIT, new String[]{"",ActiviteitType.ALLES.toString(),"2018"}, 0},
            {SorteerType.ACTIVITEIT, new String[]{"Weekend",ActiviteitType.UITSTAP.toString(), ""}, 0},
            {SorteerType.ACTIVITEIT, new String[]{"Weekend",ActiviteitType.STAGE.toString(), ""}, 1},
            
            {SorteerType.CLUBKAMPIOENSCHAP,new String[]{"",LeeftijdCategorie.ALLES.toString(),""},3},
            {SorteerType.CLUBKAMPIOENSCHAP,new String[]{"kampioenschap1",LeeftijdCategorie.ALLES.toString(),""},1},
            {SorteerType.CLUBKAMPIOENSCHAP,new String[]{"","",LocalDate.of(2019,Month.DECEMBER,12).toString()},1},
            {SorteerType.CLUBKAMPIOENSCHAP,new String[]{"",LeeftijdCategorie.BOVEN_15.toString(),""},2},
            {SorteerType.CLUBKAMPIOENSCHAP,new String[]{"kampioenschap3",LeeftijdCategorie.BOVEN_15.toString(),""},1},
            
            {SorteerType.LESMATERIAAL, new String[]{Graad.ALLES.toString(), ""}, 3},
            {SorteerType.LESMATERIAAL, new String[]{Graad.DAN1.toString(), ""}, 1},
            {SorteerType.LESMATERIAAL, new String[]{Graad.ALLES.toString(), "Test1"}, 1},
            {SorteerType.LESMATERIAAL, new String[]{Graad.DAN11.toString(), ""}, 0},
            {SorteerType.LESMATERIAAL, new String[]{Graad.ALLES.toString(), "Hallo"}, 0},
            {SorteerType.LESMATERIAAL, new String[]{Graad.BLAUW.toString(), "Test3"}, 1}

        });
    }

    public DojoMaakOverzichtListTestParameterized(SorteerType type, String[] extraParameters, int aantal) {
        this.type = type;
        this.extraParameters = Arrays.asList(extraParameters);
        this.aantal = aantal;

    }

    @Test()
    public void testMaakOverzicht() {
        Mockito.when(lidDaoDummy.findAll()).thenReturn(ledenLijst);
        Mockito.when(activiteitDaoDummy.findAll()).thenReturn(activiteitenLijst);
        Mockito.when(oefeningDaoDummy.findAll()).thenReturn(oefeningenLijst);
        Mockito.when(kampioenschapDaoDummy.findAll()).thenReturn(kampioenschapLijst);
        
        
        beheerder = new Dojo(lidDaoDummy, oefeningDaoDummy, activiteitDaoDummy, kampioenschapDaoDummy);
        List<Exportable> maakOverzichtList = beheerder.maakOverzichtList(type, extraParameters);
        Assert.assertEquals(aantal, maakOverzichtList.size());

    }
}

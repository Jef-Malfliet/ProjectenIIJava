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
    private Dojo beheerder;
    private Activiteit act1;
    private Activiteit act2;
    private Oefening oef1;
    private Oefening oef2;
    private Oefening oef3;

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

        act1 = new Activiteit("Uitstap", LocalDate.of(2014, Month.FEBRUARY, 11), LocalDate.of(2014, Month.FEBRUARY, 11), 50, ActiviteitType.UITSTAP);
        act2 = new Activiteit("Weekend", LocalDate.of(2019, Month.MARCH, 5), LocalDate.of(2019, Month.MARCH, 7), 100, ActiviteitType.STAGE);

        oef1 = new Oefening(Graad.GROEN, "Test1");
        oef2 = new Oefening(Graad.DAN1, "Test2");
        oef3 = new Oefening(Graad.BLAUW, "Test3");
        ledenLijst.addAll(Arrays.asList(lid1, lid2, lid3));
        activiteitenLijst.addAll(Arrays.asList(act1, act2));
        oefeningenLijst.addAll(Arrays.asList(oef1, oef2, oef3));
    }
    private OverzichtType type;
    private List<Object> extraParameters = new ArrayList<>();
    private int aantal;

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
            {OverzichtType.AANWEZIGHEID, new Object[]{null, "", LesType.ALLES}, 3},
            {OverzichtType.AANWEZIGHEID, new Object[]{null, "Nante", LesType.ALLES}, 1},
            {OverzichtType.AANWEZIGHEID, new Object[]{LocalDate.of(2012, 8, 16), "", LesType.ALLES}, 1},
            {OverzichtType.AANWEZIGHEID, new Object[]{null, "", LesType.WO_ZA}, 2},
            {OverzichtType.INSCHRIJVING, new Object[]{null, "", LesType.ALLES}, 3},
            {OverzichtType.INSCHRIJVING, new Object[]{null, "Indy", LesType.ALLES}, 1},
            {OverzichtType.INSCHRIJVING, new Object[]{LocalDate.of(2014, 5, 9), "", LesType.ALLES}, 2},
            {OverzichtType.INSCHRIJVING, new Object[]{null, "", LesType.DI_ZA}, 1},
            {OverzichtType.ACTIVITEIT, new Object[]{true, ""}, 1},
            //klopt dit? -- false --> betekent hier dat wel of geen stage niet relevant is
            {OverzichtType.ACTIVITEIT, new Object[]{false, ""}, 2},
            {OverzichtType.ACTIVITEIT, new Object[]{false, "Uitstap"}, 1},
            {OverzichtType.ACTIVITEIT, new Object[]{true, "Uitstap"}, 0},
            //klopt dit? -- false --> betekent hier dat wel of geen stage niet relevant is
            {OverzichtType.ACTIVITEIT, new Object[]{false, "Weekend"}, 1},
            {OverzichtType.ACTIVITEIT, new Object[]{true, "Weekend"}, 1},
            {OverzichtType.LESMATERIAAL, new Object[]{Graad.ALLES, ""}, 3},
            {OverzichtType.LESMATERIAAL, new Object[]{Graad.DAN1, ""}, 1},
            {OverzichtType.LESMATERIAAL, new Object[]{Graad.ALLES, "Test1"}, 1},
            {OverzichtType.LESMATERIAAL, new Object[]{Graad.DAN11, ""}, 0},
            {OverzichtType.LESMATERIAAL, new Object[]{Graad.ALLES, "Hallo"}, 0},
            {OverzichtType.LESMATERIAAL, new Object[]{Graad.BLAUW, "Test3"}, 1}

        });
    }

    public DojoMaakOverzichtListTestParameterized(OverzichtType type, Object[] extraParameters, int aantal) {
        this.type = type;
        this.extraParameters = Arrays.asList(extraParameters);
        this.aantal = aantal;

    }

    @Test()
    public void testMaakOverzichtAanwezigheidLeeg() {
        Mockito.when(lidDaoDummy.findAll()).thenReturn(ledenLijst);
        Mockito.when(activiteitDaoDummy.findAll()).thenReturn(activiteitenLijst);
        Mockito.when(oefeningDaoDummy.findAll()).thenReturn(oefeningenLijst);

        beheerder = new Dojo(lidDaoDummy, oefeningDaoDummy, activiteitDaoDummy, kampioenschapDaoDummy);
        List<Exportable> maakOverzichtList = beheerder.maakOverzichtList(type, extraParameters);
        System.out.println(maakOverzichtList);
        Assert.assertEquals(aantal, maakOverzichtList.size());

    }
}

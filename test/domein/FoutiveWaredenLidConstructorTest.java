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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author Mout
 */
@RunWith(Parameterized.class)
public class FoutiveWaredenLidConstructorTest {

    private final String voorNaam;
    private final String achterNaam;
    private final String wachtwoord;
    private final String gsm;
    private final String telefoon;
    private final String straat;
    private final String huisnummer;
    private final String busnummer;
    private final String postcode;
    private final String stad;
    private final String land;
    private final String email;
    private final String emailOuders;
    private final LocalDate geboorteDatum;
    private final LocalDate inschrijfdatum;
    private final List<LocalDate> aanwezigheden;
    private final Geslacht geslacht;
    private final Graad graad;
    private final RolType type;

    /* het echte lid
    {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID}
     */
    @Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
            //normale gevallen
            {"", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {null, "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", null, "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", null, "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", null, "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", null, "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", null, "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", null, "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", null, "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", null, "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", null, "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", null, "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", null,
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                null, LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            //special gevallen(gsm, telefoon, postcode, email, emailOuders)
            {"Tom", "Clarys", "tc12345", "045614587", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "04561458755", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "00456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "00456145876458", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "05342588", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "0534258899", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "0053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "005342588965412", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "0320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "93208", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "932", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom@clarys@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys.student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "@student.hogent.be",
                "ouders.tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders@tom@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom.telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "@telenet.be", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID},
            {"Tom", "Clarys", "tc12345", "0456145876", "053425889", "Dommedest", "14", "/", "9320", "Erembodegem", "België", "tom.clarys@student.hogent.be",
                "ouders.tom@telenet.", LocalDate.of(1999, Month.DECEMBER, 6), LocalDate.of(2007, Month.MARCH, 5), new ArrayList<>(), Geslacht.MAN, Graad.BLAUW, RolType.LID}

        });
    }

    public FoutiveWaredenLidConstructorTest(String voorNaam, String achterNaam, String wachtwoord, String gsm, String telefoon,
            String straat, String huisnummer, String busnummer, String postcode, String stad, String land, String email, String emailOuders, LocalDate geboorteDatum,
            LocalDate inschrijvingsDatum, List<LocalDate> aanwezigheden, Geslacht geslacht, Graad graad, RolType type) {
        this.voorNaam = voorNaam;
        this.achterNaam = achterNaam;
        this.wachtwoord = wachtwoord;
        this.gsm = gsm;
        this.telefoon = telefoon;
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.busnummer = busnummer;
        this.postcode = postcode;
        this.stad = stad;
        this.land = land;
        this.email = email;
        this.emailOuders = emailOuders;
        this.geboorteDatum = geboorteDatum;
        this.inschrijfdatum = inschrijvingsDatum;
        this.aanwezigheden = aanwezigheden;
        this.geslacht = geslacht;
        this.graad = graad;
        this.type = type;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFoutiveConstructorwaarden() {
        Lid lid = new Lid(voorNaam, achterNaam, wachtwoord, gsm, telefoon, straat, huisnummer, busnummer, postcode, stad, land, email, emailOuders, geboorteDatum, inschrijfdatum, aanwezigheden, geslacht, graad, type);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Arrays;
import java.util.Collection;
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
    private final Graad graad;
    private final String telefoon;
    private final String email;
    private final String straat;
    private final int postcode;
    private final String gemeente;

    @Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
            {null, "Brackeman", Graad.BLAUW, "0494562819", "test@test.test.com", "straat straat", 9300, "Aalst"},
            {"", "Brackeman", Graad.BLAUW, "0494562819", "test@test.test.com", "straat straat", 9300, "Aalst"},
            {"Bert", null, Graad.BLAUW, "0494562819", "test@test.test.com", "straat straat", 9300, "Aalst"},
            {"Bert", "", Graad.BLAUW, "0494562819", "test@test.test.com", "straat straat", 9300, "Aalst"},
            {"Bert", "Brackeman", Graad.BLAUW, null, "test@test.test.com", "straat straat", 9300, "Aalst"},
            {"Bert", "Brackeman", Graad.BLAUW, "", "test@test.test.com", "straat straat", 9300, "Aalst"},
            {"Bert", "Brackeman", Graad.BLAUW, "0494562819", null, "straat straat", 9300, "Aalst"},
            {"Bert", "Brackeman", Graad.BLAUW, "0494562819", "", "straat straat", 9300, "Aalst"},
            {"Bert", "Brackeman", Graad.BLAUW, "0494562819", "test@test.test.com", null, 9300, "Aalst"},
            {"Bert", "Brackeman", Graad.BLAUW, "0494562819", "test@test.test.com", "", 9300, "Aalst"},
            {"Bert", "Brackeman", Graad.BLAUW, "0494562819", "test@test.test.com", "straat straat", 9300, null},
            {"Bert", "Brackeman", Graad.BLAUW, "0494562819", "test@test.test.com", "straat straat", 9300, ""},
            {"Bert", "Brackeman", null, "0494562819", "test@test.test.com", "straat straat", 9300, ""},
            {"Bert", "Brackeman", Graad.BLAUW, "0494562819", "test@test.test.com", "straat straat", 0, ""},
            {"Bert", "Brackeman", Graad.BLAUW, "0494562819", "test@test.test.com", "straat straat", 012345, ""},
            {"Bert", "Brackeman", Graad.BLAUW, "0494562819", "testtest.test.com", "straat straat", 9300, ""},
            {"Bert", "Brackeman", Graad.BLAUW, "0494562819", "Test@", "straat straat", 9300, ""},
            {"Bert", "Brackeman", Graad.BLAUW, "0494562819", "_test@test.com", "straat straat", 9300, ""},
            {"Bert", "Brackeman", Graad.BLAUW, "0494562819", "test@test&.com", "straat straat", 9300, ""},
            {"Bert", "Brackeman", Graad.BLAUW, "045615203", "test@test.com", "straat straat", 9300, ""},
            {"Bert", "Brackeman", Graad.BLAUW, "04945628198", "test@test.com", "straat straat", 9300, ""}
        });
    }

    public FoutiveWaredenLidConstructorTest(String voorNaam, String achterNaam, Graad graad, String telefoon, String email, String straat, int postcode, String gemeente) {
        this.voorNaam = voorNaam;
        this.achterNaam = achterNaam;
        this.graad = graad;
        this.telefoon = telefoon;
        this.email = email;
        this.straat = straat;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFoutiveConstructorwaarden() {
        Lid lid = new Lid(voorNaam, achterNaam, graad, telefoon, email, straat, postcode, gemeente);
    }
}

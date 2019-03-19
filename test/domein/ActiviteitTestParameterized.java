/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import org.eclipse.persistence.jpa.jpql.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Jef
 */
@RunWith(Parameterized.class)
public class ActiviteitTestParameterized {

    private final String naam;
    private final LocalDate beginDate;
    private final LocalDate eindDate;
    private final int maxAanwezigen;
    private final ActiviteitType type;
    private final boolean shouldFail;

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
            {"act", LocalDate.now(), LocalDate.now().plusDays(1l), 20, ActiviteitType.STAGE, false},
            {"", LocalDate.now(), LocalDate.now().plusDays(1l), 20, ActiviteitType.STAGE, true},
            {null, LocalDate.now(), LocalDate.now().plusDays(1l), 20, ActiviteitType.STAGE, true},
            {"act", LocalDate.now().minusDays(1l), LocalDate.now().plusDays(1l), 20, ActiviteitType.STAGE, true},
            {"act", null, LocalDate.now().plusDays(1l), 20, ActiviteitType.STAGE, true},
            {"act", LocalDate.now(), LocalDate.now().minusDays(1l), 20, ActiviteitType.STAGE, true},
            {"act", LocalDate.now(), null, 20, ActiviteitType.STAGE, true},
            {"act", LocalDate.now().plusDays(10l), LocalDate.now().plusDays(5l), 20, ActiviteitType.STAGE, true},
            {"act", LocalDate.now(), LocalDate.now().plusDays(1l), 0, ActiviteitType.STAGE, true},
            {"act", LocalDate.now(), LocalDate.now().plusDays(1l), -1, ActiviteitType.STAGE, true},
            {"act", LocalDate.now(), LocalDate.now().plusDays(1l), 20, ActiviteitType.ALLES, true},
            {"act", LocalDate.now(), LocalDate.now().plusDays(1l), 20, null, true}
        });
    }

    public ActiviteitTestParameterized(String naam, LocalDate beginDate, LocalDate eindDate, int maxAanwezigen, ActiviteitType type, boolean shouldFail) {
        this.naam = naam;
        this.beginDate = beginDate;
        this.eindDate = eindDate;
        this.maxAanwezigen = maxAanwezigen;
        this.type = type;
        this.shouldFail = shouldFail;
    }

    @Test
    public void maakActiviteit() {
        boolean failed = false;
        String errorMessage = "invalid";
        try {
            Activiteit act = new Activiteit(naam, beginDate, eindDate, maxAanwezigen, type);
            act.wijzigActiviteit(naam, beginDate, eindDate, maxAanwezigen, type);
        } catch (Exception ex) {
            failed = true;
            errorMessage = ex.getMessage();
        }

        Assert.isEqual(shouldFail, failed, errorMessage);
    }

}

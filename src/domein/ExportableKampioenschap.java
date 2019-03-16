/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author Mout
 */
public class ExportableKampioenschap implements Exportable<Kampioenschap> {

    @Override
    public String excelFormat(Kampioenschap k) {
        return String.format("%s,%s,%s,%s", k.getName(), k.getDate().toString(), k.getLeeftijdCategorie().toString(), k.geefAanwezigen().stream()
                .map(l -> l.getVoornaam() + " " + l.getFamilienaam()).collect(Collectors.joining("\n")));
    }

    @Override
    public String excelheaders() {
        return String.format("%s,%s,%s,%s", "Naam", "Datum", "Leeftijdscategorie","Aanwezige leden");
    }

}

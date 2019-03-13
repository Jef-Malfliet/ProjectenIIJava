/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Arrays;

/**
 *
 * @author Mout
 */
public class ExportableKampioenschap implements Exportable<Kampioenschap> {

    @Override
    public String excelFormat(Kampioenschap k) {
        return String.format("%s,%s,%s,%s", k.getNaam(), k.getDatum().toString(), Arrays.toString(k.getGewichtcategorie()), k.geefAanwezigen().stream().map(l -> l.getVoornaam() + " " + l.getFamilienaam()));
    }

    @Override
    public String excelheaders() {
        return String.format("%s,%s,%s,%s", "Naam", "Datum", "Gewichtscategorieën", "Aanwezige leden");
    }

}

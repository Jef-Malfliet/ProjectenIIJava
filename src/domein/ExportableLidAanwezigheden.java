/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.stream.Collectors;

/**
 *
 * @author Mout
 */
public class ExportableLidAanwezigheden implements Exportable<Lid> {

    @Override
    public String excelFormat(Lid lid) {
        return String.format("%s %s, %s, %s", lid.getVoornaam(), lid.getFamilienaam(), lid.getLessen(), lid.getAanwezigheden().stream().map(a -> a.toString()).collect(Collectors.joining("\n")));
    }

    @Override
    public String excelheaders() {
        return String.format("%s, %s, %s", "Naam","Formule", "Aanwezigheden");
    }

}

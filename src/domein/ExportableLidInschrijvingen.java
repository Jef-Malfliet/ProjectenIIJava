/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author Mout
 */
public class ExportableLidInschrijvingen implements Exportable<Lid> {

    @Override
    public String excelFormat(Lid lid) {
        return String.format("%s %s,%s,%s%n", lid.getVoornaam(), lid.getFamilienaam(), lid.getInschrijvingsdatum().toString(), lid.getLessen().toString());

    }

    @Override
    public String excelheaders() {
        return String.format("%s,%s,%s%n", "Naam", "Inschrijfdatum", "Formule");
    }

}

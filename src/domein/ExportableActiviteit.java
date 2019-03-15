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
public class ExportableActiviteit implements Exportable<Activiteit> {

    @Override
    public String excelFormat(Activiteit activiteit) {
        return String.format("%s,%s,%s,%s", activiteit.getNaam(), activiteit.getBeginDatum().toString(), activiteit.getEindDatum(), activiteit.getActiviteitType().toString());
    }

    @Override
    public String excelheaders() {
        return String.format("%s,%s,%s,%s", "Naam", "Startdatum", "EindDatum", "Type");
    }

}

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
public class ExportableOefening implements Exportable<Oefening>{

    @Override
    public String excelFormat(Oefening o) {
        return String.format("%s,%s,%s,%s", o.getNaam(), o.getGraad().toString(),o.getVideo(),o.getImages());
    }

    @Override
    public String excelheaders() {
        return String.format("%s,%s,%s,%s","Naam","Graad","Video URL","Image paden");
    }
    
}

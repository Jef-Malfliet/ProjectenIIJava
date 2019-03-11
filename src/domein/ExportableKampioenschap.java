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
public class ExportableKampioenschap implements Exportable<Kampioenschap>{

    @Override
    public String excelFormat(Kampioenschap k) {
        return null;
    }

    @Override
    public String excelheaders() {
        return null;
    }
    
}

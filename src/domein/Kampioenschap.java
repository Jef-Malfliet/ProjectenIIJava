package domein;

import java.util.*;

public class Kampioenschap implements Exportable<Kampioenschap> {

    private Collection<Lid> aanwezigen;
    private Date datum;
    private int[] gewichtcategorie;
    private static Exportable<Kampioenschap> exportable;

    /**
     *
     * @param datum
     * @param gewichtcategorie
     */
    public Kampioenschap(Date datum, int[] gewichtcategorie) {
        // TODO - implement Kampioenschap.Kampioenschap
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param lid
     */
    public void lidInschrijven(Lid lid) {
        // TODO - implement Kampioenschap.lidInschrijven
        throw new UnsupportedOperationException();
    }

    public List<Lid> geefAanwezigen() {
        // TODO - implement Kampioenschap.geefAanwezigen
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param lid
     */
    public void lidUitschrijven(Lid lid) {
        // TODO - implement Kampioenschap.lidUitschrijven
        throw new UnsupportedOperationException();
    }

    public static Exportable<Kampioenschap> getExportable() {
        return exportable;
    }

    public static void setExportable(Exportable<Kampioenschap> exportable) {
        Kampioenschap.exportable = exportable;
    }
    
    

    @Override
    public String excelFormat(Kampioenschap object) {
        return exportable.excelFormat(this);
    }

    @Override
    public String excelheaders() {
        return excelheaders();
    }

}

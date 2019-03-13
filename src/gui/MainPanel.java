/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javafx.scene.layout.BorderPane;
import util.FullScreenResolution;

/**
 *
 * @author IndyV
 */
public class MainPanel extends BorderPane {

    private DomeinController dc;

    //BeheerderControl = 1.5/10 of the full screen.
    public final double beheerderControlWidth = (FullScreenResolution.getWidth() / 10) * 1.5;
    //DetailPaneel = 4.25/10 of the full screen.
    private final double detailPaneelWidth = (FullScreenResolution.getWidth() / 10) * 4.25;
    //OverzichtScene = 4.25/10 of the full screen.
    private final double overzichtWidth = (FullScreenResolution.getWidth() / 10) * 4.25;
    //OverzichtScene = 8.5/10 of the full screen.
    private final double overzichtOpvraagWidth = (FullScreenResolution.getWidth() / 10) * 8.5;
    //BeheermateriaalWidth = 8.5 of the full screen.
    private final double beheerMateriaalWidth = (FullScreenResolution.getWidth() / 10) * 8.5;
    //LesmateriaaloverzichtWidth
    private final double lesmateriaalOverzichtWidth = (FullScreenResolution.getWidth() / 10) * 4.25;
    //LesmateriaalDetail
    private final double lesmateriaalDetailWidth = (FullScreenResolution.getWidth() / 10) * 4.25;
    //Height is voor alles hetzelfde. = max height.
    private final double height = FullScreenResolution.getHeight();

    public MainPanel(DomeinController dc) {
        this.dc = dc;

        DetailPaneelController dpc = new DetailPaneelController(dc);
        dpc.setPrefSize(detailPaneelWidth, height);

        OverzichtSceneController osc = new OverzichtSceneController(dc, dpc);
        osc.setPrefSize(overzichtWidth, height);
        dpc.setOverzichtSceneController(osc);
        dc.addPropertyChangeListener(osc);
        OverzichtOpvraagSceneController opsc = new OverzichtOpvraagSceneController(dc);
        opsc.setPrefSize(overzichtOpvraagWidth, height);
        LesmateriaalDetailPaneelController ldpc = new LesmateriaalDetailPaneelController(dc);
        ldpc.setPrefSize(beheerMateriaalWidth, height);
        ActiviteitDetailPaneelController adpc = new ActiviteitDetailPaneelController(dc);
        ActiviteitOverzichtSceneController aosc = new ActiviteitOverzichtSceneController(dc, adpc);
        adpc.setAosc(aosc);
        
        LesmateriaalOverzichtSceneController losc = new LesmateriaalOverzichtSceneController(dc,ldpc);
        dc.addPropertyChangeListener(losc);
        ldpc.setLesmateriaalOverzicht(losc);
        losc.setPrefSize(lesmateriaalOverzichtWidth, height);
        BeheerderControlController bcc = new BeheerderControlController(dc, osc, this, opsc, ldpc, aosc, adpc,losc);
        bcc.setPrefSize(beheerderControlWidth, height);

        this.setCenter(osc);
        this.setLeft(bcc);
        this.setRight(dpc);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import util.FullScreenResolution;

/**
 *
 * @author IndyV
 */
public class MainPanel extends BorderPane {

    private DomeinController dc;
    
    //BeheerderControl = 2/10 of the full screen.
    public final double beheerderControlWidth = (FullScreenResolution.getWidth()/10)*1.5;
    //DetailPaneel = 4/10 of the full screen.
    private final double detailPaneelWidth = (FullScreenResolution.getWidth()/10)*4.25;
    //OverzichtScene = 4/10 of the full screen.
    private final double overzichtWidth = (FullScreenResolution.getWidth()/10)*4.25;
    //OverzichtScene = 8/10 of the full screen.
    private final double overzichtOpvraagWidth = (FullScreenResolution.getWidth()/10)*8.5;
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
        
        BeheerderControlController bcc = new BeheerderControlController(dc, osc, this, opsc);
        bcc.setPrefSize(beheerderControlWidth, height);
        
        this.setCenter(osc);
        this.setLeft(bcc);
        this.setRight(dpc);
    }

}

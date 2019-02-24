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

/**
 *
 * @author IndyV
 */
public class MainPanel extends BorderPane {

    private DomeinController dc;
    private final double SCREENWIDTH = 1440;
    private final double SCREENHEIGHT = 900;

    public MainPanel(DomeinController dc) {
        this.dc = dc;

        DetailPaneelController dpc = new DetailPaneelController(dc);
        dpc.setPrefSize(600, SCREENHEIGHT);
        OverzichtSceneController osc = new OverzichtSceneController(dc, dpc);
        osc.setPrefSize(600, SCREENHEIGHT);
        dpc.setOverzichtSceneController(osc);
        dc.addPropertyChangeListener(osc);
        BeheerderControlController bcc = new BeheerderControlController(dc, osc, this, new OverzichtOpvraagSceneController(dc));
        bcc.setPrefSize(250, SCREENHEIGHT);
        this.setCenter(osc);
        this.setLeft(bcc);
        this.setRight(dpc);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author IndyV
 */
public class MainPanel extends BorderPane{
    
    private DomeinController dc;
    private final double SCREENWIDTH = 1440;
    private final double SCREENHEIGHT = 900;
    
    public MainPanel(DomeinController dc){
        this.dc=dc;
        BeheerderControlController bcc = new BeheerderControlController(dc);
        bcc.setPrefSize(250, SCREENHEIGHT);
        DetailPaneelController dpc = new DetailPaneelController(dc);
        dpc.setPrefSize(1190, SCREENHEIGHT);
        OverzichtSceneController osc = new OverzichtSceneController(dc,dpc);
        osc.setPrefSize(1190, SCREENHEIGHT);
       
        this.setCenter(osc);
        this.setLeft(bcc);
        this.setRight(dpc);
    }
    
}

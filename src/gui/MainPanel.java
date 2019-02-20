/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.scene.layout.BorderPane;

/**
 *
 * @author IndyV
 */
public class MainPanel extends BorderPane{
    
    public MainPanel(){
        StrangerControlsController scc = new StrangerControlsController();
        LidControlsController lcc = new LidControlsController();
        BeheerderControlsController bcc = new BeheerderControlsController();
        this.setRight(lcc);
        this.setLeft(scc);
        this.setCenter(bcc);
    }
    
}

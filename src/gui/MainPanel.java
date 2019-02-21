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
    
    public MainPanel(DomeinController dc){
        this.dc=dc;
        BeheerderControlController bcc = new BeheerderControlController(dc);
        this.setLeft(bcc);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Date;

/**
 *
 * @author Jef
 */
public interface IActiviteit {
    long getId();
    
    String getNaam();
    
    Date getStartDatum();
    
    Date getEindDatum();
    
    boolean isStage(); 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Jef
 */
public interface IActiviteit {
    long getId();
    
    String getNaam();
    
    LocalDate getStartDatum();
    
    LocalDate getEindDatum();
    
    boolean isStage();
    
    SimpleStringProperty getNaamProperty();
    
    SimpleStringProperty getBeginDatumProperty();
    
    SimpleStringProperty getEindDatumProperty();
    
    SimpleStringProperty getStageProperty();
}

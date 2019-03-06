/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

/**
 *
 * @author IndyV
 */
public interface IOefening extends Serializable {

    SimpleStringProperty getNaamProperty();

    SimpleStringProperty getVideoProperty();

    SimpleStringProperty getGraadProperty();
    
    String getUitleg();
    
    List<Image>getImages();
    
    String getVideo();
    
    Graad getGraad();
    
    String getNaam();
    
    long getId();
    

}
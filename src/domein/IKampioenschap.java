/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Mout
 */
public interface IKampioenschap extends Serializable {

    LocalDate getDatum();

    String[] getGewichtcategorie();

    SimpleStringProperty getDatumProperty();

    SimpleStringProperty getGewichtcategorieënProperty();

    SimpleStringProperty getLeeftijdcategorieënProperty();
    
    SimpleStringProperty getNaamProperty();

    List<Lid> geefAanwezigen();
}

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

    SimpleStringProperty getDatumProperty();

    SimpleStringProperty getNaamProperty();

    SimpleStringProperty getLeeftijdCategorieProperty();

    List<Lid> geefAanwezigen();

    LocalDate getDate();

    String getName();
    
    LeeftijdCategorie getLeeftijdCategorie();

}

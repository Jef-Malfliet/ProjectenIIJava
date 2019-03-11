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
 * @author Nante
 */
public interface ILid extends Serializable {

    long getId();

    String getVoornaam();

    String getFamilienaam();

    String getWachtwoord();

    String getGsm();

    String getTelefoon_vast();

    String getStraatnaam();

    String getHuisnummer();

    String getBusnummer();

    String getPostcode();

    String getStad();

    Land getLand();

    String getEmail();

    String getEmail_ouders();

    LocalDate getGeboortedatum();

    LocalDate getInschrijvingsdatum();

    List<LocalDate> getAanwezigheden();

    Geslacht getGeslacht();

    RolType getType();

    Graad getGraad();

    LesType getLessen();

    String getRijksregisternummer();

    SimpleStringProperty getVoornaamProperty();

    SimpleStringProperty getFamilienaamProperty();

    SimpleStringProperty getGraadProperty();

    SimpleStringProperty getTypeProperty();
    
    SimpleStringProperty getLessenProperty();
    
    SimpleStringProperty getInschrijvingsDatumProperty();

}

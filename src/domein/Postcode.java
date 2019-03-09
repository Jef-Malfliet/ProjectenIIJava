/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.List;

/**
 *
 * @author Nante
 */
public class Postcode {
    private String postcode;
    private List<String> gemeentes;
    private Land land;

    public Postcode(String postcode, List<String> gemeente,Land land) {
        this.postcode = postcode;
        this.gemeentes = gemeente;
        this.land = land;
    }

    @Override
    public String toString() {
        return "Postcode{" + "postcode=" + postcode + ", gemeente=" + gemeentes + '}';
    }

    public List<String> getGemeentes() {
        return gemeentes;
    }

    public Land getLand() {
        return land;
    }
    
    
    
}

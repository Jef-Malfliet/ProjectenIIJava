package domein;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.*;

/**
 *
 * @author Nante
 */
@Entity
public class Lid implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String naam;
    private Graad graad;
    private Type type;

    public Lid() {
    }

    /**
	 * 
	 * @param naam
	 * @param graad
	 * @param type
	 */
	public Lid(String naam, Graad graad, Type type) {
        this.naam = naam;
        this.graad = graad;
        this.type = type;
    }

    public String getNaam() {
        return naam;
    }

    @javax.persistence.Column(name="graad")
	public Graad getGraad() {
        return graad;
    }

    @javax.persistence.Transient
	public Type getType() {
        return type;
    }
    

    
}

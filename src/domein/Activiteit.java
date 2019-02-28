package domein;

import java.util.*;

public class Activiteit {

	private Collection<Lid> aanwezigen;
	private Date startDatum;
	private Date eindDatum;
	private boolean stage;

    public Activiteit(Date startDatum, Date eindDatum, boolean stage) {
        this.startDatum = startDatum;
        this.eindDatum = eindDatum;
        this.stage = stage;
    }

        
        
	public List<Lid> geefAanwezigen() {
		// TODO - implement Activiteit.geefAanwezigen
		throw new UnsupportedOperationException();
	}

}
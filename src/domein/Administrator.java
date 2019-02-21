package domein;

import persistentie.*;
import java.util.*;

public class Administrator {

	private PersistentieController persistentieController;
	private final Collection<Lid> lijstLeden;
	private final Type type;

	public Collection<Lid> getLijstLeden() {
		return this.lijstLeden;
	}

	public Type getType() {
		return this.type;
	}

	/**
	 * 
	 * @param lid
	 */
	public boolean wijzigLid(Lid lid) {
		// TODO - implement Administrator.wijzigLid
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param lid
	 */
	public boolean verwijderLid(Lid lid) {
		// TODO - implement Administrator.verwijderLid
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param lid
	 */
	public boolean voegLidToe(Lid lid) {
		// TODO - implement Administrator.voegLidToe
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param persistentieController
	 */
	public void Beheerder(PersistentieController persistentieController) {
		// TODO - implement Administrator.Beheerder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param persistentieController
	 */
	private void setPersistentieController(PersistentieController persistentieController) {
		this.persistentieController = persistentieController;
	}

	public void Beheerder() {
		// TODO - implement Administrator.Beheerder
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public Lid toonLid(long id) {
		// TODO - implement Administrator.toonLid
		throw new UnsupportedOperationException();
	}

}
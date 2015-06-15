package jeu;

import java.io.File;

public class Carte {

	private File file;
	private String type;
	private String zone;
	private int num�ro;
	private String nom;
	
	public Carte(String type, String zone, int num�ro){
		this.type = type;
		this.zone = zone;
		this.num�ro = num�ro;
		this.nom = this.type + "-" + this.zone + "-" + Integer.toString(this.num�ro);
		this.file = new File(this.nom);
	}
	
	public String toString(){
		return this.nom;
	}

	public String getZone() {
		return this.zone;
	}
}

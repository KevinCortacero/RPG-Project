package jeu;

import java.io.File;

public class Carte {

	private File file;
	private String type;
	private String zone;
	private int numéro;
	private String nom;
	
	public Carte(String type, String zone, int numéro){
		this.type = type;
		this.zone = zone;
		this.numéro = numéro;
		this.nom = this.type + "-" + this.zone + "-" + Integer.toString(this.numéro);
		this.file = new File(this.nom);
	}
	
	public String toString(){
		return this.nom;
	}

	public String getZone() {
		return this.zone;
	}
}

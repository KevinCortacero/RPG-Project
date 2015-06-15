package jeu;

import java.io.File;

public class Carte {

	private File file;
	private String type;
	private String lieu;
	private int num�ro;
	private String nom;
	
	public Carte(String type, String lieu, int num�ro){
		this.type = type;
		this.lieu = lieu;
		this.num�ro = num�ro;
		this.nom = this.type + "-" + this.lieu + "-" + Integer.toString(this.num�ro);
		this.file = new File(this.nom);
	}
	
	public String toString(){
		return this.nom;
	}
}

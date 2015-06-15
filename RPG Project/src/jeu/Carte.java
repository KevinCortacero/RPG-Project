package jeu;

import java.io.File;

public class Carte {

	private File file;
	private String type;
	private String lieu;
	private int numéro;
	private String nom;
	
	public Carte(String type, String lieu, int numéro){
		this.type = type;
		this.lieu = lieu;
		this.numéro = numéro;
		this.nom = this.type + "-" + this.lieu + "-" + Integer.toString(this.numéro);
		this.file = new File(this.nom);
	}
	
	public String toString(){
		return this.nom;
	}
}

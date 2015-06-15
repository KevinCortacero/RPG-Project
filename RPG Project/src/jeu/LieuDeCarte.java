package jeu;

import java.util.ArrayList;
import java.util.List;

public class LieuDeCarte {
	
	private String zone;
	private String type;
	private List<Carte> listeCartes;	
	
	public LieuDeCarte(String type, String zone, int numéro){
		this.type = type;
		this.zone = zone;
		this.listeCartes = new ArrayList<Carte>();
		this.listeCartes.add(new Carte(this.type, this.zone, numéro));
		this.listeCartes.add(new Carte(this.type, this.zone, numéro));
	}
	
	public String toString(){
		return "Zone : " + this.zone;
	}
	
	public List<Carte> getListeCarte(){
		return this.listeCartes;
	}
}

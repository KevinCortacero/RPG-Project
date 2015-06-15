package jeu;

import java.util.ArrayList;
import java.util.List;

public class ZoneDeCarte {
	
	private String zone;
	private String type;
	private List<Carte> listeCartes;	
	
	public ZoneDeCarte(String type, String zone){
		this.type = type;
		this.zone = zone;
		this.listeCartes = new ArrayList<Carte>();
	}
	
	public String toString(){
		return "Zone : " + this.zone;
	}
	
	public List<Carte> getListeCarte(){
		return this.listeCartes;
	}
	
	public void addCarte(Carte carte){
		this.listeCartes.add(carte);
	}
}

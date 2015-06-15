package jeu;

import java.util.HashMap;
import java.util.Map;

public class ZoneDeCarte {
	
	private String zone;
	private String type;
	private Map<String,Carte> listeCartes;	
	
	public ZoneDeCarte(String type, String zone){
		this.type = type;
		this.zone = zone;
		this.listeCartes = new  HashMap<String,Carte>();
	}
	
	public String toString(){
		return "Zone : " + this.zone;
	}
	
	public  Map<String,Carte> getListeCarte(){
		return this.listeCartes;
	}
	
	public void addCarte(Carte carte){
		this.listeCartes.put(carte.getZone(),carte);
	}
}

package jeu;

import java.util.HashMap;
import java.util.Map;

public class TypeDeCarte {
	
	private String type;
	private Map<String,ZoneDeCarte> listeZoneCartes;	
	
	public TypeDeCarte(String type){
		this.type = type;
		this.listeZoneCartes = new HashMap<String,ZoneDeCarte>();
		
		if (this.type == "Base")
			this.listeZoneCartes.add(new ZoneDeCarte(this.type, "Maison"));
		if (this.type == "Village")
			this.listeZoneCartes.add(new ZoneDeCarte(this.type,"Banque"));
		if (this.type == "Level"){
			this.listeZoneCartes.add(new ZoneDeCarte(this.type,"Montagne"));
			this.listeZoneCartes.add(new ZoneDeCarte(this.type,"Plaine"));
		}
	}
	
	public String toString(){
		return "Type : " + this.type;
	}
	
	public List<ZoneDeCarte> getListeCarte(){
		return this.listeZoneCartes;
	}
	
	public void addCarte(Carte carte){
		this.listeZoneCartes.get(0).addCarte(carte);
	}
}

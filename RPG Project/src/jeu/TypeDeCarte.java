package jeu;

import java.util.ArrayList;
import java.util.List;

public class TypeDeCarte {
	
	private String type;
	private List<ZoneDeCarte> listeLieuCartes;	
	
	public TypeDeCarte(String type){
		this.type = type;
		this.listeLieuCartes = new ArrayList<ZoneDeCarte>();
		
		if (this.type == "Base")
			this.listeLieuCartes.add(new ZoneDeCarte(this.type, "Maison"));
		if (this.type == "Village")
			this.listeLieuCartes.add(new ZoneDeCarte(this.type,"Banque"));
		if (this.type == "Level")
			this.listeLieuCartes.add(new ZoneDeCarte(this.type,"Montagne"));
			this.listeLieuCartes.add(new ZoneDeCarte(this.type,"Plaine"));
	}
	
	public String toString(){
		return "Type : " + this.type;
	}
	
	public List<ZoneDeCarte> getListeCarte(){
		return this.listeLieuCartes;
	}
}

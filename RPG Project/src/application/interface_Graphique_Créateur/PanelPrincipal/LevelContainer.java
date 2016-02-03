package application.interface_Graphique_Créateur.PanelPrincipal;

import java.util.HashMap;
import java.util.Map;

import application.interface_Graphique_Créateur.Origin;

public class LevelContainer {

	private Map<String, Level> listeLevel; 
	private Level level;
	
	public LevelContainer() {
		this.listeLevel = new HashMap<String, Level>();  
	}
	
	public Level getLevel() {
		return this.level;
	}
	
	public Map<String, Level> getListeLevel() {
		return listeLevel;
	}

	public void ajouterLevel(String string,Level level){
		this.listeLevel.put(string, level);
	}

	public void changerLevel(String clé){
		Origin.reset();
		this.level = this.listeLevel.get(clé);
		this.level.charger();
	}
	
	
}
package application.interface_Graphique_Cr�ateur.PanelPrincipal;

import java.util.HashMap;
import java.util.Map;

import application.fonction.Origine;
import application.interface_Graphique_Cr�ateur.FrameCr�ateur;

public class LevelContainer{

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

	public void changerLevel(String cl�){
		Origine.reset();
		System.out.println("On change de Level dans le Container");
		this.level = this.listeLevel.get(cl�);
		System.out.println(FrameCr�ateur.getFrame().getKeyListeners()[0]);
	}
}
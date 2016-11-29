package application.interface_Graphique_Créateur.PanelPrincipal;

import java.util.HashMap;
import java.util.Map;

import application.fonction.Origine;

public class LevelContainer{

	private Map<String, GameLevel> gameLevels; 
	private GameLevel level;

	public LevelContainer() {
		this.gameLevels = new HashMap<String, GameLevel>();  
	}

	public GameLevel getLevel() {
		return this.level;
	}

	public Map<String, GameLevel> getListeLevel() {
		return gameLevels;
	}

	public void addGameLevel(String path){
		try {
			this.gameLevels.put(path, new GameLevel(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changerLevel(String clé){
		Origine.reset();
		this.level = this.gameLevels.get(clé);
		LevelDesigner.getPanel().repaint();
	}
}
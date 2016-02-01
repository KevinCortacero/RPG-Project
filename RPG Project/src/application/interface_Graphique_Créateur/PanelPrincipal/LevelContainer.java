package application.interface_Graphique_Créateur.PanelPrincipal;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import application.interface_Graphique_Créateur.Origin;
import application.interface_Graphique_Créateur.PanelObjets.ObjetIcone;
import application.jeu.ObjetCourant;

public class LevelContainer {

	private Map<String, Level> listeMapFile; 
	private Level level;
	
	public LevelContainer() {
		this.level = new Level(this,new File("cartes\\test.txt"));
		this.listeMapFile = new HashMap<String, Level>();
		this.listeMapFile.put(this.level.getCurrentFile().getPath(), this.level);  
		Origin.getOrigin();
	}
	
	public Level getMapFileCourante() {
		return this.level;
	}
	
	public Map<String, Level> getListeMapFile() {
		return listeMapFile;
	}


	public void ajouterMapFile(String string,Level mapFile){
		this.listeMapFile.put(string, mapFile);
	}

	public void changerMapFileCourante(String clé){
		this.level = this.listeMapFile.get(clé);
		Origin.setX(0);
		Origin.setY(0);
	}

	public void afficherCarte(Graphics g){
		this.level.afficherCarte(g);
	}
}
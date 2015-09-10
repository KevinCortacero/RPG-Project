package interface_Graphique_Créateur;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;

import jeu.ObjetCourant;

public class Map {

	protected MapFile mapFile;
	private ImageIcon background;
	private PanelChoixObjetsCréateur panelChoixObjetsCréateur;
	protected java.util.Map<String, MapFile> listeMapFile; 

	public Map() {
		this.mapFile = new MapFile(this,new File("cartes\\test.txt"));
		this.listeMapFile = new HashMap<String, MapFile>();
		this.listeMapFile.put("cartes\\test.txt", this.mapFile);
		Origin.getOrigin();
	}
	
	public void ajouterMapFile(String string,MapFile mapFile){
		this.listeMapFile.put(string, mapFile);
	}

	public void setPanel(PanelChoixObjetsCréateur panelChoixObjetsCréateur){
		this.mapFile.panelChoixObjetsCréateur = panelChoixObjetsCréateur;
		this.panelChoixObjetsCréateur = panelChoixObjetsCréateur;
	}


	public void changerMapFile(File fileMap){
		this.mapFile = new MapFile(this,fileMap);
		this.mapFile.panelChoixObjetsCréateur = this.panelChoixObjetsCréateur;
		Origin.setX(0);
		Origin.setY(0);
	}

	public void afficherCarte(Graphics g){

		// affichage du background en premier
		if (this.background != null && this.mapFile.backgroundNum != 0)
			g.drawImage(this.background.getImage(), 0, 0,Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height,  null);

		// affichage des Tiles
		if (!this.mapFile.map.isEmpty()){
			for (Tile tile : this.mapFile.map){
				if (tile.getNuméro() > 1)
			g.drawImage(tile.getImageIcon().getImage(),(int) Origin.getX() + tile.getX()*ObjetIcone.tailleImageJeu,(int)Origin.getY() + tile.getY()*ObjetIcone.tailleImageJeu,null);

			}

		}

		// affichage des bordures
		for(int y = 0; y <= this.mapFile.tileSize.getY() ; y ++){
			for(int x = 0; x <= this.mapFile.tileSize.getX() ; x ++){
				if (y == this.mapFile.tileSize.getY() && x != this.mapFile.tileSize.getX() )
					g.drawImage(new ImageIcon("imagesSpeciales\\bordureV.jpg").getImage(),(int) Origin.getX() + x*ObjetIcone.tailleImageJeu, (int)Origin.getY() + y*ObjetIcone.tailleImageJeu, null);

				if (x == this.mapFile.tileSize.getX() && y != this.mapFile.tileSize.getY() )
					g.drawImage(new ImageIcon("imagesSpeciales\\bordureH.jpg").getImage(),(int) Origin.getX() + x*ObjetIcone.tailleImageJeu, (int)Origin.getY() + y*ObjetIcone.tailleImageJeu, null);

			}
		}

		g.drawImage(this.mapFile.map.get(0).getImageIcon().getImage(),(int) Origin.getX() + this.mapFile.map.get(0).getX()*ObjetIcone.tailleImageJeu,(int) Origin.getY() + this.mapFile.map.get(0).getY()*ObjetIcone.tailleImageJeu,null);
	}

	public void gestionClicGauche(int x, int y, ObjetCourant objetCourant){

		// on remplace la Tile
		if (objetCourant.getNuméro() != this.mapFile.tileSize.getNuméro() && this.mapFile.getTile(x, y).getNuméro() != this.mapFile.tileSize.getNuméro()){
			this.mapFile.map.remove(this.mapFile.getTile(x, y));
			this.mapFile.map.add(new Tile(x, y, objetCourant.getImageIcon(), objetCourant.getNuméro()));
		}

		// on replace la bordure s'il s'agit de la Tile de gestion de la taille de la map
		if (objetCourant.getNuméro() == this.mapFile.tileSize.getNuméro()){
			this.mapFile.tileSize.setX(x);
			this.mapFile.tileSize.setY(y);
		}

		this.mapFile.remplirMatrice();
	}

	public void setBackground(Image imageTailleRéelle, int backgroundNum) {
		if (imageTailleRéelle == null)
			this.background = null;
		else
			this.background = new ImageIcon(imageTailleRéelle);	
		this.mapFile.backgroundNum = backgroundNum;
	}
}

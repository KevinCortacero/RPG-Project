package interface_Graphique_Créateur;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;

import jeu.ObjetCourant;

public class Map {

	protected MapFile mapFile;
	private ImageIcon background;
	private PanelChoixObjetsCréateur panelChoixObjetsCréateur;

	public Map() {
		this.mapFile = new MapFile(this,new File("cartes/test.txt"));
	}

	public void setPanel(PanelChoixObjetsCréateur panelChoixObjetsCréateur){
		this.mapFile.panelChoixObjetsCréateur = panelChoixObjetsCréateur;
		this.panelChoixObjetsCréateur = panelChoixObjetsCréateur;
	}
	
	public void changerMapFile(File fileMap){
		this.mapFile = new MapFile(this,fileMap);
		this.mapFile.panelChoixObjetsCréateur = this.panelChoixObjetsCréateur;
	}

	public void afficherCarte(Graphics g){

		// affichage du background en premier
		if (this.background != null)
			g.drawImage(this.background.getImage(), 0, 0,Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height,  null);

		// affichage des Tiles
		if (this.mapFile.map == null)
			System.out.println("La map est vide");
		else {
			if (!this.mapFile.map.isEmpty()){
				for (Tile tile : this.mapFile.map){
					if (tile == null)
						System.out.println("La tile est vide");
					else
						if (tile.getNuméro() != 0)
							g.drawImage(tile.getImageIcon().getImage(),tile.getX()*ObjetIcone.tailleImageJeu,tile.getY()*ObjetIcone.tailleImageJeu,null);
				}
			}
		}

		// affichage des bordures
		for(int y = 0; y <= this.mapFile.tileSize.getY() ; y ++){
			for(int x = 0; x <= this.mapFile.tileSize.getX() ; x ++){
				if (y == this.mapFile.tileSize.getY() && x != this.mapFile.tileSize.getX() )
					g.drawImage(new ImageIcon("images\\1.utilitaires\\bordureV.jpg").getImage(), x*ObjetIcone.tailleImageJeu, y*ObjetIcone.tailleImageJeu, null);
				if (x == this.mapFile.tileSize.getX() && y != this.mapFile.tileSize.getY() )
					g.drawImage(new ImageIcon("images\\1.utilitaires\\bordureH.jpg").getImage(), x*ObjetIcone.tailleImageJeu, y*ObjetIcone.tailleImageJeu, null);
			}
		}
	}

	public void gestionClicGauche(int x, int y, ObjetCourant objetCourant){

		// on remplace la Tile
		if (objetCourant.getNuméro() != this.mapFile.tileSize.getNuméro()){
			//this.map.remove(this.getTile(x, y));
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
		this.background = new ImageIcon(imageTailleRéelle);	
		this.mapFile.backgroundNum = backgroundNum;
	}
}
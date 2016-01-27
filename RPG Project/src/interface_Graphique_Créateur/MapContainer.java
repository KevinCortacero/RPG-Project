package interface_Graphique_Cr�ateur;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import jeu.ObjetCourant;

public class MapContainer {

	private static MapContainer instance;
	private ImageIcon background;
	private String cl�MapFileCourante;
	private Map<String, MapFile> listeMapFile; 


	public synchronized static MapContainer getMap(){
		if ( instance == null ) {
			instance =  new MapContainer();
		}
		return instance;
	}
	
	private MapContainer() {
		this.cl�MapFileCourante = "cartes\\test.txt";
		this.listeMapFile = new HashMap<String, MapFile>();
		this.listeMapFile.put(this.cl�MapFileCourante, new MapFile(this,new File(this.cl�MapFileCourante)));
		Origin.getOrigin();
	}
	
	public MapFile getMapFileCourante() {
		return this.listeMapFile.get(this.cl�MapFileCourante);
	}
	
	public java.util.Map<String, MapFile> getListeMapFile() {
		return listeMapFile;
	}


	public void ajouterMapFile(String string,MapFile mapFile){
		this.listeMapFile.put(string, mapFile);
	}

	public void changerMapFileCourante(String cl�){
		this.cl�MapFileCourante = cl�;
		Origin.setX(0);
		Origin.setY(0);
	}

	public void afficherCarte(Graphics g){

		// affichage du background en premier
		if (this.background != null && this.getMapFileCourante().getBackgroundNum() != 0)
			g.drawImage(this.background.getImage(), 0, 0,Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height,  null);

		// affichage des Tiles
		if (!this.listeMapFile.get(this.cl�MapFileCourante).getMap().isEmpty()){
			for (Tile tile : this.listeMapFile.get(this.cl�MapFileCourante).getMap()){
				if (tile.getNum�ro() > 1)
			g.drawImage(tile.getImageIcon().getImage(),(int) Origin.getX() + tile.getX()*ObjetIcone.tailleImageJeu,(int)Origin.getY() + tile.getY()*ObjetIcone.tailleImageJeu,null);

			}
		}

		// affichage des bordures
		for(int y = 0; y <= this.listeMapFile.get(this.cl�MapFileCourante).getTileSize().getY() ; y ++){
			for(int x = 0; x <= this.listeMapFile.get(this.cl�MapFileCourante).getTileSize().getX() ; x ++){
				if (y == this.listeMapFile.get(this.cl�MapFileCourante).getTileSize().getY() && x != this.listeMapFile.get(this.cl�MapFileCourante).getTileSize().getX() )
					g.drawImage(new ImageIcon("imagesSpeciales\\bordureV.jpg").getImage(),(int) Origin.getX() + x*ObjetIcone.tailleImageJeu, (int)Origin.getY() + y*ObjetIcone.tailleImageJeu, null);

				if (x == this.listeMapFile.get(this.cl�MapFileCourante).getTileSize().getX() && y != this.listeMapFile.get(this.cl�MapFileCourante).getTileSize().getY() )
					g.drawImage(new ImageIcon("imagesSpeciales\\bordureH.jpg").getImage(),(int) Origin.getX() + x*ObjetIcone.tailleImageJeu, (int)Origin.getY() + y*ObjetIcone.tailleImageJeu, null);
			}
		}
		
		g.drawImage(this.listeMapFile.get(this.cl�MapFileCourante).getMap().get(0).getImageIcon().getImage(),(int) Origin.getX() + this.listeMapFile.get(this.cl�MapFileCourante).getMap().get(0).getX()*ObjetIcone.tailleImageJeu,(int) Origin.getY() + this.listeMapFile.get(this.cl�MapFileCourante).getMap().get(0).getY()*ObjetIcone.tailleImageJeu,null);
	}

	
	public void gestionClicGauche(int x, int y, ObjetCourant objetCourant){

		// on remplace la Tile
		if (objetCourant.getNum�ro() != this.listeMapFile.get(this.cl�MapFileCourante).getTileSize().getNum�ro() && this.listeMapFile.get(this.cl�MapFileCourante).getTile(x, y).getNum�ro() != this.listeMapFile.get(this.cl�MapFileCourante).getTileSize().getNum�ro()){
			this.listeMapFile.get(this.cl�MapFileCourante).getMap().remove(this.listeMapFile.get(this.cl�MapFileCourante).getTile(x, y));
			this.listeMapFile.get(this.cl�MapFileCourante).getMap().add(new Tile(x, y, objetCourant.getImageIcon(), objetCourant.getNum�ro()));
		}

		// on replace la bordure s'il s'agit de la Tile de gestion de la taille de la map
		if (objetCourant.getNum�ro() == this.listeMapFile.get(this.cl�MapFileCourante).getTileSize().getNum�ro()){
			this.listeMapFile.get(this.cl�MapFileCourante).getTileSize().setX(x);
			this.listeMapFile.get(this.cl�MapFileCourante).getTileSize().setY(y);
		}

		this.listeMapFile.get(this.cl�MapFileCourante).remplirMatrice();
	}

	public void setBackground(Image imageTailleR�elle, int backgroundNum) {
		if (imageTailleR�elle == null)
			this.background = null;
		else
			this.background = new ImageIcon(imageTailleR�elle);	
		this.listeMapFile.get(this.cl�MapFileCourante).setBackgroundNum(backgroundNum);
	}
}
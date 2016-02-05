package application.interface_Graphique_Créateur.PanelPrincipal;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import application.fonction.Origine;
import application.interface_Graphique_Créateur.PanelObjets.ObjetIcone;
import application.interface_Graphique_Créateur.PanelObjets.PanelChoixObjetsCréateur;
import application.jeu.ObjetCourant;

public class Level {

	private File file;
	private int[][] matrice;
	private List<Tile> map;
	private int backgroundNum;
	private Tile tileSize;
	private FileWriter fileWriter;


	public Level(String path){
		this.map = new ArrayList<Tile>();
		this.matrice = new int[1000][1000];
		this.file = new File(path);
		this.charger();
	}

	public File getFile() {
		return file;
	}

	public void setCurrentFile(File currentFile) {
		this.file = currentFile;
	}
	
	public List<Tile> getMap() {
		return map;
	}
	
	public int getBackgroundNum() {
		return backgroundNum;
	}

	public void setBackgroundNum(int backgroundNum) {
		this.backgroundNum = backgroundNum;
	}

	public Tile getTileSize() {
		return tileSize;
	}

	public void sauvegarder() throws IOException{
		// on écrase et on resauvegarde
		this.fileWriter = new FileWriter(this.file);

		// on retranscrie notre matrice
		for(int y = 0; y < this.tileSize.getY() ; y ++){
			for(int x = 0; x < this.tileSize.getX() ; x ++){
				this.fileWriter.write(Integer.toString(this.matrice[x][y]) + "  ");
			}
			// si on est sur la première ligne, on rajoute l'information du background
			if (y == 0)
				this.fileWriter.write(Integer.toString(this.backgroundNum));
			this.fileWriter.write("\r\n");
		}
		this.fileWriter.close();
	}

	public void remplirMatrice(){
		// remplissage de la matrice
		for(int y = 0; y < this.tileSize.getY() ; y ++){
			for(int x = 0; x < this.tileSize.getX(); x ++){
				this.matrice[x][y] = this.getTile(x,y).getNuméro();
			}
		}
	}

	public void charger(){
		// on recrée la matrice avec les chiffres
		try {
			InputStream is = new FileInputStream(this.file);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			try {
				String ligne;
				int y = 0;
				while ((ligne = br.readLine()) != null ){

					int xMatrice = 0;
					int x = 0;

					while (x < ligne.length()-1){
						String numéro = String.valueOf(ligne.charAt(x));
						while (ligne.charAt(x+1) != ' '){
							numéro += String.valueOf(ligne.charAt(x+1));	
							x ++;
						}
						if (Integer.parseInt(numéro) <= PanelChoixObjetsCréateur.getPanel().getListeImageNuméro().size())
							this.matrice[xMatrice][y] = Integer.parseInt(numéro);
						else
							this.matrice[xMatrice][y] = 0;
						xMatrice ++;
						x += 3;
					}
					if (y == 0){
						this.matrice[xMatrice+1][y] = Integer.parseInt(String.valueOf(ligne.charAt(ligne.length()-1)));
					if (this.matrice[xMatrice+1][y] != 0)
					this.setBackgroundNum(this.matrice[xMatrice+1][y]);
					else
						this.setBackgroundNum(0);
					}

					y ++;
					this.tileSize = new Tile(1,1,new ImageIcon("images\\1.utilitaires\\angleMax.jpg"), 1);
					this.tileSize.setX(xMatrice);
				}

				this.tileSize.setY(y);
				this.map.add(this.tileSize);

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for(int y = 0; y < this.tileSize.getY() ; y ++){
			for(int x = 0; x < this.tileSize.getX(); x ++){
				if (this.matrice[x][y] != 0)
					this.map.add(new Tile(x,y,this.getTileMatrice(this.matrice[x][y]), this.matrice[x][y]));
			}
		}
	}

	public Tile getTile(int x, int y){
		Tile resultat = new Tile(0);
		if (this.map != null){
			for (Tile tile : this.map){
				if (tile.getX() == x && tile.getY() == y)
					resultat = tile;
			}
		}
		return resultat;
	}

	public ImageIcon getTileMatrice(int numéro){
		return new ImageIcon(PanelChoixObjetsCréateur.getPanel().getListeImageNuméro().get(numéro).getImage().getScaledInstance(ObjetIcone.tailleImageJeu,ObjetIcone.tailleImageJeu, Image.SCALE_SMOOTH));
	}
	
	public void afficherCarte(Graphics g){

		// affichage du background en premier
		if (this.getBackgroundNum() != 0)
			g.drawImage(this.getTileMatrice(this.backgroundNum).getImage(), 0, 0,Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height,  null);

		// affichage des Tiles
		if (!this.getMap().isEmpty()){
			for (Tile tile : this.getMap()){
				if (tile.getNuméro() > 1)
			g.drawImage(tile.getImageIcon().getImage(),Origine.getX()+tile.getX()*ObjetIcone.tailleImageJeu,Origine.getY()+tile.getY()*ObjetIcone.tailleImageJeu,null);

			}
		}

		// affichage des bordures
		for(int y = 0; y <= this.getTileSize().getY() ; y ++){
			for(int x = 0; x <= this.getTileSize().getX() ; x ++){
				if (y == this.getTileSize().getY() && x != this.getTileSize().getX() )
					g.drawImage(new ImageIcon("imagesSpeciales\\bordureV.jpg").getImage(),Origine.getX()+x*ObjetIcone.tailleImageJeu,Origine.getY()+y*ObjetIcone.tailleImageJeu, null);

				if (x == this.getTileSize().getX() && y != this.getTileSize().getY() )
					g.drawImage(new ImageIcon("imagesSpeciales\\bordureH.jpg").getImage(),Origine.getX()+x*ObjetIcone.tailleImageJeu,Origine.getY()+y*ObjetIcone.tailleImageJeu, null);
			}
		}
		
		g.drawImage(this.getMap().get(0).getImageIcon().getImage(),Origine.getX()+this.getMap().get(0).getX()*ObjetIcone.tailleImageJeu,Origine.getY()+this.getMap().get(0).getY()*ObjetIcone.tailleImageJeu,null);
	}
	
	public void gestionClicGauche(int x, int y, ObjetCourant objetCourant){

		// on remplace la Tile
		if (objetCourant.getNuméro() != this.getTileSize().getNuméro() && this.getTile(x, y).getNuméro() != this.getTileSize().getNuméro()){
			this.getMap().remove(this.getTile(x, y));
			this.getMap().add(new Tile(x, y, objetCourant.getImageIcon(), objetCourant.getNuméro()));
		}

		// on replace la bordure s'il s'agit de la Tile de gestion de la taille de la map
		if (objetCourant.getNuméro() == this.getTileSize().getNuméro()){
			this.getTileSize().setX(x);
			this.getTileSize().setY(y);
		}

		this.remplirMatrice();
	}
}
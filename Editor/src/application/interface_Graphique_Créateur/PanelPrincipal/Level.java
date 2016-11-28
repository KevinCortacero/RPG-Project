package application.interface_Graphique_Créateur.PanelPrincipal;

import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
	private String[][] matrice;
	private List<Tile> map;
	private Image background;
	private Tile tileSize;
	private FileWriter fileWriter;


	public Level(String path) throws Exception{
		this.map = new ArrayList<Tile>();
		this.matrice = new String[1000][1000];
		this.file = new File(path);
		this.load();
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

	public void setBackground(String  background) {
		this.background = new ImageIcon(background).getImage();
	}

	public Tile getTileSize() {
		return tileSize;
	}

	public void sauvegarder() throws IOException{
		// on écrase et on resauvegarde
		this.fileWriter = new FileWriter(this.file);

		// on retranscrie notre matrice
		for(int y = this.tileSize.getY() -1; y >= 0 ; y --){
			for(int x = 0; x < this.tileSize.getX() ; x ++){
				this.fileWriter.write(Integer.toString(this.matrice[x][y]) + ":");
			}
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

	public void load() throws Exception{
		// on recrée la matrice avec les chiffres
		InputStream is = new FileInputStream(this.file);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bfr = new BufferedReader(isr);

		String line = bfr.readLine();
		int width = Integer.parseInt(line.substring(line.lastIndexOf("=") + 2, line.length()));
		line = bfr.readLine();
		int height = Integer.parseInt(line.substring(line.lastIndexOf("=") + 2, line.length()));
		line = bfr.readLine();
		this.setBackground("res/images/" + line.substring(line.lastIndexOf("=") + 2, line.length()));
		line = bfr.readLine();
		float gravity_x = Float.parseFloat(line.substring(line.lastIndexOf("=") + 2, line.length()));
		line = bfr.readLine();
		float gravity_y = Float.parseFloat(line.substring(line.lastIndexOf("=") + 2, line.length()));

		String level_matrix[][] = new String[height][width];

		for (int i = 0; i < height; i++){
			line = bfr.readLine();
			this.matrice[i] = line.split(":");
		}
		bfr.close();


		this.tileSize = new Tile(1, 1, "1");
		this.tileSize.setX(width);
		this.tileSize.setY(height);
		this.map.add(this.tileSize);


		for(int y = 0; y < this.tileSize.getY() ; y ++){
			for(int x = 0; x < this.tileSize.getX(); x ++){
				if (this.matrice[x][y] != "0")
					this.map.add(new Tile(x,y,this.matrice[x][y]));
			}
		}
		LevelDesigner.getPanel().repaint();
	}


	public Tile getTile(int x, int y){
		Tile resultat = new Tile(0, 0, "0");
		if (this.map != null){
			for (Tile tile : this.map){
				if (tile.getX() == x && tile.getY() == y)
					resultat = tile;
			}
		}
		return resultat;
	}

	public ImageIcon getTileMatrice(String id){
		return new ImageIcon(PanelChoixObjetsCréateur.getPanel().getListeImageNuméro().get(id).getImage());
	}

	public void draw(Graphics g){

		// affichage du background en premier
		if (this.background != null)
			g.drawImage(this.background, Origine.getX(), Origine.getY(), null);

		// affichage des Tiles
		if (!this.getMap().isEmpty()){
			for (Tile tile : this.getMap()){
				if (tile.getNuméro() > 1)
					g.drawImage(tile.getImageIcon(),Origine.getX()+tile.getX()*ObjetIcone.tailleImageJeu,Origine.getY()+tile.getY()*ObjetIcone.tailleImageJeu, null);
			}
		}

		Image hBorder = new ImageIcon("imagesSpeciales\\bordureH.jpg").getImage();
		hBorder = new ImageIcon(hBorder.getScaledInstance(ObjetIcone.tailleImageJeu, ObjetIcone.tailleImageJeu,Image.SCALE_DEFAULT)).getImage();
		Image vBorder = new ImageIcon("imagesSpeciales\\bordureV.jpg").getImage();
		vBorder = new ImageIcon(vBorder.getScaledInstance(ObjetIcone.tailleImageJeu, ObjetIcone.tailleImageJeu,Image.SCALE_DEFAULT)).getImage();
		// affichage des bordures
		for(int y = 0; y <= this.getTileSize().getY() ; y ++){
			for(int x = 0; x <= this.getTileSize().getX() ; x ++){
				if (y == this.getTileSize().getY() && x != this.getTileSize().getX() )
					g.drawImage(vBorder,Origine.getX()+x*ObjetIcone.tailleImageJeu,Origine.getY()+y*ObjetIcone.tailleImageJeu, null);

				if (x == this.getTileSize().getX() && y != this.getTileSize().getY() )
					g.drawImage(hBorder,Origine.getX()+x*ObjetIcone.tailleImageJeu,Origine.getY()+y*ObjetIcone.tailleImageJeu, null);
			}
		}

		g.drawImage(new ImageIcon(this.getMap().get(0).getImageIcon().getScaledInstance(ObjetIcone.tailleImageJeu, ObjetIcone.tailleImageJeu, Image.SCALE_SMOOTH)).getImage(),Origine.getX()+this.getMap().get(0).getX()*ObjetIcone.tailleImageJeu,Origine.getY()+this.getMap().get(0).getY()*ObjetIcone.tailleImageJeu,null);
	}

	public void gestionClicGauche(int x, int y, ObjetCourant objetCourant){

		// on remplace la Tile
		if (objetCourant.getNuméro() != this.getTileSize().getNuméro() && this.getTile(x, y).getNuméro() != this.getTileSize().getNuméro()){
			this.getMap().remove(this.getTile(x, y));
			this.getMap().add(new Tile(x, y, objetCourant.getImage(), objetCourant.getNuméro()));
		}

		// on replace la bordure s'il s'agit de la Tile de gestion de la taille de la map
		if (objetCourant.getNuméro() == this.getTileSize().getNuméro()){
			this.getTileSize().setX(x);
			this.getTileSize().setY(y);
		}

		this.remplirMatrice();
	}
}
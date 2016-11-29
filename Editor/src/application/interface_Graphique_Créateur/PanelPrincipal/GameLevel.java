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

public class GameLevel {

	private File file;
	private Image background;
	private FileWriter fileWriter;
	private List<GameObject> gameObjects;
	private int width;
	private int height;
	private float gravity_x;
	private float gravity_y;


	public GameLevel(String path) throws Exception{
		this.gameObjects = new ArrayList<GameObject>();
		this.file = new File(path);
		this.load();
	}

	public File getFile() {
		return file;
	}

	public void setCurrentFile(File currentFile) {
		this.file = currentFile;
	}

	public void setBackground(String  background) {
		this.background = new ImageIcon(background).getImage();
	}

	public void sauvegarder() throws IOException{
		// on écrase et on resauvegarde
		this.fileWriter = new FileWriter(this.file);

		// on retranscrie notre matrice
		for(GameObject go : this.gameObjects){
			this.fileWriter.write(go.encode());
		}
		this.fileWriter.close();
	}

	public void load() throws Exception{
		// on recrée la matrice avec les chiffres
		InputStream is = new FileInputStream(this.file);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader bfr = new BufferedReader(isr);

		String line = bfr.readLine();
		this.width = Integer.parseInt(line.substring(line.lastIndexOf("=") + 2, line.length()));
		line = bfr.readLine();
		this.height = Integer.parseInt(line.substring(line.lastIndexOf("=") + 2, line.length()));
		line = bfr.readLine();
		this.setBackground("res/images/" + line.substring(line.lastIndexOf("=") + 2, line.length()));
		line = bfr.readLine();
		this.gravity_x = Float.parseFloat(line.substring(line.lastIndexOf("=") + 2, line.length()));
		line = bfr.readLine();
		this.gravity_y = Float.parseFloat(line.substring(line.lastIndexOf("=") + 2, line.length()));

		// READ GAME OBJECTS

		bfr.close();



		LevelDesigner.getPanel().repaint();
	}

	public ImageIcon getTileMatrice(String id){
		return new ImageIcon(PanelChoixObjetsCréateur.getPanel().getListeImageNuméro().get(id).getImage());
	}

	public void draw(Graphics g){

		// affichage du background en premier
		if (this.background != null)
			g.drawImage(this.background, Origine.getX(), Origine.getY(), null);

		Image hBorder = new ImageIcon("imagesSpeciales\\bordureH.jpg").getImage();
		hBorder = new ImageIcon(hBorder.getScaledInstance(ObjetIcone.tailleImageJeu, ObjetIcone.tailleImageJeu,Image.SCALE_DEFAULT)).getImage();
		Image vBorder = new ImageIcon("imagesSpeciales\\bordureV.jpg").getImage();
		vBorder = new ImageIcon(vBorder.getScaledInstance(ObjetIcone.tailleImageJeu, ObjetIcone.tailleImageJeu,Image.SCALE_DEFAULT)).getImage();
		// affichage des bordures
		for(int y = 0; y < this.height ; y ++){
			g.drawImage(hBorder,Origine.getX()+this.width*ObjetIcone.tailleImageJeu,Origine.getY()+y*ObjetIcone.tailleImageJeu, null);
		}
		for(int x = 0; x <= this.width ; x ++){
			g.drawImage(vBorder,Origine.getX()+x*ObjetIcone.tailleImageJeu,Origine.getY()+this.height*ObjetIcone.tailleImageJeu, null);
		}
		
		//g.drawImage(new ImageIcon(this.getMap().get(0).getImageIcon().getScaledInstance(ObjetIcone.tailleImageJeu, ObjetIcone.tailleImageJeu, Image.SCALE_SMOOTH)).getImage(),Origine.getX()+this.getMap().get(0).getX()*ObjetIcone.tailleImageJeu,Origine.getY()+this.getMap().get(0).getY()*ObjetIcone.tailleImageJeu,null);
	}

	public void gestionClicGauche(int x, int y, ObjetCourant objetCourant){
		/*
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
		*/
	}
}
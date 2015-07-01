package interface_Graphique_Créateur;

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

import jeu.ObjetCourant;


// Cette class doit être refactorisée en créant la class MapFile 
public class Map {

	private Tile tileSize;
	private int backgroundNum;
	private List<Tile> map;
	private PanelChoixObjetsCréateur panelChoixObjetsCréateur;
	private int[][] mapFile;
	private ImageIcon background;
	private FileWriter fileWriter;
	protected File currentFile;

	public Map() throws IOException{
		this.map = new ArrayList<Tile>();
		this.tileSize = new Tile(1,1,new ImageIcon("images\\1.utilitaires\\angleMax.jpg"), 1);
		this.map.add(this.tileSize);
		this.mapFile = new int[1000][1000];
		this.setCurrentFile(new File("cartes/test.txt"));
		this.chargerCarteActuelle();
	}
	
	public void setCurrentFile(File currentFile) {
		this.currentFile = currentFile;
	}
	
	public void setPanel(PanelChoixObjetsCréateur panelChoixObjetsCréateur){
		this.panelChoixObjetsCréateur = panelChoixObjetsCréateur;
	}
	
	public void getTileMatrice(int numéro){
		
		//System.out.println(this.panelChoixObjetsCréateur.onglets.get);
		
	}
	public void chargerCarteActuelle(){
		// on recrée la matrice avec les chiffres
		try {
			InputStream is = new FileInputStream(this.currentFile);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			try {
				String ligne;
				int y = 0;
				while ((ligne = br.readLine()) != null ){
					for (int x = 0; x <= ligne.length() -1; x+=3){
						this.mapFile[x/3][y] = Character.getNumericValue(ligne.charAt(x)) ;
					}
					y ++;
					this.tileSize.setX((ligne.length()+1)/3 );
				}
				
				this.tileSize.setY(y);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void afficherCarte(Graphics g){
		
		// affichage du background en premier
		if (this.background != null)
			g.drawImage(this.background.getImage(), 0, 0,Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height,  null);
		
		// affichage des Tiles
		for (Tile tile : this.map){
			g.drawImage(tile.getImageIcon().getImage(),tile.getX()*ObjetIcone.tailleImageJeu,tile.getY()*ObjetIcone.tailleImageJeu,null);
		}

		// remplissage de la matrice
		for(int y = 0; y < this.tileSize.getY() ; y ++){
			for(int x = 0; x < this.tileSize.getX(); x ++){
				this.mapFile[x][y] = this.getTile(x,y).getNuméro();
			}
		}
		
//		// Test de la matrice
//		System.out.print("\n" +"***********************" + "\n");
//		for(int y = 0; y < this.tileSize.getY() ; y ++){
//			for(int x = 0; x < this.tileSize.getX(); x ++){
//				System.out.print(" " + this.mapFile[x][y] + " ");
//			}
//			System.out.print("\n");
//		}

		// affichage des bordures
		for(int y = 0; y <= this.tileSize.getY() ; y ++){
			for(int x = 0; x <= this.tileSize.getX() ; x ++){
				if (y == this.tileSize.getY() && x != this.tileSize.getX() )
					g.drawImage(new ImageIcon("images\\1.utilitaires\\bordureV.jpg").getImage(), x*ObjetIcone.tailleImageJeu, y*ObjetIcone.tailleImageJeu, null);
				if (x == this.tileSize.getX() && y != this.tileSize.getY() )
					g.drawImage(new ImageIcon("images\\1.utilitaires\\bordureH.jpg").getImage(), x*ObjetIcone.tailleImageJeu, y*ObjetIcone.tailleImageJeu, null);
			}
		}
		this.getTileMatrice(0);
	}
	
	public void gestionClicGauche(int x, int y, ObjetCourant objetCourant){
		
		// on remplace la Tile
		if (objetCourant.getNuméro() != this.tileSize.getNuméro()){
			this.map.remove(this.getTile(x, y));
			this.map.add(new Tile(x, y, objetCourant.getImageIcon(), objetCourant.getNuméro()));
		}

		// on replace la bordure s'il s'agit de la Tile de gestion de la taille de la map
		if (objetCourant.getNuméro() == this.tileSize.getNuméro()){
			this.tileSize.setX(x);
			this.tileSize.setY(y);
		}
	}
	
	public Tile getTile(int x, int y){
		Tile resultat = new Tile(0);
		for (Tile tile : this.map){
			if (tile.getX() == x && tile.getY() == y)
				resultat = tile;
		}
		return resultat;
	}
	
	public void sauvegarder() throws IOException{
		// on écrase et on resauvegarde
		this.fileWriter = new FileWriter(this.currentFile);

		// on retranscrie notre matrice
		for(int y = 0; y < this.tileSize.getY() ; y ++){
			for(int x = 0; x < this.tileSize.getX() ; x ++){
				this.fileWriter.write(Integer.toString(this.mapFile[x][y]) + "  ");
			}
			// si on est sur la première ligne, on rajoute l'information du background
			if (y == 0)
				this.fileWriter.write(Integer.toString(this.backgroundNum));
			this.fileWriter.write("\r\n");
		}
		this.fileWriter.close();
	}
	
	public void setBackground(Image imageTailleRéelle, int backgroundNum) {
		this.background = new ImageIcon(imageTailleRéelle);	
		this.backgroundNum = backgroundNum;
	}
}
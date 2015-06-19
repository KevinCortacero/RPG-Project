package interface_Graphique_Créateur;

import javax.swing.ImageIcon;

public class Tile {

	private int x;
	private int y;
	private ImageIcon image;
	private int numéro;
	
	public Tile(int x, int y, ImageIcon image, int numéro){
		this.x = x;
		this.y = y;
		this.image = image;
		this.numéro = numéro;
	}
	
	public ImageIcon getImageIcon(){
		return this.image;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
}

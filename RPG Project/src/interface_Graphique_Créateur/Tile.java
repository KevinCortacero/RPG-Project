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
	
	public Tile(int numéro){
		this(0,0,null,numéro);
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
	
	public int getNuméro(){
		return this.numéro;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}

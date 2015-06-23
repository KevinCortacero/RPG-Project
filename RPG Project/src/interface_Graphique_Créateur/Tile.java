package interface_Graphique_Cr�ateur;

import javax.swing.ImageIcon;

public class Tile {

	private int x;
	private int y;
	private ImageIcon image;
	private int num�ro;
	
	public Tile(int x, int y, ImageIcon image, int num�ro){
		this.x = x;
		this.y = y;
		this.image = image;
		this.num�ro = num�ro;
	}
	
	public Tile(int num�ro){
		this(0,0,null,num�ro);
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
	
	public int getNum�ro(){
		return this.num�ro;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}

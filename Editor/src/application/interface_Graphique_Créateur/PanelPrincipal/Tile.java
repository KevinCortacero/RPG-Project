package application.interface_Graphique_Créateur.PanelPrincipal;

import java.awt.Image;

public class Tile {

	private int x;
	private int y;
	private Image image;
	private String id;
	
	public Tile(int x, int y, String id){
		this.x = x;
		this.y = y;
		this.image = ;
		this.id = id;
	}
	
	public Image getImageIcon(){
		return this.image;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public String getNuméro(){
		return this.id;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}
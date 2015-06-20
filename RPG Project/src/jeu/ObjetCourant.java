package jeu;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ObjetCourant {

	private ImageIcon image;
	private int num�ro;
	
	public ObjetCourant(Image image, int num�ro){
		this.image = new ImageIcon(image);
		this.num�ro = num�ro;	
	}
	
	public ImageIcon getImageIcon(){
		return this.image;
	}
	
	public int getNum�ro(){
		return this.num�ro;
	}
}

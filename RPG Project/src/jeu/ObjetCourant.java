package jeu;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ObjetCourant {

	private ImageIcon image;
	private int numéro;
	
	public ObjetCourant(Image image, int numéro){
		this.image = new ImageIcon(image);
		this.numéro = numéro;	
	}
	
	public ImageIcon getImageIcon(){
		return this.image;
	}
	
	public int getNuméro(){
		return this.numéro;
	}
}

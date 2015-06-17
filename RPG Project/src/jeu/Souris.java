package jeu;

import javax.swing.ImageIcon;

public class Souris {
	
	private ImageIcon image;
	
	public Souris(){

		this.image = new ImageIcon("images/souris.jpg");


	}
	
	public ImageIcon getImageIcon(){
		return this.image;
	}
	
}


package jeu;

import javax.swing.ImageIcon;

public class Souris {
	
	private ImageIcon image;
	
	public Souris(){
		this.image = new ImageIcon("C:/Users/Admin/git/RPG-Project/RPG Project/src/images/souris.jpg");
	}
	
	public ImageIcon getImageIcon(){
		return this.image;
	}
	
}

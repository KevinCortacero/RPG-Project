package jeu;

import javax.swing.ImageIcon;

public class Souris {
	
	private ImageIcon image;
	public static ImageIcon imageIcon = new ImageIcon("images/souris.jpg");
	
	public Souris(){
		this.image = new ImageIcon("images/souris.jpg");
	}
	
	public ImageIcon getImageIcon(){
		return this.image;
	}
	
}


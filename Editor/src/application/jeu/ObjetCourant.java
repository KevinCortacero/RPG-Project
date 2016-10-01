package application.jeu;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ObjetCourant {

	private ImageIcon image;
	private int num�ro;
	
	public ObjetCourant(Image image, int num�ro){
		this.image = new ImageIcon(image);
		this.num�ro = num�ro;	
	}
	
	public int getNum�ro(){
		return this.num�ro;
	}

	public Image getImage() {
		return this.image.getImage();
	}
}

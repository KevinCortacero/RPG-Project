package personnage;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Sprite {
	public Coordonnée2D coordonnée2D;
	public Rectangle hitbox;
	public Image image;
	
	public Sprite (int x, int y, int hauteur, int largeur){
		this.coordonnée2D = new Coordonnée2D(x,y);
		this.hitbox = new Rectangle(x,y,hauteur,largeur);
	}

	public void setSprite(String string) {
		this.image = new ImageIcon(string).getImage();
	}
}

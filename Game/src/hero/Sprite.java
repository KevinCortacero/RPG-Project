package hero;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Sprite {
	public Coord2D coordonnée2D;
	public Rectangle hitbox;
	public Image image;
	
	public Sprite (Coord2D coord, int hauteur, int largeur){
		this.coordonnée2D = coord;
		this.hitbox = new Rectangle(coord.getX(), coord.getY(),hauteur,largeur);
	}

	public void setSprite(String string) {
		this.image = new ImageIcon(string).getImage();
	}
}

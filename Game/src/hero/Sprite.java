package hero;

import java.awt.Image;
import java.awt.Rectangle;

public class Sprite{
	public Coord2D coordonn�e2D;
	public Rectangle hitbox;
	public Image image;
	private Thread animation;
	private Animation anim;
	public String path;

	public Sprite (Coord2D coord, int hauteur, int largeur){
		this.coordonn�e2D = coord;
		this.hitbox = new Rectangle(coord.getX(), coord.getY(),hauteur,largeur);
		this.anim = new Animation(this, largeur, hauteur);
	}

	public void animate(int width, int height) {
		
		this.animation = new Thread(this.anim);
		this.animation.start();
	}

	public Image getImage(){
		return this.anim.getCurrentTile();
	}
	
	public void setPath(String path){
		this.anim.setPath(path);
	}
}

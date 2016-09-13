package hero;

import java.awt.Image;
import java.awt.Rectangle;

public class Sprite{
	public Coord2D coordonnée2D;
	public Rectangle hitbox;
	public Image image;
	private Thread animation;
	private Animation anim;
	private String path;

	public Sprite (Coord2D coord, int hauteur, int largeur){
		this.coordonnée2D = coord;
		this.hitbox = new Rectangle(coord.getX(), coord.getY(),hauteur,largeur);
	}

	private void animate(String spritePath, int width, int height) {
		this.anim = new Animation(spritePath, height, height);
		this.animation = new Thread(this.anim);
		this.animation.start();
	}

	public void changeAnimation(String spritePath, int width, int height) {
		if (this.path != null){
			if (!this.path.equals(spritePath)){
				if (this.animation != null)
					this.animation = null;
				this.animate(spritePath, width, height);
			}
		}
		else{
			this.animate(spritePath, width, height);
		}
		this.path = spritePath;
	}


	public Image getImage(){
		return this.anim.getCurrentTile();
	}
}

package hero;

import java.awt.Color;
import java.awt.Graphics;

public class Obstacle extends GameObject {

	public Obstacle(Coord2D coord, int hauteur, int largeur) {
		super(coord, hauteur, largeur);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(255,0,0));
		g.fillRect(this.getX(), this.getY(), this.sprite.hitbox.width, this.sprite.hitbox.height);
		g.fillRect(1100, this.getY(), this.sprite.hitbox.width, this.sprite.hitbox.height);
	}
	
}

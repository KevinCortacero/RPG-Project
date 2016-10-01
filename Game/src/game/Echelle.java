package game;

import java.awt.Color;
import java.awt.Graphics;

import hero.Coord2D;
import hero.GameObject;


public class Echelle extends GameObject{

	public Echelle(Coord2D coord, int hauteur, int largeur) {
		super(coord, hauteur, largeur);
	}

	@Override
	public void draw(Graphics g){
		g.setColor(new Color(0,255,0));
		g.fillRect(1050, this.getY(), this.sprite.hitbox.width, this.sprite.hitbox.height);
	}
}

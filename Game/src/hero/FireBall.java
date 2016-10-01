package hero;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class FireBall extends GameObject{

	private float vecteurX;

	private FireBall(Coord2D coord, int hauteur, int largeur, float vecteur, Direction d, Element e) {
		super(coord, hauteur, largeur);
		this.vecteurY = -5.0F;
		if (e == Element.BLIZZ){
			if (d == Direction.DROITE){
				this.vecteurX = 5.0F;
				this.sprite.image = new ImageIcon("images/aquaball.png").getImage();
			}
			else if (d == Direction.GAUCHE){
				this.vecteurX = -5.0F;
				this.sprite.image = new ImageIcon("images/aquaball2.png").getImage();
			}
		}
		else if (e == Element.IGNIS){
			if (d == Direction.DROITE){
				this.vecteurX = 5.0F;
				this.sprite.image = new ImageIcon("images/fireball.png").getImage();
			}
			else if (d == Direction.GAUCHE){
				this.vecteurX = -5.0F;
				this.sprite.image = new ImageIcon("images/fireball2.png").getImage();
			}
		}
	}

	public FireBall(float vecteurY, Coord2D coord, Direction d,Element e) {
		this(new Coord2D(coord.getX(), coord.getY()),50,50,vecteurY, d, e);
	}

	public void update() {
		this.sprite.coordonnée2D.setX( this.getX() + (int)this.vecteurX );
		this.sprite.coordonnée2D.setY( this.getY() + (int)this.vecteurY );
		this.vecteurY += 0.15F;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(this.sprite.image, this.getX(), this.getY(), null);
	}
}

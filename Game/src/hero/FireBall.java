package hero;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class FireBall extends GameObject{

	private FireBall(Coord2D coord, int hauteur, int largeur, float vecteur, Direction d, Element e) {
		super(coord, hauteur, largeur);
		this.applyForceY(-5.0F);
		if (e == Element.BLIZZ){
			if (d == Direction.DROITE){
				this.applyForceX(+5.0F);
				this.sprite.image = new ImageIcon("images/aquaball.png").getImage();
			}
			else if (d == Direction.GAUCHE){
				this.applyForceX(-5.0F);
				this.sprite.image = new ImageIcon("images/aquaball2.png").getImage();
			}
		}
		else if (e == Element.IGNIS){
			if (d == Direction.DROITE){
				this.applyForceX(+5.0F);
				this.sprite.image = new ImageIcon("images/fireball.png").getImage();
			}
			else if (d == Direction.GAUCHE){
				this.applyForceX(-5.0F);
				this.sprite.image = new ImageIcon("images/fireball2.png").getImage();
			}
		}
	}

	public FireBall(float vecteurY, Coord2D coord, Direction d,Element e) {
		this(new Coord2D(coord.getX(), coord.getY()),50,50,vecteurY, d, e);
	}

	public void update() {
		this.sprite.coordonn�e2D.setX( this.getX() + (int)this.getVelocityX());
		this.sprite.coordonn�e2D.setY( this.getY() + (int)this.getVelocityY());
		this.applyGravity();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(this.sprite.image, this.getX(), this.getY(), null);
	}
}

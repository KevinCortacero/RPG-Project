package hero;

import javax.swing.ImageIcon;

public class FireBall extends GameObject{

	private float vecteurX;

	private FireBall(int x, int y, int hauteur, int largeur, float vecteur, Direction d, Element e) {
		super(x, y, hauteur, largeur);
		this.vecteurY = -5.0F;
		this.sprite = new Sprite(x,y,hauteur,largeur);
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

	public FireBall(float vecteurY, Coord2D coordonn�e2d, Direction d,Element e) {
		this(coordonn�e2d.getX().getComposante(), coordonn�e2d.getY().getComposante(),50,50,vecteurY, d, e);
	}

	public void update() {
		this.sprite.coordonn�e2D.setX((int) (this.sprite.coordonn�e2D.getX().getComposante()+this.vecteurX));
		this.sprite.coordonn�e2D.setY((int) (this.sprite.coordonn�e2D.getY().getComposante()+this.vecteurY));
		this.vecteurY += 0.15F;
	}
}

package hero;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class FireBall extends GameObject{

	public FireBall(float x, float y, Direction d) {
		super(x, y, 50, 50);
		if (d == Direction.DROITE){
			this.applyForce(+8.0F, -10.0F);
			this.sprite.image = new ImageIcon("images/fireball.png").getImage();
		}
		else if (d == Direction.GAUCHE){
			this.applyForce(-8.0F, -10.0F);
			this.sprite.image = new ImageIcon("images/fireball2.png").getImage();
		}
	}

	public void update() {
		this.addX(this.getVelocityX());
		this.addY(this.getVelocityY());
		this.applyGravity();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(this.sprite.image, (int) this.getX(), (int) this.getY(), null);
	}
}

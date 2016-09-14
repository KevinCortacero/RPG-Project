package hero;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	public Coord2D coordonnée2D;
	public Rectangle hitbox;
	public Image image;
	public String imagePath;

	public Sprite (Coord2D coord, int hauteur, int largeur){
		this.coordonnée2D = coord;
		this.hitbox = new Rectangle(coord.getX(), coord.getY(),hauteur,largeur);
	}

	public void animate() {
		Thread t = new Thread(){
			public void run() {
				while(true){
					try {
						for (int i = 0; i < 6; i ++){
							try {
								Thread.sleep(120);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Sprite.this.image = ImageIO.read(new File(Sprite.this.imagePath)).getSubimage(i*Sprite.this.hitbox.width, 0, Sprite.this.hitbox.width, Sprite.this.hitbox.height);
						}
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
				}
			}
		};
		t.start();
	}

	public void setSprite(String string) {
		this.imagePath = string;
	}
}

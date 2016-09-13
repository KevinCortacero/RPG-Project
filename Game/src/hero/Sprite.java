package hero;

import java.awt.Image;
import java.awt.Rectangle;
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
					for (int i = 0; i <=5; i ++){
						try {
							Thread.sleep(100);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							Sprite.this.image = ImageIO.read(new File(Sprite.this.imagePath)).getSubimage(i*50, 0, 50, 50);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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

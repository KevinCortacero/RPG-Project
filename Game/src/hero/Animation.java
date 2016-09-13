package hero;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Animation implements Runnable {

	private BufferedImage sprite;
	public BufferedImage tile;
	private int imageCount;
	private int width;
	private int height;

	public Animation(String spritePath, int width, int height){
		try {
			this.sprite = ImageIO.read(new File(spritePath));
			this.height = height;
			this.width = width;
			this.tile = this.sprite.getSubimage(0, 0, 50, 50);
			this.imageCount = this.sprite.getWidth() / this.width;
		} catch (IOException e) {
			System.out.println("ERR");
		}
		
	}
	
	public BufferedImage getCurrentTile(){
		return this.tile;
	}

	@Override
	public void run() {
		while(true){
			for (int i = 0; i < this.imageCount; i ++){
				try {
					this.tile = this.sprite.getSubimage(i*this.width, 0, this.width, this.height);
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

}

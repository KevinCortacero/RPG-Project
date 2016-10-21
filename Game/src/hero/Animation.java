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
	private String path;

	public Animation(Sprite sprite, int width, int height){
		//this.path = sprite.path;
		//this.setPath(this.path);
		this.height = height;
		this.width = width;
		//this.tile = this.sprite.getSubimage(0, 0, 50, 50);
		//this.imageCount = this.sprite.getWidth() / this.width;
	}

	public BufferedImage getCurrentTile(){
		return this.tile;
	}

	@Override
	public void run() {
		float count = 0;
		while(true){
			this.tile = this.sprite.getSubimage((int)count*this.width, 0, this.width, this.height);
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (count < this.sprite.getWidth() / this.width -1)
				count += 0.5;
			else
				count = 0;
		}
	}

	public void setPath(String path) {
		try {
			this.sprite = ImageIO.read(new File(path));
		} catch (IOException e) {
			//System.out.println("ERROR : image not loaded : " + path);
		}
	}

}

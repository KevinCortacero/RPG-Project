package image_generator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class SpriteGenerator {
	
	private static final int PINK_TRANSPARENT = new Color(255, 0, 255).getRGB();
	private static final int ALPHA_TRANSPARENT = new Color(0.0F, 0.0F, 0.0F, 0.0F).getRGB();

	private static void generateAlphaSprite(String imageName){
		Image img = new ImageIcon("../Game/images/" + imageName).getImage();
		int w = img.getWidth(null);
		int h = img.getHeight(null);
		BufferedImage bim = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gBim = bim.createGraphics();
		gBim.drawImage(img, 0, 0, null);
		for (int i = 0; i < w; i++)
			for (int j = 0; j < h; j++)
				if (bim.getRGB(i, j) == PINK_TRANSPARENT)
					bim.setRGB(i, j, ALPHA_TRANSPARENT);
		File nomfichier = new File("../Game/images/" + imageName);
		try {
			ImageIO.write(bim, "PNG", nomfichier);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

	public static void main(String[] args) {
		for (String name : new File("../Game/images/").list()){
			if (name.endsWith(".png")){
				SpriteGenerator.generateAlphaSprite(name);
			}
			
		}
	}

}


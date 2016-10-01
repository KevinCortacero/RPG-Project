package image_generator;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageGenerator {

	private final static int IMAGE_SIZE = 10;
	private final static Map<String, Color> ELEMENTS = new HashMap<String, Color>();
	private final static String BLOCK_TYPES[] = {"full", "down", "up", "right", "left", "filled"};

	private static void createImage(String nomImage, Color color){
		BufferedImage bim = new BufferedImage(ImageGenerator.IMAGE_SIZE, ImageGenerator.IMAGE_SIZE, BufferedImage.TYPE_INT_ARGB);
		
		if (nomImage.contains("full")){
			for (int i = 0; i < ImageGenerator.IMAGE_SIZE; i++)
				for (int j = 0; j < ImageGenerator.IMAGE_SIZE; j++)
					if (i ==0 || j == 0 || i == ImageGenerator.IMAGE_SIZE -1 || j == ImageGenerator.IMAGE_SIZE -1)
						bim.setRGB(i, j, color.getRGB());
		}
		else if (nomImage.contains("down")){
			for (int i = 0; i < ImageGenerator.IMAGE_SIZE; i++)
				for (int j = 0; j < ImageGenerator.IMAGE_SIZE; j++)
					if (i ==0 || j == 0 || i == ImageGenerator.IMAGE_SIZE -1 || (j == ImageGenerator.IMAGE_SIZE -1 && i > 2 && i < ImageGenerator.IMAGE_SIZE -3))
						bim.setRGB(i, j, color.getRGB());
		}
		else if (nomImage.contains("up")){
			for (int i = 0; i < ImageGenerator.IMAGE_SIZE; i++)
				for (int j = 0; j < ImageGenerator.IMAGE_SIZE; j++)
					if (i ==0 || (j == 0 && i > 2 && i < ImageGenerator.IMAGE_SIZE -3) || i == ImageGenerator.IMAGE_SIZE -1 || j == ImageGenerator.IMAGE_SIZE -1)
						bim.setRGB(i, j, color.getRGB());
		}
		else if (nomImage.contains("left")){
			for (int i = 0; i < ImageGenerator.IMAGE_SIZE; i++)
				for (int j = 0; j < ImageGenerator.IMAGE_SIZE; j++)
					if ((i ==0 && j > 2 && j < ImageGenerator.IMAGE_SIZE -3) || j == 0 || i == ImageGenerator.IMAGE_SIZE -1 || j == ImageGenerator.IMAGE_SIZE -1)
						bim.setRGB(i, j, color.getRGB());
		}
		else if (nomImage.contains("right")){
			for (int i = 0; i < ImageGenerator.IMAGE_SIZE; i++)
				for (int j = 0; j < ImageGenerator.IMAGE_SIZE; j++)
					if (i ==0 || j == 0 || (i == ImageGenerator.IMAGE_SIZE -1 && j > 2 && j < ImageGenerator.IMAGE_SIZE -3) || j == ImageGenerator.IMAGE_SIZE -1)
						bim.setRGB(i, j, color.getRGB());
		}
		else if (nomImage.contains("filled")){
			for (int i = 0; i < ImageGenerator.IMAGE_SIZE; i++)
				for (int j = 0; j < ImageGenerator.IMAGE_SIZE; j++)
					if (i == 0 || i == ImageGenerator.IMAGE_SIZE /2 || i == ImageGenerator.IMAGE_SIZE /2 -1 || j == 0 || i == ImageGenerator.IMAGE_SIZE -1 || j == ImageGenerator.IMAGE_SIZE -1 || j == ImageGenerator.IMAGE_SIZE /2 || j == ImageGenerator.IMAGE_SIZE/2 -1)
						bim.setRGB(i, j, color.getRGB());
		}
		
		File nomfichier = new File("images\\3.blocks\\" + nomImage + ".png");
		try {
			ImageIO.write(bim, "PNG", nomfichier);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

	private static void initialize(){
		ImageGenerator.ELEMENTS.put("basic", new Color(100,100,100));
		ImageGenerator.ELEMENTS.put("water", new Color(0,160,230));
		ImageGenerator.ELEMENTS.put("fire", new Color(240,30,30));
		ImageGenerator.ELEMENTS.put("air", new Color(160,70,160));
		ImageGenerator.ELEMENTS.put("earth", new Color(185,120,90));
	}

	public static void main(String[] args) {
		ImageGenerator.initialize();
		for (String name : ImageGenerator.ELEMENTS.keySet()){
			for (String blockType : ImageGenerator.BLOCK_TYPES)
				ImageGenerator.createImage(name + "_" + blockType , ImageGenerator.ELEMENTS.get(name));
		}
	}

}

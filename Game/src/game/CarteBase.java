package game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class CarteBase extends Carte {
	
	private static final int TAILLE_MAX_X = 5;
	private static final int TAILLE_MAX_Y = 5;
	
	private Image background;
	
	public CarteBase(){
		super("base",CarteBase.TAILLE_MAX_X, CarteBase.TAILLE_MAX_Y, 100, 400);
		this.background = new ImageIcon("images/bg.png").getImage();
	}
	
	public int getTailleMaxX(){
		return CarteBase.TAILLE_MAX_X;
	}
	
	public int getTailleMaxY(){
		return CarteBase.TAILLE_MAX_Y;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(this.background ,-1500 , -460, null);
	}
}

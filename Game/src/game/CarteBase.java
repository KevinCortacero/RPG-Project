package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;


public class CarteBase extends Carte {

	private static final int TAILLE_MAX_X = 1000;
	private static final int TAILLE_MAX_Y = 1000;

	private Image background;

	public CarteBase(){
		super("base",CarteBase.TAILLE_MAX_X, CarteBase.TAILLE_MAX_Y, 100, 450);
		this.background = new ImageIcon("images/bg1.png").getImage();
		this.load("maps/map1.txt");
	}

	public int getTailleMaxX(){
		return CarteBase.TAILLE_MAX_X;
	}

	public int getTailleMaxY(){
		return CarteBase.TAILLE_MAX_Y;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(this.background ,0 , 0, null);
		/*
		g.setColor(new Color(0,160,230));
		for (int i = 0; i < CarteBase.TAILLE_MAX_X; i ++){
			for (int j = 0; j < CarteBase.TAILLE_MAX_Y; j ++){
				int value = this.getMapValue(i, j);
				if (value > 0)
					g.fillRect(i * 10, j *10, 10, 10);
			}
		}
		*/
	}

	@Override
	public void load(String mapFile) {
		try {
			InputStream is = new FileInputStream(mapFile);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			try {
				String ligne;
				int y = 0;
				while ((ligne = br.readLine()) != null ){

					int xMatrice = 0;
					int x = 0;

					while (x < ligne.length()-1){
						String numéro = String.valueOf(ligne.charAt(x));
						while (ligne.charAt(x+1) != ' '){
							numéro += String.valueOf(ligne.charAt(x+1));	
							x ++;
						}
						if (Integer.parseInt(numéro) <= 30)
							this.setCoordonnées(xMatrice, y,Integer.parseInt(numéro));
						else
							this.setCoordonnées(xMatrice, y,0);
						xMatrice ++;
						x += 3;
					}
					if (y == 0){
						this.setCoordonnées(xMatrice + 1, y,Integer.parseInt(String.valueOf(ligne.charAt(ligne.length()-1))));
						/* apply background
						if (this.matrice[xMatrice+1][y] != 0)
							this.setBackgroundNum(this.matrice[xMatrice+1][y]);
						else
							this.setBackgroundNum(0);
						 */
					}

					y ++;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}

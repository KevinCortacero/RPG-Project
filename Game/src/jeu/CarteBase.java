package jeu;

import personnage.Coordonnée;

public class CarteBase extends Carte {
	
	private static final int TAILLE_MAX_X = 5;
	private static final int TAILLE_MAX_Y = 5;
	
	public CarteBase(){
		super("base",CarteBase.TAILLE_MAX_X, CarteBase.TAILLE_MAX_Y, 100, 400);
		
		for (int i=0; i <= TAILLE_MAX_X-1; i++){
			for (int j=0; j <= TAILLE_MAX_Y-1; j++){
				if (i == j)
					this.setCoordonnées(i, j, new Coordonnée(1));
				else
					this.setCoordonnées(i, j, new Coordonnée(0));
			}
		}
	}
	
	public int getTailleMaxX(){
		return CarteBase.TAILLE_MAX_X;
	}
	
	public int getTailleMaxY(){
		return CarteBase.TAILLE_MAX_Y;
	}
}

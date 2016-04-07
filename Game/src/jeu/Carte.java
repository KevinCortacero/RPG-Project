package jeu;

import personnage.Coordonnée;
import personnage.Coordonnée2D;

public abstract class Carte {
	
	public static final Carte CARTE_BASE = new CarteBase();
	public static final Carte CARTE_FEU = new CarteFeu();
	private String nom;
	private Coordonnée[][] map;
	private Coordonnée2D spawn;
	
	public Carte(String nom, int maxX, int maxY, int spawnX, int spawnY){
		this.setNom(nom);
		this.map = new Coordonnée[maxX][maxY];
		this.setSpawn(new Coordonnée2D(spawnX, spawnY));
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Coordonnée[][] getMap() {
		return this.map;
	}

	public void setCoordonnées(int x, int y, Coordonnée valeur) {
		this.map[x][y] = valeur;
	}

	public Coordonnée2D getSpawn() {
		return this.spawn;
	}

	public void setSpawn(Coordonnée2D spawn) {
		this.spawn = spawn;
	}
	
	public abstract int getTailleMaxX();
	
	public abstract int getTailleMaxY();
}

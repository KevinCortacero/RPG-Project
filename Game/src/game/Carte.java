package game;

import hero.Coord2D;
import hero.Drawable;

public abstract class Carte implements Drawable{
	
	public static final Carte CARTE_BASE = new CarteBase();
	private String nom;
	private int[][] map;
	private Coord2D spawn;
	
	public Carte(String nom, int maxX, int maxY, int spawnX, int spawnY){
		this.setNom(nom);
		this.map = new int[maxX][maxY];
		this.setSpawn(new Coord2D(spawnX, spawnY));
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int[][] getMap() {
		return this.map;
	}

	public void setCoordonnées(int x, int y, int valeur) {
		this.map[x][y] = valeur;
	}

	public Coord2D getSpawn() {
		return this.spawn;
	}

	public void setSpawn(Coord2D spawn) {
		this.spawn = spawn;
	}
	
	public int getMapValue(int x, int y){
		return this.map[x][y];
	}
	
	public abstract int getTailleMaxX();
	
	public abstract int getTailleMaxY();
	
	public abstract void load(String mapFile);
}

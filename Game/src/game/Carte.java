package game;

import hero.Drawable;

public abstract class Carte implements Drawable{
	
	public static final Carte CARTE_BASE = new CarteBase();
	private String nom;
	private int[][] map;
	private float spawnX;
	private float spawnY;
	
	public Carte(String nom, int maxX, int maxY, int spawnX, int spawnY){
		this.setNom(nom);
		this.map = new int[maxX][maxY];
		this.setSpawn(spawnX, spawnY);
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

	public float getSpawnX() {
		return this.spawnX;
	}
	
	public float getSpawnY() {
		return this.spawnY;
	}

	public void setSpawn(float x, float y) {
		this.spawnX = x;
		this.spawnY = y;
	}
	
	public int getMapValue(int x, int y){
		if (y > 0 && y < 1000 && x > 0 && x < 1000)
			return this.map[x][y];
		return 0;
	}
	
	public abstract int getTailleMaxX();
	
	public abstract int getTailleMaxY();
	
	public abstract void load(String mapFile);
}

package game;

import hero.Coord;
import hero.Coord2D;

public abstract class Carte {
	
	public static final Carte CARTE_BASE = new CarteBase();
	public static final Carte CARTE_FEU = new CarteFeu();
	private String nom;
	private Coord[][] map;
	private Coord2D spawn;
	
	public Carte(String nom, int maxX, int maxY, int spawnX, int spawnY){
		this.setNom(nom);
		this.map = new Coord[maxX][maxY];
		this.setSpawn(new Coord2D(spawnX, spawnY));
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Coord[][] getMap() {
		return this.map;
	}

	public void setCoordonnées(int x, int y, Coord valeur) {
		this.map[x][y] = valeur;
	}

	public Coord2D getSpawn() {
		return this.spawn;
	}

	public void setSpawn(Coord2D spawn) {
		this.spawn = spawn;
	}
	
	public abstract int getTailleMaxX();
	
	public abstract int getTailleMaxY();
}

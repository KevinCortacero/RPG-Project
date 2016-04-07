package jeu;

import personnage.Coordonn�e;
import personnage.Coordonn�e2D;

public abstract class Carte {
	
	public static final Carte CARTE_BASE = new CarteBase();
	public static final Carte CARTE_FEU = new CarteFeu();
	private String nom;
	private Coordonn�e[][] map;
	private Coordonn�e2D spawn;
	
	public Carte(String nom, int maxX, int maxY, int spawnX, int spawnY){
		this.setNom(nom);
		this.map = new Coordonn�e[maxX][maxY];
		this.setSpawn(new Coordonn�e2D(spawnX, spawnY));
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Coordonn�e[][] getMap() {
		return this.map;
	}

	public void setCoordonn�es(int x, int y, Coordonn�e valeur) {
		this.map[x][y] = valeur;
	}

	public Coordonn�e2D getSpawn() {
		return this.spawn;
	}

	public void setSpawn(Coordonn�e2D spawn) {
		this.spawn = spawn;
	}
	
	public abstract int getTailleMaxX();
	
	public abstract int getTailleMaxY();
}

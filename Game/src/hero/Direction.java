package hero;

public enum Direction {
	
	HAUT("Haut"),
	BAS("Bas"),
	DROITE("Droite"),
	GAUCHE("Gauche");

	private Direction direction;
	private String nom;
	
	public void sauvegarde(Direction direction) {
		this.direction = direction;
	}

	public Direction getSauvegarde() {
		return this.direction;
	}
	
	private Direction(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return this.nom;
	}
}

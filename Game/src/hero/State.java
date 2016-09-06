package hero;

public enum State {
	
	MARCHE("Marche"),
	SAUTE("Saute"),
	TOMBE("Tombe"),
	ENCLUME("Enclume"),
	IMMOBILE("Immobile"),
	NAGE("Nage"),
	GRIMPE("Grimpe"),
	BOUCLIER("Bouclier");
	
	private String nom;

	private State(String nom) {
		this.nom = nom;
	}
	
	public String toString(){
		return this.nom;
	}
}

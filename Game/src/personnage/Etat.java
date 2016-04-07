package personnage;

public enum Etat {
	
	MARCHE("Marche"),
	SAUTE("Saute"),
	TOMBE("Tombe"),
	ENCLUME("Enclume"),
	IMMOBILE("Immobile"),
	NAGE("Nage"),
	GRIMPE("Grimpe"),
	BOUCLIER("Bouclier");
	
	private String nom;

	private Etat(String nom) {
		this.nom = nom;
	}
	
	public String toString(){
		return this.nom;
	}
}

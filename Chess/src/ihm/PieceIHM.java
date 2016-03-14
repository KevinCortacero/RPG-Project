package ihm;

public class PieceIHM {

	private String couleur;
	private String nom;
	private String typeImage;
	
	public PieceIHM(String couleur, String nom, String typeImage) {
		this.couleur = couleur;
		this.nom = nom;
		this.typeImage = typeImage;
	}

	public String getCouleur() {
		return couleur;
	}

	public String getNom() {
		return nom;
	}

	public String getTypeImage() {
		return typeImage;
	}
	
}

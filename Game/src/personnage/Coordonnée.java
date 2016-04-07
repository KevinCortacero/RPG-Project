package personnage;

public class Coordonnée {
	private int composante;
	
	public Coordonnée(int composante){
		this.setComposante(composante);
	}

	public int getComposante() {
		return this.composante;
	}

	public void setComposante(int composante) {
		this.composante = composante;
	}
}


public class Montant {

	private int valeur;
	
	public Montant(){
		this.valeur = 0;
	}

	public int getValeur() {
		return this.valeur;
	}

	public void MiseAJour(int valeur) {
		this.valeur = valeur;
	}
}
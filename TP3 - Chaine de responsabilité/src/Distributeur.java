import java.util.List;

public class Distributeur {

	private EtatDistributeur etat;
	private Montant montant;
	private CalculateurBillet50 CB50;

	public Distributeur(int nb50, int nb20, int nb10) {
		this.etat = new EtatDistributeur();
		this.CB50 = new CalculateurBillet50(this.etat);
		this.recharger(nb50, nb20, nb10);
	}

	public void recharger(int nb50, int nb20, int nb10) {
		this.etat.setNb50Disponible(nb50);
		this.etat.setNb20Disponible(nb20);
		this.etat.setNb10Disponible(nb10);
	}
	
	public List<Couple> donnerBillets(int montant) {
		this.montant = new Montant();
		this.montant.MiseAJour(montant);
		return this.CB50.calculBillets(this.montant);
	}

	public String toStringProposition(List<Couple> proposition, int montant) {
		StringBuffer res = new StringBuffer();
		res.append("Montant à débiter : " + montant + "€ \n");
		for (Couple c : proposition) {
			res.append(c.toString() + '\n');
		}
		res.append("Montant restant dû : " + this.montantRestantDû(proposition, montant));
		return res.toString();
	}

	public int montantRestantDû(List<Couple> proposition, int montant) {
		int montantRestantDû = montant;
		for (Couple c : proposition) {
			montantRestantDû -= c.getValeurBillet() * c.getNombreBilletsDélivrés();
		}
		return montantRestantDû;
	}

	public EtatDistributeur getEtat() {
		return etat;
	}
}
import java.util.LinkedList;
import java.util.List;


public class CalculateurBillet50 extends CalculateurBillet{

	private CalculateurBillet20 CB20;

	public CalculateurBillet50(EtatDistributeur etat) {
		super(etat);
		this.CB20 = new CalculateurBillet20(etat);
	}

	public List<Couple> calculBillets(Montant montant){
		List<Couple> proposition = new LinkedList<Couple>();
		return this.calcul(montant, proposition);
	}
	
	
	@Override
	public List<Couple> calcul(Montant montant, List<Couple> proposition) {
		if (montant.getValeur() >= 100){
			Couple billet50 = new Couple(50, Math.min(montant.getValeur()/100, this.etat.getNb50Disponible()));
			this.etat.setNb50Disponible(this.etat.getNb50Disponible() - billet50.nombreBilletsDélivrés);
			montant.MiseAJour(montant.getValeur() - billet50.valeurCouple());
			proposition.add(billet50);
		}
		return this.CB20.calcul(montant, proposition);
	}
}
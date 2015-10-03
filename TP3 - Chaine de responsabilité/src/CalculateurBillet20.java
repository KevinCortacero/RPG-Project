import java.util.List;


public class CalculateurBillet20 extends CalculateurBillet{

	private CalculateurBillet10 CB10;

	public CalculateurBillet20(EtatDistributeur etat) {
		super(etat);
		this.CB10 = new CalculateurBillet10(etat);
	}

	@Override
	public List<Couple> calcul(Montant montant,List<Couple> proposition ) {
		if (montant.getValeur() >20){
			int nBillets20 = (montant.getValeur() % 20 == 0) ? 
					montant.getValeur() / 20 -1
					: montant.getValeur() / 20;
			nBillets20 = Math.min(nBillets20, this.etat.getNb20Disponible());
			Couple billet20 = new Couple(20,nBillets20);
			this.etat.setNb20Disponible(this.etat.getNb20Disponible() - billet20.nombreBilletsDélivrés);
			montant.MiseAJour(montant.getValeur() - billet20.valeurCouple());
			proposition.add(billet20);
		}
		return this.CB10.calcul(montant, proposition);
	}

}

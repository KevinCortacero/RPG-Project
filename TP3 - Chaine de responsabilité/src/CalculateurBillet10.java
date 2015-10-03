import java.util.List;


public class CalculateurBillet10 extends CalculateurBillet{

	public CalculateurBillet10(EtatDistributeur etat) {
		super(etat);
	}

	@Override
	public List<Couple> calcul(Montant montant, List<Couple> proposition) {
		if (montant.getValeur() > 0){
			Couple billet10 = new Couple(10,Math.min(montant.getValeur()/10, this.etat.getNb10Disponible()));
			this.etat.setNb10Disponible(this.etat.getNb10Disponible() - billet10.nombreBilletsDélivrés);
			proposition.add(billet10);
		}
		return proposition;
	}

}

import java.util.List;


public abstract class CalculateurBillet {

	protected EtatDistributeur etat;
	
	public CalculateurBillet(EtatDistributeur etat) {
		this.etat = etat;
	}
	
	public abstract List<Couple> calcul(Montant montant, List<Couple> proposition);
	
}

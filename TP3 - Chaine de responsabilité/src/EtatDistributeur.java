
public class EtatDistributeur {

	private int nb50Disponible;
	private int nb20Disponible;
	private int nb10Disponible;
	
	public EtatDistributeur() {
		this.nb50Disponible = 0;
		this.nb20Disponible = 0;
		this.nb10Disponible = 0;
	}

	public int getNb50Disponible() {
		return nb50Disponible;
	}

	public void setNb50Disponible(int nb50Disponible) {
		this.nb50Disponible = nb50Disponible;
	}

	public int getNb20Disponible() {
		return nb20Disponible;
	}

	public void setNb20Disponible(int nb20Disponible) {
		this.nb20Disponible = nb20Disponible;
	}

	public int getNb10Disponible() {
		return nb10Disponible;
	}

	public void setNb10Disponible(int nb10Disponible) {
		this.nb10Disponible = nb10Disponible;
	}
	
	public int getNbDisponible(int valeurBillet){
		int result = 0;
		switch(valeurBillet){
		
		case 5 :
			result = this.getNb10Disponible();
			break;
		case 10 :
			result = this.getNb10Disponible();
			break;
		case 20 :
			result = this.getNb20Disponible();
			break;
		case 50 :
			result = this.getNb50Disponible();
			break;
		}
		return result;
	}

}

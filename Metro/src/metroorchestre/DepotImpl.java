package metroorchestre;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class DepotImpl extends StationImpl {

	public List<Rame> rames;
	
	public DepotImpl(String nom, String url, int port, String stationSuivante, int voieSuivante)throws RemoteException {
		super(nom, url, port, stationSuivante, voieSuivante);
		this.rames = new ArrayList<Rame>();
		this.voies.add(new Voie(2));
	}
	
	public void demarrerRame(){
		Rame r = this.rames.get(0);
		try {
			this.setRame(0, r);
			this.demarrerRame(0);
			this.rames.remove(0);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	// NULL / 20
	/*public int getNumeroVoie(){
		if (this.voies.get(0).estRamePresente(this.rames.get(0))){
			
		}
	}*/

	public void setRame(Rame r){
		this.rames.add(r);
	}
	
}

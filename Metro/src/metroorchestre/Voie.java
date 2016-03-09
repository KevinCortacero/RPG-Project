package metroorchestre;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Voie {

	private Station stationSuivante;
	private Rame rame;
	private Thread gestionnaireRame;
	private int tempsDeParcours;
	private boolean feu;

	public Voie(int tempsDeParcours) {
		this.allumerFeuVert();
		this.tempsDeParcours = tempsDeParcours;
		this.initialiserGestionnaireRame();
	}

	private void initialiserGestionnaireRame() {
		this.gestionnaireRame = new Thread(){
			public void run(){
				Rame rame = Voie.this.getRame();
				try {
					rame.fermerPorte();
					Voie.this.setRame(null);
					rame.actionnerMoteur(Voie.this.tempsDeParcours);
					Voie.this.stationSuivante.setRame(0, rame);
					rame.ouvrirPorte();
					Thread.sleep(3000);
					rame.DepartImminent();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
	}

	public Rame getRame() {
		return this.rame;
	}

	public void setRame(Rame rame){
		this.rame = rame;
	}

	public void ajouterStationSuivante(String url, int port, String stationSuivante){
		this.stationSuivante = Superviseur.rechercherStation(url, port, stationSuivante);
	}

	public void allumerFeuRouge(){
		this.feu = false;
	}

	public void allumerFeuVert(){
		this.feu = true;
	}

	public void demarrerRame(){
		try {
			this.stationSuivante.allumerFeuxRouge(this.getNumeroVoieSuivante());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public boolean estRamePresente(Rame rame){
		return (this.rame == rame);
	}

	public boolean estVert(){
		return this.feu;
	}

	public String getNomStationSuivante(){
		try {
			return this.stationSuivante.getNom();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Clairement pas sur
	public int getNumeroVoieSuivante(){
		try {
			return this.stationSuivante.numeroVoieSuivante(0);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return 0;
	}
}

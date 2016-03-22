package metroorchestre;

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
		System.out.println("Démarrage gestionnaire");
		this.gestionnaireRame = new Thread(){
			@Override
			public void run(){
				Rame rame = Voie.this.getRame();
				if(rame != null){
					try {
						System.out.println("On gère la rame " + rame.getNumero());
						rame.fermerPorte();
						System.out.println("On ferme les portes de la rame " + rame.getNumero());
						rame.actionnerMoteur(Voie.this.tempsDeParcours);
						System.out.println("On actionne le moteur de la rame" + rame.getNumero() + " pour " + Voie.this.tempsDeParcours);
						Voie.this.allumerFeuVert();
						Voie.this.stationSuivante.setRame(Voie.this.getNumeroVoieSuivante(), rame);
						rame.ouvrirPorte();
						Thread.sleep(3000);
						rame.DepartImminent();
						//Voie.this.setRame(null);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		this.gestionnaireRame.start();
	}

	public Rame getRame() {
		return this.rame;
	}

	public void setRame(Rame rame){
		this.rame = rame;
	}

	public void ajouterStationSuivante(String url, int port, String actuelle, String stationSuivante){
		System.out.println(actuelle + " ----> " + stationSuivante);
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
		try {
			if (this.rame != null){
				System.out.println("ON CHERCHE : " + rame.getNumero());
				System.out.println("ON A " + this.rame.getNumero());
				if (rame.getNumero() == this.rame.getNumero()){
					return true;
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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
			System.out.println(this.stationSuivante);
			return this.stationSuivante.numeroVoieSuivante(0);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return 0;
	}
}

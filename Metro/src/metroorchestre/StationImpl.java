package metroorchestre;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class StationImpl extends UnicastRemoteObject implements Station {

	public List<Voie> voies;
	private String nom;
	
	public StationImpl(String nom, String url, int port, String stationSuivante, int voieSuivante) throws RemoteException {
		this.voies = new ArrayList<Voie>();
		this.nom = nom;
	}
	
	@Override
	public String afficher() throws RemoteException {
		return this.getNom();
	}

	@Override
	public String getNom() throws RemoteException {
		return this.nom;
	}

	@Override
	public void ajouterStationSuivante(String machine, int port, int numeroVoieDepart, String stationSuivante,
			int voieSuivante) throws RemoteException, NotBoundException, MalformedURLException {
		this.voies.get(numeroVoieDepart).ajouterStationSuivante(machine, port, stationSuivante);
	}

	@Override
	public boolean estFeuVert(int numeroVoie) throws RemoteException {
		return this.voies.get(numeroVoie).estVert();
	}

	@Override
	public void demarrerRame(int numeroVoie) throws RemoteException {
		try {
			this.voies.get(numeroVoie).getRame().DepartImminent();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Superviseur sup = (Superviseur) Naming.lookup("rmi://localhost:9000/sup");
			sup.ihm.modifierAffichage(this.nom, 1, this.nomStationSuivante(1), 1, "1");
		} catch (MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String nomStationSuivante(int numeroVoie) throws RemoteException {
		return this.voies.get(numeroVoie).getNomStationSuivante();
	}

	@Override
	public int numeroVoieSuivante(int numeroVoie) throws RemoteException {
		return this.voies.get(numeroVoie).getNumeroVoieSuivante();
	}

	@Override
	public void setRame(int numeroVoie, Rame rame) throws RemoteException {
		this.voies.get(numeroVoie).setRame(rame);
	}

	@Override
	public void allumerFeuxRouge(int numeroVoie) throws RemoteException {
		this.voies.get(numeroVoie).allumerFeuRouge();
	}

	@Override
	public int getNumeroVoie(Rame rame) throws RemoteException {
		return rame.getNumero();
	}
}
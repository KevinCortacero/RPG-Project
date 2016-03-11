package metroorchestre;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RameImpl extends UnicastRemoteObject implements Rame{

	private ETAT etat;
	private boolean feuVert;
	private int numero;
	
	public RameImpl(int numero) throws RemoteException{
		this.etat = ETAT.RAME_ARRETEE;
		this.feuVert = false;
		this.numero = numero;
	}
	
	@Override
	public void fermerPorte() throws RemoteException, InterruptedException {
		if (this.etat == ETAT.DEPART_IMMINENT){
			Thread.sleep(500);
			this.etat = ETAT.EN_ACTION;
		}
	}

	@Override
	public void actionnerMoteur(int duree) throws InterruptedException, RemoteException {
		if (this.etat == ETAT.EN_ACTION){
			Thread.sleep(duree * 1000);
			this.etat = ETAT.RAME_ARRETEE;
		}
	}

	@Override
	public void ouvrirPorte() throws InterruptedException, RemoteException {
		if (this.etat == ETAT.RAME_ARRETEE){
			this.etat = ETAT.PORTE_OUVERTE;
		}
	}

	@Override
	public void DepartImminent() throws InterruptedException, RemoteException {
		this.etat = ETAT.DEPART_IMMINENT;
	}

	@Override
	public int getNumero() throws RemoteException {
		return this.numero;
	}

	@Override
	public boolean estRamePreteAPartir() throws RemoteException {
		return this.feuVert;
	}

}

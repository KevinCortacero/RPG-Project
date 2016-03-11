package metroorchestre;

import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Superviseur extends UnicastRemoteObject{

	public final MetroSuperviseurIHMImpl ihm = this.createIHM();
	private EtatLigne etatLigne;
	private List<Rame> rames;
	private List<Station> stations;

	public Superviseur() throws RemoteException{
		this.etatLigne = EtatLigne.FIN_DE_SERVICE;
		this.rames = new ArrayList<Rame>();
		this.stations = new ArrayList<Station>();
		
		try {
			this.stations.add((Station)Naming.lookup("rmi://localhost:9000/station-Depot"));
			System.out.println("On récupère le depot");
		} catch (MalformedURLException | RemoteException | NotBoundException e1) {
			e1.printStackTrace();
		}
		

		try {
			for (int i = 0; i < StationServer.STATIONS.length; i++){
				this.stations.add((Station) Naming.lookup("rmi://localhost:9000/station-"+StationServer.STATIONS[i]));
				System.out.println(this.stations.get(i).afficher());
			}
			for (int i = 1; i <= StationServer.NB_RAMES; i++){
				Rame r = (Rame) Naming.lookup("rmi://localhost:9000/rame-"+i);
				this.rames.add(r);
			}
			System.out.println(this.stations.size());
			for (int i = 0; i < StationServer.STATIONS.length-1; i++){
				System.out.println("station suivante : " + StationServer.STATIONS[i]);
				this.stations.get(0).ajouterStationSuivante("localhost", 9000, 0, StationServer.STATIONS[i], 0);
			}
			//this.stations.get(StationServer.STATIONS.length-1).ajouterStationSuivante("localhost", 9000, 0, StationServer.STATIONS[StationServer.STATIONS.length-1], 1);
		} catch (MalformedURLException | RemoteException | NotBoundException e){
			e.printStackTrace();
		}
		
		this.initialiserIHM();
	}

	public void arreterMetro(){
		this.etatLigne = EtatLigne.FIN_DE_SERVICE;
	}

	public void gererLigne(){
		Thread t = new GestionLigne();
		t.start();
	}

	private MetroSuperviseurIHMImpl createIHM(){
		try {
			return new MetroSuperviseurIHMImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void initialiserIHM()
	{
		this.ihm.initialiserReseau(Arrays.asList(StationServer.STATIONS));                 
		Ecouteur ec = new Ecouteur() ;
		this.ihm.setActionListenerArreter (ec);
		this.ihm.setActionListenerDemarrer(ec);       
	}

	private class Ecouteur implements java.awt.event.ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent ae) 
		{
			if (ae.getActionCommand().equals("START"))
			{
				javax.swing.JButton bt = (javax.swing.JButton) ae.getSource() ;
				bt.setEnabled(false) ;
				Superviseur.this.gererLigne();
			}
			else
			{
				javax.swing.JButton bt = (javax.swing.JButton) ae.getSource() ;
				bt.setEnabled(false) ;                
				Superviseur.this.arreterMetro();
			}
		}
	}

	public static Station rechercherStation(String url, int port, String nom){
		try {
			return (Station) Naming.lookup("rmi://" + url + ":" + port + "/station-" + nom);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Rame rechercherRame(String url, int port, int num){
		try {
			return (Rame) Naming.lookup("rmi://" + url + ":" + port + "/rame-" + num);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean sontToutesLesRamesAuDepot(){
		try {
			DepotImpl depot = (DepotImpl)Naming.lookup("rmi://localhost:9000/station-Depot");
			return (depot.rames.size() == StationServer.NB_RAMES);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean estDepotVide(){
		try {
			DepotImpl depot = (DepotImpl)Naming.lookup("rmi://localhost:9000/station-Depot");
			return (depot.rames.size() == 0);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Station rechercherStationRame(Rame r){
		for (Station s : this.stations){
			for (Voie v : ((StationImpl)s).voies){
				if (v.estRamePresente(r)){
					return s;
				}
			}
		}
		return null;
	}

	public static void main(final String [] argv) throws RemoteException, NotBoundException, MalformedURLException, AlreadyBoundException
	{      
		/**
		 * Connexion aux diffÃ©rentes stations
		 */
		Superviseur sup = new Superviseur();  
		Naming.rebind("rmi://localhost:9000/sup", sup);
	}

	class GestionLigne extends Thread{

		@Override
		public void run(){
			Superviseur.this.etatLigne = EtatLigne.DEMARRAGE_METRO;
			while (Superviseur.this.etatLigne != EtatLigne.FIN_DE_SERVICE){
				for (Rame r : Superviseur.this.rames){
					try {
						if (r.estRamePreteAPartir() && Superviseur.this.etatLigne != EtatLigne.FIN_DE_SERVICE && ((DepotImpl)Superviseur.this.stations.get(0)).rames.contains(r)){
							Station suivante = Superviseur.rechercherStation("localhost", 9000, Superviseur.this.rechercherStationRame(r).nomStationSuivante(0));
							if (suivante.estFeuVert(0)){
								r.DepartImminent();
							}
							if (Superviseur.this.etatLigne == EtatLigne.DEMARRAGE_METRO){
								if (Superviseur.this.estDepotVide()){
									Superviseur.this.etatLigne = EtatLigne.EN_SERVICE;
								}
								else if (Superviseur.this.etatLigne == EtatLigne.FIN_DE_SERVICE){
									// Superviseur.this.sontToutesLesRamesAuDepot();
									// CHELOU
								}
									
							}
						}
					} catch (RemoteException | InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
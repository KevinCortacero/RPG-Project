package metroorchestre;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class StationServer{

	protected final static int NB_RAMES = 7;
	protected final static String[] STATIONS= {"Ramonville", "UPS", "Pharmacie", "Rangueil", "Saouzelong", "Saint-Agne"};

	public static void main(String[] args) {
		DepotImpl depot;
		try {
			LocateRegistry.createRegistry(9000);
			depot = new DepotImpl("Depot", "localhost", 9000, STATIONS[0], 0);
			Naming.rebind("rmi://localhost:9000/station-Depot", depot);
			System.out.println("Ajout du Depot");
			for (int i = 0; i <= STATIONS.length-2; i++){
				Station station = new StationImpl(STATIONS[i], "localhost", 9000, STATIONS[i+1], 0);
				Naming.rebind("rmi://localhost:9000/station-" + STATIONS[i], station);
				System.out.println("Ajout de la station " + STATIONS[i]);
			}
			
			Station station = new StationImpl(STATIONS[STATIONS.length-1], "localhost", 9000, STATIONS[STATIONS.length-2], 1);
			Naming.rebind("rmi://localhost:9000/station-" + STATIONS[STATIONS.length-1], station);
			System.out.println("Ajout de la station " + STATIONS[STATIONS.length-1]);
			
			for (int i = 1; i <= NB_RAMES; i++){
				Rame rame = new RameImpl(i);
				depot.rames.add(rame);
				Naming.rebind("rmi://localhost:9000/rame-" + i, rame);
				System.out.println("Ajout de la rame " + i);
			}
		} catch (MalformedURLException | RemoteException e) {
			e.printStackTrace();
		}
	}
}

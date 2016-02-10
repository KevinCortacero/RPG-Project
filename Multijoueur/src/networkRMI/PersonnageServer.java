package networkRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class PersonnageServer {

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(9999);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Personnage twarz = null;
		try {
			twarz = new PersonnageImpl("Twarz", 666, 666);
			Naming.rebind("rmi://localhost:9999/Twarz", twarz);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Personnage koreuc = null;
		try {
			koreuc = new PersonnageImpl("Koreuc", 0, 0);
			Naming.rebind("rmi://localhost:9999/Koreuc", koreuc);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

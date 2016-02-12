package networkRMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Test {

	public static void main(String[] args) {
		try {
			Player qq = (Player) Naming.lookup("rmi://localhost:9999/Twarz");
			System.out.println("Bienvenue : " + qq.getPseudo());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}

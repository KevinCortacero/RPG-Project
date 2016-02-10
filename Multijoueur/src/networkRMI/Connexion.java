package networkRMI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class Connexion implements Runnable {

	private ServerSocket ss;
	private Socket socket;
	private ObjectInputStream in = null;
	private static Map<String,IdentifiantClient> listeClient;
	private PersonnageImpl perso;

	public static void putClient(String libelle, IdentifiantClient identifiantClient){
		Connexion.listeClient.put(libelle, identifiantClient);
		try {
			Naming.rebind(identifiantClient.getPers().pseudo, identifiantClient.getPers());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Personnage getPersonnage(String name){
		Personnage perso = null;
		try {
			perso = (Personnage) Naming.lookup(name);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return perso;
	}

	public static Map<String,IdentifiantClient> getListeClient(){
		return Connexion.listeClient;
	}

	public Connexion(ServerSocket ss) {
		this.ss = ss;
		if (Connexion.listeClient == null) {
			Connexion.listeClient = new HashMap<String,IdentifiantClient>();
		}
	}

	@Override
	public void run() {
		int nbCo = 0;
		while(true && nbCo <2){
			try {
				this.socket = ss.accept();
				in = new ObjectInputStream(this.socket.getInputStream());
				Server.getMaFrame().sysout("[SERVEUR] Quelqu'un vient de se connecter au serveur");
				this.perso = (PersonnageImpl)in.readObject();
				Connexion.putClient(this.perso.getPseudo(), new IdentifiantClient(this.socket,this.perso));
				Server.getMaFrame().sysout("[SERVEUR] " + this.perso);
				
				// communication 
				Thread t1 = new Thread(new CommunicationServer(this.perso.getPseudo()));
				t1.start();
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			nbCo++;
		}
	}
}
package network2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Connexion implements Runnable {

	private ServerSocket ss;
	private Socket socket;
	private ObjectInputStream in = null;
	private static Map<String,IdentifiantClient> listeClient;
	private PrintWriter out;

	public Connexion(ServerSocket ss){
		this.ss = ss;
		if (Connexion.listeClient == null) {
			Connexion.listeClient = new HashMap<String,IdentifiantClient>();
		}
	}
	

	public static void addClient(String pseudo, IdentifiantClient identifiantClient){
		Connexion.listeClient.put(pseudo, identifiantClient);
	}

	public static IdentifiantClient getClient(String pseudo){
		return Connexion.listeClient.get(pseudo);
	}
	
	public static void removeClient(String pseudo){
		Connexion.listeClient.remove(pseudo);
	}

	public static Map<String,IdentifiantClient> getListeClient(){
		return Connexion.listeClient;
	}

	@Override
	public void run() {
		int nbCo = 0;
		while(nbCo <3){
			try {
				this.socket = ss.accept();
				in = new ObjectInputStream(this.socket.getInputStream());
				Personnage perso = (Personnage) in.readObject();
				this.out = new PrintWriter(this.socket.getOutputStream());
				Server.getMaFrame().sysout("[SERVEUR] " + perso + " vient de se connecter au serveur");
				Connexion.addClient(perso.getPseudo(), new IdentifiantClient(this.socket,perso));
				this.out.println("[SERVEUR] : Bienvenue !!");
				this.out.flush();
				// communication 
				Thread t1 = new Thread(new CommunicationServer(perso));
				t1.start();

			} catch (IOException | ClassNotFoundException e){
				e.printStackTrace();
			}
			nbCo++;
		}
	}
}
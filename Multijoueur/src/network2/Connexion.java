package network2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Connexion implements Runnable {
	
	private Socket socket;
	private ObjectInputStream in = null;
	private static Map<String,IdentifiantClient> listeClient;
	private PrintWriter out;

	public Connexion(){
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
				this.socket = Server.getInstance().getSocketServer().accept();
				this.out = new PrintWriter(this.socket.getOutputStream());
				System.out.println("out passe");
				this.in = new ObjectInputStream(this.socket.getInputStream());
				Personnage perso = (Personnage) this.in.readObject();
				Server.getInstance().getConsole().sysout("[SERVEUR] " + perso + " vient de se connecter au serveur");
				Connexion.addClient(perso.getPseudo(), new IdentifiantClient(this.socket,perso));
				this.out.println("[SERVEUR] : Bienvenue !!");
				this.out.flush();
				// communication 
				Thread t1 = new Thread(new CommunicationServer(perso));
				t1.start();

			} catch (ClassNotFoundException e){
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			nbCo++;
		}
	}
}
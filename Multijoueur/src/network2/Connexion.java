package network2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Connexion implements Runnable {
	
	private static Map<String,ConnectedClient> listeClient;

	public Connexion(){
		if (Connexion.listeClient == null) {
			Connexion.listeClient = new HashMap<String,ConnectedClient>();
		}
	}
	

	public static void addClient(String pseudo, ConnectedClient identifiantClient){
		Connexion.listeClient.put(pseudo, identifiantClient);
	}

	public static ConnectedClient getClient(String pseudo){
		return Connexion.listeClient.get(pseudo);
	}
	
	public static void removeClient(String pseudo){
		Server.getInstance().getConsole().sysout(Connexion.listeClient.get(pseudo).getPers().toString() + " s'est déconnecté.");
		Connexion.listeClient.remove(pseudo);
	}

	public static Map<String,ConnectedClient> getListeClient(){
		return Connexion.listeClient;
	}

	@Override
	public void run() {
		int nbCo = 0;
		while(nbCo <3){
			Player player = this.registerPlayer(); 
			this.startPlayerCommunication(player);
			nbCo++;
		}
	}


	private void startPlayerCommunication(Player player) {
		Thread t1 = new Thread(new CommunicationServer(player));
		t1.start();
	}


	private Player registerPlayer(){
		Socket socketPlayer = this.listenSocketServer();
		this.welcomePlayer(socketPlayer);
		Player player = this.register(socketPlayer);
		return player;
	}


	private void welcomePlayer(Socket socketPlayer) {
		try {
			this.send(new PrintWriter(socketPlayer.getOutputStream()),"Bienvenue !!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Player register(Socket socketPlayer){
		try {
			ObjectInputStream in = new ObjectInputStream(socketPlayer.getInputStream());
			Player player = (Player) in.readObject();
			Server.getInstance().getConsole().sysout(" [SERVEUR] --> " + player + " vient de se connecter au serveur");
			Connexion.addClient(player.getPseudo(), new ConnectedClient(socketPlayer,player));
			return player;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private void send(PrintWriter out, String message) {
		out.println(" [SERVEUR] --> " + message);
		out.flush();
	}

	private Socket listenSocketServer() {
		try {
			return Server.getInstance().getSocketServer().accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
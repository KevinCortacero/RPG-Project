package network2;

import ihm.MaFrame;

import java.io.IOException;
import java.net.ServerSocket;


public class Server{

	public static ServerSocket socketServer = null;
	public static Thread t;
	private static MaFrame maFrame;

	public static MaFrame getMaFrame(){

		if (Server.maFrame == null){
			Server.maFrame = new MaFrame();
		}
		return Server.maFrame;
	}

	public static void main(String[] args) {
		try {
			socketServer = new ServerSocket(26964);
			Server.getMaFrame().sysout("[SERVEUR] Le serveur est à l'écoute du port " + socketServer.getLocalPort());
			t = new Thread(new Annuaire(socketServer));
			t.start();

		} catch (IOException e) {
			Server.getMaFrame().sysoutErreur("[SERVEUR] Le port " + socketServer.getLocalPort() + " est déjà utilisé !");
		}
	}
}


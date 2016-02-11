package network2;

import ihm.MaFrame;

import java.io.IOException;
import java.net.ServerSocket;


public class Server{

	public ServerSocket socketServer;
	public Thread t;
	private static MaFrame maFrame;

	public Server() {
		this.initialiser();
	}
	
	public void initialiser(){
		try {
			this.socketServer = new ServerSocket(26964);
			Server.getMaFrame().sysout("[SERVEUR] Initialisation sur le port " + this.socketServer.getLocalPort() + "...");
			this.t = new Thread(new Annuaire(this.socketServer));
		} catch (IOException e) {
			Server.getMaFrame().sysoutErreur("[SERVEUR] Le port " + this.socketServer.getLocalPort() + " est déjà utilisé !");
		}
	}
	
	public static MaFrame getMaFrame(){

		if (Server.maFrame == null){
			Server.maFrame = new MaFrame();
		}
		return Server.maFrame;
	}

	public void start(){
		this.t.start();
	}
	public static void main(String[] args) {
		Server serv = new Server();
		serv.start();
	}
}


package network2;

import ihm.MaFrame;

import java.io.IOException;
import java.net.ServerSocket;


public class Server{

	public ServerSocket socketServer;
	public Thread t;
	private static MaFrame maFrame;
	private Connexion connexion;

	public Server() {
		this.initialiser();
	}
	
	public void initialiser(){
		try {
			this.socketServer = new ServerSocket(26964);
			Server.getMaFrame().sysout(" [SERVEUR] Initialisation sur le port " + this.socketServer.getLocalPort() + "...");
			this.connexion = new Connexion(this.socketServer);
			this.t = new Thread(this.connexion);
		} catch (IOException e) {
			Server.getMaFrame().sysoutErreur(" [SERVEUR] Le port " + this.socketServer.getLocalPort() + " est déjà utilisé !");
		}
	}
	
	public Connexion getAnnuaire(){
		return this.connexion;
	}

	public static MaFrame getMaFrame(){

		if (Server.maFrame == null){
			Server.maFrame = new MaFrame();
		}
		return Server.maFrame;
	}

	public void start(){
		this.t.start();
		Server.getMaFrame().sysout(" [SERVEUR] Démarrage du serveur...");
	}
	public static void main(String[] args) {
		Server serv = new Server();
		serv.start();
	}
}


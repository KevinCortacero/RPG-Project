package network2.serveur;

import ihm.Console;

import java.io.IOException;
import java.net.ServerSocket;


public class Server{

	private ServerSocket socketServer;
	private Thread t;
	private Console out;
	private Connexion connexion;
	private static Server instance;
	
	public static Server getInstance(){
		if (Server.instance == null)
			Server.instance = new Server();
		return Server.instance;
	}
	
	private Server() {
		this.initialiser();
	}
	
	public static void print(String message){
		Server.getInstance().out.print(message);
	}
	
	private void initialiser(){
		try {
			this.socketServer = new ServerSocket(26964);
			this.out = new Console();
			this.out.print(" [SERVEUR] Initialisation sur le port " + this.socketServer.getLocalPort());
			this.t = new Thread(new Connexion());
		} catch (IOException e) {
			this.out.sysoutErreur(" [SERVEUR] Le port " + this.socketServer.getLocalPort() + " est déjà utilisé !");
		}
	}
	
	public void start(){
		this.t.start();
		this.out.print(" [SERVEUR] Démarrage du serveur");
	}
	public static void main(String[] args){
		Server serv = Server.getInstance();
		serv.start();
	}
	
	public Connexion getConnexion(){
		return this.connexion;
	}

	private Console getConsole() {
		return this.out;
	}

	public ServerSocket getSocketServer() {
		return this.socketServer;
	}
}


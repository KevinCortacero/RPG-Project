package networkRMI;

import ihm.Console;

import java.applet.Applet;
import java.io.*;
import java.net.*;


public class Server extends Applet{

	private static final long serialVersionUID = 1L;
	public static ServerSocket socketServer = null;
	public static Thread t;
	private static Console maFrame;

	public static Console getMaFrame(){

		if (Server.maFrame == null){
			Server.maFrame = new Console();
		}
		return Server.maFrame;
	}


	public static void main(String[] args) {
		try {
			socketServer = new ServerSocket(2009);
			System.out.println("[SERVEUR] Le serveur est à l'écoute du port "+socketServer.getLocalPort());
			Server.getMaFrame().sysout("[SERVEUR] Le serveur est à l'écoute du port "+socketServer.getLocalPort());
			t = new Thread(new Connexion(socketServer));
			t.start();

		} catch (IOException e) {
			System.err.println("[SERVEUR] Le port "+socketServer.getLocalPort()+" est déjà utilisé !");
			Server.getMaFrame().sysoutErreur("[SERVEUR] Le port "+socketServer.getLocalPort()+" est déjà utilisé !");
		}
	}

	public void init() {
		try {
			socketServer = new ServerSocket(2009);
			System.out.println("[SERVEUR] Le serveur est à l'écoute du port "+socketServer.getLocalPort());
			Server.getMaFrame().sysout("[SERVEUR] Le serveur est à l'écoute du port "+socketServer.getLocalPort());
			t = new Thread(new Connexion(socketServer));
			t.start();

		} catch (IOException e) {
			System.err.println("[SERVEUR] Le port "+socketServer.getLocalPort()+" est déjà utilisé !");
			Server.getMaFrame().sysoutErreur("[SERVEUR] Le port "+socketServer.getLocalPort()+" est déjà utilisé !");
		}
	}
}


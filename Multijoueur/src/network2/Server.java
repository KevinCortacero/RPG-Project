package network2;

import java.io.*;
import java.net.*;


public class Server {
	public static ServerSocket socketServer = null;
	public static Thread t;

	public static void mettreAJour(Personnage perso){
		
	}

	public static void main(String[] args) {

		try {
			socketServer = new ServerSocket(2009);
			System.out.println("[SERVEUR] Le serveur est à l'écoute du port "+socketServer.getLocalPort());

			t = new Thread(new Connexion(socketServer));
			t.start();

		} catch (IOException e) {
			System.err.println("[SERVEUR] Le port "+socketServer.getLocalPort()+" est déjà utilisé !");
		}

	}


}


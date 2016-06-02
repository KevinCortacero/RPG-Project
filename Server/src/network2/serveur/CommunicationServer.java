package network2.serveur;

import ihm.Console;

import java.io.IOException;
import java.io.ObjectInputStream;

import network2.Player;
import network2.client.SocketClient;

public class CommunicationServer implements Runnable {

	private Player player;
	private ObjectInputStream in;
	private Thread read;
	private Thread write;

	public CommunicationServer(Player player, ObjectInputStream in) {
		this.in = in;
		this.player = player;
	}

	@Override
	public void run() {
		
		read = new Thread(){
			public void run() {
				while(true){
					try {
						Player p = (Player)in.readObject();
						//Connexion.updatePlayer(p);
						Server.print(p.toString());
					} catch (IOException | ClassNotFoundException e1) {}
					
					try {
						Thread.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		read.start();
	}
}
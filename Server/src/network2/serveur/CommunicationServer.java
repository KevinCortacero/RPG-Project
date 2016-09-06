package network2.serveur;

import java.io.IOException;
import java.io.ObjectInputStream;

import network2.Player;

public class CommunicationServer implements Runnable {

	private Player player;
	private ObjectInputStream in;

	public CommunicationServer(Player player, ObjectInputStream in) {
		this.in = in;
		this.player = player;
	}

	@Override
	public void run() {
		while(true){
			try {
				CommunicationServer.this.player = (Player) CommunicationServer.this.in.readObject();
				Server.print(Integer.toString(CommunicationServer.this.player.getPositionY()));
				//Connexion.updatePlayer(p);
			} catch (IOException | ClassNotFoundException e1) {}

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
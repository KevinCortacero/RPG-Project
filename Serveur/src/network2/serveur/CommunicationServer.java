package network2.serveur;

import java.io.IOException;
import java.io.ObjectInputStream;

import network2.Player;
import network2.client.SocketClient;

public class CommunicationServer implements Runnable {

	private Player player;
	
	public CommunicationServer(Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		
		new UpdatePlayerServer(Connexion.getListeClient().get(player.getPseudo()).getSocket());
		while(true){
			for(String key : Connexion.getListeClient().keySet()){
				/*try {
					ObjectInputStream in = new ObjectInputStream(Connexion.getClient(key).getSocket().getInputStream());

				} catch (IOException e1) {
					e1.printStackTrace();
				}*/
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
package network2;

import java.io.IOException;
import java.io.ObjectInputStream;

public class CommunicationServer implements Runnable {

	public CommunicationServer(Player player) {

	}

	@Override
	public void run() {
		while(true){
			for(String key : Connexion.getListeClient().keySet()){
				try {
					ObjectInputStream in = new ObjectInputStream(Connexion.getClient(key).getSocket().getInputStream());

				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}
}
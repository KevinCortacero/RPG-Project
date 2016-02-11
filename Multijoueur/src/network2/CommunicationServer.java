package network2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;


public class CommunicationServer implements Runnable {

	private Personnage perso;

	public CommunicationServer(Personnage perso) {
		this.perso = perso;
	}

	@Override
	public void run() {
		int nbTourBoucleTotal = 5;
		int nbToutBoucle = 0;
		while( nbToutBoucle < nbTourBoucleTotal ){
			for(String key : Connexion.getListeClient().keySet()){
				Personnage perso = null;
				try {
					ObjectInputStream in = new ObjectInputStream(Connexion.getListeClient().get(key).getSocket().getInputStream());
					perso = (Personnage)in.readObject();
					
					Connexion.addClient(key, new IdentifiantClient(Connexion.getClient(key).getSocket(),perso));
				} catch (IOException e) {}
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			System.out.println("ça marche");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			nbToutBoucle++;
		}

	}

}

package network2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;


public class CommunicationServer implements Runnable {

	private ObjectInputStream in = null;
	private String libelle;
	private Personnage perso;

	public CommunicationServer(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public void run() {
		int x = 0;
		int y = 0; 
		int nbTourBoucleTotal = 1;
		int nbToutBoucle = 0;
		while( nbToutBoucle < nbTourBoucleTotal ){
			for(String key : Connexion.getListeClient().keySet()){
				try {
	
					in = new ObjectInputStream(Connexion.getListeClient().get(key).getSocket().getInputStream());
					System.out.println("[SERVEUR] Le perso "+key );
					perso = (Personnage)in.readObject();
			
					System.out.println("[SERVEUR]   ---->    X:"+ perso.getPositionX() + "   Y:"+ perso.getPositionY());
					Connexion.putClient(key, new IdentifiantClient(Connexion.getClient(key).getSocket(),perso));


				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			nbToutBoucle++;
		}

	}

}

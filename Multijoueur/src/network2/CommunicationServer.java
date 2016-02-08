package network2;

import java.io.IOException;
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
		int nbTourBoucleTotal = 1;
		int nbToutBoucle = 0;
		while( nbToutBoucle < nbTourBoucleTotal ){
			for(String key : Connexion.getListeClient().keySet()){
				try {
	
					in = new ObjectInputStream(Connexion.getListeClient().get(key).getSocket().getInputStream());
					perso = (Personnage)in.readObject();
					Connexion.putClient(key, new IdentifiantClient(Connexion.getClient(key).getSocket(),perso));
					
				} catch (IOException e) {
					//e.printStackTrace();
					Server.getMaFrame().sysoutErreur("[SERVEUR] le client " + key + " ne s'est pas ré-authentifier ");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} finally{
					Server.getMaFrame().sysout("[SERVEUR] Le perso "+key );
					perso = Connexion.getClient(key).getPers();
					Server.getMaFrame().sysout("[SERVEUR]   ---->    X:"+ perso.getPositionX() + "   Y:"+ perso.getPositionY());
					
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

package networkRMI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;


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
	
					this.in = new ObjectInputStream(Connexion.getListeClient().get(key).getSocket().getInputStream());
					this.perso = (PersonnageImpl)in.readObject();
					//Connexion.putClient(key, new IdentifiantClient(Connexion.getPersonnage(key).getSocket(),perso));
					
				} catch (IOException e) {
					//e.printStackTrace();
					Server.getMaFrame().sysoutErreur("[SERVEUR] le client " + key + " ne s'est pas ré-authentifier ");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} finally{
					Server.getMaFrame().sysout("[SERVEUR] Le perso "+key );
					this.perso = Connexion.getPersonnage(key);
					try {
						Server.getMaFrame().sysout("[SERVEUR]   ---->    X:"+ perso.getPositionX() + "   Y:"+ perso.getPositionY());
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
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

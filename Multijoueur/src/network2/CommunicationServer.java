package network2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;


public class CommunicationServer implements Runnable {

	private ObjectInputStream in = null;
	private String libelle;
	private Personnage perso;
	private PrintWriter out;

	public CommunicationServer(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public void run() {
		int nbTourBoucleTotal = 5;
		int nbToutBoucle = 0;
		while( nbToutBoucle < nbTourBoucleTotal ){
			for(String key : Annuaire.getListeClient().keySet()){
				try {
					in = new ObjectInputStream(Annuaire.getListeClient().get(key).getSocket().getInputStream());
					perso = (Personnage)in.readObject();
					Annuaire.addClient(key, new IdentifiantClient(Annuaire.getClient(key).getSocket(),perso));
				} catch (IOException e) {}
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				} finally{
					perso = Annuaire.getClient(key).getPers();
					Server.getMaFrame().sysout("[SERVEUR] : Bienvenue " + perso);

					Server.getMaFrame().sysout("[SERVEUR]   ---->    X:"+ perso.getPositionX() + "   Y:"+ perso.getPositionY());				
				}

				try {
					this.out = new PrintWriter(Annuaire.getListeClient().get(key).getSocket().getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("[SERVEUR] : 1");
				this.out.println("[SERVEUR] : Bienvenue !!");
				this.out.flush();
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

package communicationJoueur;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TicTacToc extends Remote{
	
	public void propagerCoup(final int ligne, final int colonne) throws RemoteException ;

	public String getPseudo() throws RemoteException;

	public void setAdversaire(TicTacToc adv) throws RemoteException;

}
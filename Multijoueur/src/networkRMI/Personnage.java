package networkRMI;

import java.awt.Graphics;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Personnage extends Remote {

	public void mettreAJour()throws RemoteException;
	public void afficherPersonnage(Graphics g)throws RemoteException;
	public String getPseudo()throws RemoteException;
	public int getPositionY()throws RemoteException;
	public int getPositionX()throws RemoteException;
}

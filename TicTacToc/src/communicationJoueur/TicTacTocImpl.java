package communicationJoueur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JButton;

import tictactoc.TicTacTocIHM;
import tictactoc.TicTacTocIHMImpl;

@SuppressWarnings("serial")
public class TicTacTocImpl extends UnicastRemoteObject implements ActionListener, TicTacToc {

	private TicTacTocIHM ihm;
	private TicTacToc joueurDistant;
	private String pseudo;
	
	public TicTacTocImpl(boolean commence, String pseudo) throws Exception{
		this.ihm = new TicTacTocIHMImpl(commence);
		this.ihm.addListener(this);
		this.pseudo = pseudo;
		Naming.bind("rmi://localhost:10666/" + this.pseudo, this);
	}

	@Override
	public void propagerCoup(final int ligne, final int colonne) throws RemoteException {
		this.ihm.jouerDistant(ligne, colonne);
	}

	public void setAdversaire(TicTacToc adv) {
		try {
			this.joueurDistant = (TicTacToc) Naming.lookup("rmi://localhost:10666/" + adv.getPseudo());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if (!this.ihm.estBloque() && btn.getText().length() == 0) {
			int[] coords = this.ihm.getDernierCoupJoue(btn);
			try {
				this.joueurDistant.propagerCoup(coords[0], coords[1]);
			} catch (RemoteException e1) {
				System.out.println(e1.getMessage());
			}
		}
	}
	
	public String getPseudo() {
		return pseudo;
	}

}
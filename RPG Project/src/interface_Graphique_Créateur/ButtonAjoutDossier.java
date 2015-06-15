package interface_Graphique_Cr�ateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonAjoutDossier extends JButton implements ActionListener {

	private boolean peutCr�erDossier;
	
	public ButtonAjoutDossier(){
		super("Ajouter un Dossier");
		this.setPeutCr�erCarte(false);
		this.addActionListener(this);
		this.setBounds(10, 640, 160, 30);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this)
			this.setPeutCr�erCarte(true);
		else
			this.setPeutCr�erCarte(false);
	}

	public boolean isPeutCr�erCarte() {
		return peutCr�erDossier;
	}

	public void setPeutCr�erCarte(boolean peutCr�erCarte) {
		this.peutCr�erDossier = peutCr�erCarte;
	}
}

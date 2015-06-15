package interface_Graphique_Créateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonAjoutDossier extends JButton implements ActionListener {

	private boolean peutCréerDossier;
	
	public ButtonAjoutDossier(){
		super("Ajouter un Dossier");
		this.setPeutCréerCarte(false);
		this.addActionListener(this);
		this.setBounds(10, 640, 160, 30);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this)
			this.setPeutCréerCarte(true);
		else
			this.setPeutCréerCarte(false);
	}

	public boolean isPeutCréerCarte() {
		return peutCréerDossier;
	}

	public void setPeutCréerCarte(boolean peutCréerCarte) {
		this.peutCréerDossier = peutCréerCarte;
	}
}

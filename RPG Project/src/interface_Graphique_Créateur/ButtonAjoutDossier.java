package interface_Graphique_Créateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonAjoutDossier extends JButton implements ActionListener{

	private boolean peutCréerDossier;
	private ButtonsSynchronisation buttonsSynchronisation;
	
	public ButtonAjoutDossier(ButtonsSynchronisation buttonsSynchronisation){
		super("Ajouter un Dossier");
		this.buttonsSynchronisation = buttonsSynchronisation;
		this.setPeutCréerDossier(false);
		this.setBounds(5, 40, 160, 30);
		this.addActionListener(this);
	}

	public boolean isPeutCréerDossier() {
		return peutCréerDossier;
	}

	public void setPeutCréerDossier(boolean peutCréerDossier) {
		this.peutCréerDossier = peutCréerDossier;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.buttonsSynchronisation.buttonAjoutCarte.setPeutCréerCarte(false);
		this.buttonsSynchronisation.buttonAjoutDossier.setPeutCréerDossier(true);
		this.buttonsSynchronisation.buttonSupprimer.setPeutSupprimer(false);
	}
}

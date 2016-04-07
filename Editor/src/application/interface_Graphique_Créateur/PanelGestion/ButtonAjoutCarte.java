package application.interface_Graphique_Créateur.PanelGestion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonAjoutCarte extends JButton implements ActionListener{

	private boolean peutCréerCarte;
	private ButtonsSynchronisation buttonsSynchronisation;
	
	public ButtonAjoutCarte(ButtonsSynchronisation buttonsSynchronisation){
		super("Ajouter une Carte");
		this.buttonsSynchronisation = buttonsSynchronisation;
		this.setPeutCréerCarte(false);
		this.setBounds(5, 0, 160, 30);
		this.addActionListener(this);
	}

	public boolean isPeutCréerCarte() {
		return peutCréerCarte;
	}

	public void setPeutCréerCarte(boolean peutCréerCarte) {
		this.peutCréerCarte = peutCréerCarte;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.buttonsSynchronisation.buttonAjoutCarte.setPeutCréerCarte(true);
		this.buttonsSynchronisation.buttonAjoutDossier.setPeutCréerDossier(false);
		this.buttonsSynchronisation.buttonSupprimer.setPeutSupprimer(false);
	}
}

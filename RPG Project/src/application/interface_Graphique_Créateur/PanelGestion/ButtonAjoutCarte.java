package application.interface_Graphique_Cr�ateur.PanelGestion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonAjoutCarte extends JButton implements ActionListener{

	private boolean peutCr�erCarte;
	private ButtonsSynchronisation buttonsSynchronisation;
	
	public ButtonAjoutCarte(ButtonsSynchronisation buttonsSynchronisation){
		super("Ajouter une Carte");
		this.buttonsSynchronisation = buttonsSynchronisation;
		this.setPeutCr�erCarte(false);
		this.setBounds(5, 0, 160, 30);
		this.addActionListener(this);
	}

	public boolean isPeutCr�erCarte() {
		return peutCr�erCarte;
	}

	public void setPeutCr�erCarte(boolean peutCr�erCarte) {
		this.peutCr�erCarte = peutCr�erCarte;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.buttonsSynchronisation.buttonAjoutCarte.setPeutCr�erCarte(true);
		this.buttonsSynchronisation.buttonAjoutDossier.setPeutCr�erDossier(false);
		this.buttonsSynchronisation.buttonSupprimer.setPeutSupprimer(false);
	}
}

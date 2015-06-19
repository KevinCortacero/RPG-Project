package interface_Graphique_Cr�ateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonAjoutDossier extends JButton implements ActionListener{

	private boolean peutCr�erDossier;
	private ButtonsSynchronisation buttonsSynchronisation;
	
	public ButtonAjoutDossier(ButtonsSynchronisation buttonsSynchronisation){
		super("Ajouter un Dossier");
		this.buttonsSynchronisation = buttonsSynchronisation;
		this.setPeutCr�erDossier(false);
		this.setBounds(5, 40, 160, 30);
		this.addActionListener(this);
	}

	public boolean isPeutCr�erDossier() {
		return peutCr�erDossier;
	}

	public void setPeutCr�erDossier(boolean peutCr�erDossier) {
		this.peutCr�erDossier = peutCr�erDossier;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.buttonsSynchronisation.buttonAjoutCarte.setPeutCr�erCarte(false);
		this.buttonsSynchronisation.buttonAjoutDossier.setPeutCr�erDossier(true);
		this.buttonsSynchronisation.buttonSupprimer.setPeutSupprimer(false);
	}
}

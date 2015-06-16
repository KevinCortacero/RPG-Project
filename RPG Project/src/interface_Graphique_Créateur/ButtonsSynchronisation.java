package interface_Graphique_Cr�ateur;

import java.awt.Toolkit;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ButtonsSynchronisation extends JPanel{
	
	protected ButtonAjoutCarte buttonAjoutCarte;
	protected ButtonAjoutDossier buttonAjoutDossier;
	protected ButtonSupprimer buttonSupprimer;
	
	public ButtonsSynchronisation(){
		super();
		this.setLayout(null);
		this.setBounds(5, Toolkit.getDefaultToolkit().getScreenSize().height-400, 170, 112);
		this.buttonAjoutCarte = new ButtonAjoutCarte(this);
		this.buttonAjoutDossier = new ButtonAjoutDossier(this);
		this.buttonSupprimer = new ButtonSupprimer(this);
		this.add(this.buttonAjoutCarte);
		this.add(this.buttonAjoutDossier);
		this.add(this.buttonSupprimer);
	}
}

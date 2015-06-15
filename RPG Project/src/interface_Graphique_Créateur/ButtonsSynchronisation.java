package interface_Graphique_Créateur;

import java.awt.Component;

@SuppressWarnings("serial")
public class ButtonsSynchronisation extends Component{
	
	protected ButtonAjoutCarte buttonAjoutCarte;
	protected ButtonAjoutDossier buttonAjoutDossier;
	protected ButtonSupprimer buttonSupprimer;
	
	public ButtonsSynchronisation(){
		super();
		this.buttonAjoutCarte = new ButtonAjoutCarte();
		this.buttonAjoutDossier = new ButtonAjoutDossier();
		this.buttonSupprimer = new ButtonSupprimer();
	}
}

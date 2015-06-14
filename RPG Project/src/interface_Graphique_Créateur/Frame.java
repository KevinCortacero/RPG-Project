package interface_Graphique_Créateur;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame{
	
	private PanelPrincipalCréateur panelPrincipal;

	public Frame(){
		super("Création de niveau");
		this.setSize(new DimensionsCréateurNiveau());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.panelPrincipal = new PanelPrincipalCréateur();
		this.add(this.panelPrincipal,NORMAL);
	}
}

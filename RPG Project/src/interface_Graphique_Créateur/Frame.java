package interface_Graphique_Cr�ateur;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame{
	
	private PanelPrincipalCr�ateur panelPrincipal;

	public Frame(){
		super("Cr�ation de niveau");
		this.setSize(new DimensionsCr�ateurNiveau());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.panelPrincipal = new PanelPrincipalCr�ateur();
		this.add(this.panelPrincipal,NORMAL);
	}
}

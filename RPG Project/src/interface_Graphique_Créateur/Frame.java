package interface_Graphique_Créateur;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame{
	
	private DimensionEcran dimensionEcran;
	private PanelPrincipalCréateur panelPrincipal;
	private PanelChoixObjetsCréateur panelChoixObjets;

	public Frame(){
		super("Création de niveau");
		this.dimensionEcran = new DimensionEcran();
		this.setSize(this.dimensionEcran.getSize());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		
		
		this.panelPrincipal = new PanelPrincipalCréateur(this.dimensionEcran);
	//	this.panelChoixObjets = new PanelChoixObjetsCréateur(this.dimensionEcran);
		
		this.add(this.panelPrincipal);
		//this.add(this.panelChoixObjets);
	}
}

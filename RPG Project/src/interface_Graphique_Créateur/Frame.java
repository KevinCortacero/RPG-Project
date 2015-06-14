package interface_Graphique_Cr�ateur;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame{
	
	private DimensionEcran dimensionEcran;
	private PanelPrincipalCr�ateur panelPrincipal;
	private PanelChoixObjetsCr�ateur panelChoixObjets;

	public Frame(){
		super("Cr�ation de niveau");
		this.dimensionEcran = new DimensionEcran();
		this.setSize(this.dimensionEcran.getSize());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		
		
		this.panelPrincipal = new PanelPrincipalCr�ateur(this.dimensionEcran);
	//	this.panelChoixObjets = new PanelChoixObjetsCr�ateur(this.dimensionEcran);
		
		this.add(this.panelPrincipal);
		//this.add(this.panelChoixObjets);
	}
}

package interface_Graphique_Créateur;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelPrincipalCréateur extends JPanel{
	
	public PanelPrincipalCréateur(DimensionEcran dimensionEcran){
		super(null);
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1),	
					   BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), 			
					   BorderFactory.createEmptyBorder(10, 10, 10, 10))));
		this.setBackground(Color.GRAY);
		this.setBounds(200, 200,dimensionEcran.width - 200 , dimensionEcran.height - 200);
	}
}

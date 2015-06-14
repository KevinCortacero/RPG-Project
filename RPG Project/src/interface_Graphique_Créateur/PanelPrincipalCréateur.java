package interface_Graphique_Cr�ateur;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelPrincipalCr�ateur extends JPanel{
	
	public PanelPrincipalCr�ateur(DimensionEcran dimensionEcran){
		super(null);
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1),	
					   BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), 			
					   BorderFactory.createEmptyBorder(10, 10, 10, 10))));
		this.setBackground(Color.GRAY);
		this.setBounds(200, 200,dimensionEcran.width - 200 , dimensionEcran.height - 200);
	}
}

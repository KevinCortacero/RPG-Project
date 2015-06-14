package interface_Graphique_Créateur;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelCréateur extends JPanel{
	
	public PanelCréateur(DimensionEcran dimensionEcran){
		super(null);
		this.setLayout(null);
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1),	
					   BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), 			
					   BorderFactory.createEmptyBorder(10, 10, 10, 10))));
		this.setBackground(new Color(220,220,220));
		this.setBounds(0, 0,dimensionEcran.width, dimensionEcran.height);
		this.add(new PanelChoixObjetsCréateur(dimensionEcran));
	}
}

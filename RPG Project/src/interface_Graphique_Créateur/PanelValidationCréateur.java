package interface_Graphique_Créateur;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelValidationCréateur extends JPanel{

	public PanelValidationCréateur(){
		super(null);
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0),	
					   BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), 			
					   BorderFactory.createEmptyBorder(10, 10, 10, 10))));
		this.setBackground(new Color(250,250,250));
		this.setBounds(10, 10, 180 , 180);
	}
}

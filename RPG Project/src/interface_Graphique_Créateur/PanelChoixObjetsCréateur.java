package interface_Graphique_Cr�ateur;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelChoixObjetsCr�ateur extends JPanel{

	public PanelChoixObjetsCr�ateur(){
		super(null);
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),	
					   BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), 			
					   BorderFactory.createEmptyBorder(10, 10, 10, 10))));
		this.setBackground(Color.BLACK);
		this.setBounds(200, 10, 180 , 180);
	}
}

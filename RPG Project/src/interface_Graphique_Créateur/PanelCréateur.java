package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelCréateur extends JPanel{
	
	public PanelCréateur(){
		super(null);
		this.setLayout(null);
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0),	
					   BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), 			
					   BorderFactory.createEmptyBorder(10, 10, 10, 10))));
		this.setBackground(new Color(220,220,220));
		this.setBounds(0, 0,Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		this.add(new PanelChoixObjetsCréateur());
		this.add(new PanelValidationCréateur());
		this.add(new PanelGestionCréateur());
	}
}

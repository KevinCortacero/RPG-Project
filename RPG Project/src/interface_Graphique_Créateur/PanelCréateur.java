package interface_Graphique_Cr�ateur;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelCr�ateur extends JPanel{
	
	public PanelCr�ateur(){
		super(null);
		this.setLayout(null);
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0),	
					   BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), 			
					   BorderFactory.createEmptyBorder(10, 10, 10, 10))));
		this.setBackground(new Color(220,220,220));
		this.setBounds(0, 0,Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		this.add(new PanelChoixObjetsCr�ateur());
		this.add(new PanelValidationCr�ateur());
		this.add(new PanelGestionCr�ateur());
	}
}

package interface_Graphique_Cr�ateur;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelGestionCr�ateur extends JPanel{

	public PanelGestionCr�ateur(){
		super(null);
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0),	
					   BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), 			
					   BorderFactory.createEmptyBorder(10, 10, 10, 10))));
		this.setBackground(new Color(250,250,250));
		this.setBounds(10, 200, 180 , Toolkit.getDefaultToolkit().getScreenSize().height - 270);
	}
}

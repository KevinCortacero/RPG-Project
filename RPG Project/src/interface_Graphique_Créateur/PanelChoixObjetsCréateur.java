package interface_Graphique_Cr�ateur;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelChoixObjetsCr�ateur extends JPanel{

	public PanelChoixObjetsCr�ateur(){
		super(null);
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0),	
					   BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE), 			
					   BorderFactory.createEmptyBorder(10, 10, 10, 10))));
		this.setBackground(new Color(250,250,250));
		this.setBounds(200, 10, Toolkit.getDefaultToolkit().getScreenSize().width - 210 , 180);
	}
}

package interface_Graphique_Créateur;

import java.awt.Color;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelValidationCréateur extends JPanel{

	public PanelValidationCréateur(){
		super(null);
		this.setBorder(new BorderGray(1));
		this.setBackground(new Color(250,250,250));
		this.setBounds(10, 10, 180 , 180);
	}
}

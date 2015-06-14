package interface_Graphique_Créateur;

import java.awt.Color;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelValidationCréateur extends JPanel{

	public PanelValidationCréateur(){
		super(null);
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(10, 10, 180 , 180);
	}
}

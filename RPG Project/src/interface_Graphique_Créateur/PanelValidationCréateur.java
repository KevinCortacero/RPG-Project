package interface_Graphique_Cr�ateur;

import java.awt.Color;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelValidationCr�ateur extends JPanel{

	public PanelValidationCr�ateur(){
		super(null);
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(10, 10, 180 , 180);
	}
}

package interface_Graphique_Cr�ateur;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelGestionCr�ateur extends JPanel{

	public PanelGestionCr�ateur(){
		super();
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(10, 200, 180 , Toolkit.getDefaultToolkit().getScreenSize().height - 270);
	}
}

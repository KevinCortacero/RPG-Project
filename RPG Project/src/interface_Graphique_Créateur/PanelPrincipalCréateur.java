package interface_Graphique_Cr�ateur;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelPrincipalCr�ateur extends JPanel{

	public PanelPrincipalCr�ateur(){
		super();
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(200,200, Toolkit.getDefaultToolkit().getScreenSize().width - 210 , Toolkit.getDefaultToolkit().getScreenSize().height - 270);
	}
}

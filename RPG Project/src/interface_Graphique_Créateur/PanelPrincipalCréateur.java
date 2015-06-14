package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelPrincipalCréateur extends JPanel{

	public PanelPrincipalCréateur(){
		super(null);
		this.setBorder(new BorderGray(1));
		this.setBackground(new Color(250,250,250));
		this.setBounds(200,200, Toolkit.getDefaultToolkit().getScreenSize().width - 210 , Toolkit.getDefaultToolkit().getScreenSize().height - 270);
	}
}

package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelCréateur extends JPanel{
	
	public PanelCréateur(){
		super(null);
		this.setLayout(null);
		this.setBackground(new Color(220,220,220));
		this.setBounds(0, 0,Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		this.add(new PanelChoixObjetsCréateur());
		this.add(new PanelValidationCréateur());
		this.add(new PanelGestionCréateur());
		this.add(new PanelPrincipalCréateur());
	}
}

package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelValidationCréateur extends JPanel{
	
	public PanelValidationCréateur(Map map){
		super();
		this.setLayout(new GridLayout(3,1));
		this.add(new ButtonEnregistrer(map));
		this.add(new ButtonSaveAndQuit(map));
		this.add( new ButtonQuit());
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(10, 10, 180 , 180);
	}
}

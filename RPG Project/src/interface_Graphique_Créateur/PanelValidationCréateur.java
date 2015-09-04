package interface_Graphique_Cr�ateur;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelValidationCr�ateur extends JPanel{
	
	public PanelValidationCr�ateur(Map map){
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

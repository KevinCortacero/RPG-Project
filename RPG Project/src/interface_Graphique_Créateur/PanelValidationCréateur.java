package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelValidationCréateur extends JPanel{

	private JButton enregister;
	private JButton enregistrerEtQuitter;
	
	public PanelValidationCréateur(){
		super();
		this.setLayout(new GridLayout(3,1));
		this.enregister = new JButton("Enregistrer");
		this.enregistrerEtQuitter = new JButton("Enregistrer et Quitter");
		this.add(this.enregister);
		this.add(this.enregistrerEtQuitter);
		this.add( new ButtonQuit());
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(10, 10, 180 , 180);
	}
}

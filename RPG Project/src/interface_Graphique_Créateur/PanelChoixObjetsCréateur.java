package interface_Graphique_Cr�ateur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelChoixObjetsCr�ateur extends JPanel{

	private JLabel titre;
	
	public PanelChoixObjetsCr�ateur(){
		super();
		this.setLayout(new GridLayout());
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(200, 10, Toolkit.getDefaultToolkit().getScreenSize().width - 210 , 180);
		this.titre = new JLabel("S�lectionner un objet pour le placer sur la carte actuelle");
		this.titre.setFont(new Font("Arial", 18,18));
		this.add(this.titre);
	}
}

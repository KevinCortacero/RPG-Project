package interface_Graphique_Cr�ateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;

import jeu.H�ros;

@SuppressWarnings("serial")
public class PanelGestionCr�ateur extends JPanel{

	private JLabel titre;

	public PanelGestionCr�ateur(){
		super();
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(10, 200, 180 , Toolkit.getDefaultToolkit().getScreenSize().height - 270);
		this.titre = new JLabel("Gestion des cartes");
		this.titre.setFont(new Font("Arial", 18,18));
		this.add(this.titre);
		
		Object[] h�ros = new H�ros[10];
		h�ros[0]  = new H�ros(0,0,0);
		this.add(new JTree(h�ros));
	}
}

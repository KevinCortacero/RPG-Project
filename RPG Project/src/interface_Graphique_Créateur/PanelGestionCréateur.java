package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;

import jeu.Héros;

@SuppressWarnings("serial")
public class PanelGestionCréateur extends JPanel{

	private JLabel titre;

	public PanelGestionCréateur(){
		super();
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(10, 200, 180 , Toolkit.getDefaultToolkit().getScreenSize().height - 270);
		this.titre = new JLabel("Gestion des cartes");
		this.titre.setFont(new Font("Arial", 18,18));
		this.add(this.titre);
		
		Object[] héros = new Héros[10];
		héros[0]  = new Héros(0,0,0);
		this.add(new JTree(héros));
	}
}

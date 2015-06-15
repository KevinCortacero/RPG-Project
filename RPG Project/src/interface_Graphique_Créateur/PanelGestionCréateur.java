package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import jeu.LieuDeCarte;

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
		
		List<LieuDeCarte> cartes = new ArrayList<LieuDeCarte>();
		cartes.add(new LieuDeCarte(1,0,0));
		cartes.add(new LieuDeCarte(2,0,0));
		cartes.add(new LieuDeCarte(3,0,0));
		this.add(new TreeCarte(cartes).getTree());
	}
}

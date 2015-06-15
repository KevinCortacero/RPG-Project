package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import jeu.Carte;
import jeu.TypeDeCarte;

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
		
		Map<String,TypeDeCarte> cartes = new HashMap<String,TypeDeCarte>();
		cartes.put("Base",new TypeDeCarte("Base"));
		cartes.put("Village",new TypeDeCarte("Village"));
		cartes.put("Level",new TypeDeCarte("Level"));
		cartes.get(0).addCarte(new Carte("Base", "Maison", cartes.get(0).getListeCarte().get(0).getListeCarte().size() +1));
		this.add(new TreeCarte(cartes).getTree());
	}
}

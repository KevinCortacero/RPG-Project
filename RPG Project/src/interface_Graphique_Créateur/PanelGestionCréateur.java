package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelGestionCréateur extends JPanel {

	private JLabel titre;
	private ArbreCartes arbre;
	private ModelArbreCarte model;
	private ButtonsSynchronisation boutons;
	
	public PanelGestionCréateur(Map map) {
		super();
		this.setLayout(null);
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245, 245, 245));
		this.setBounds(10, 200, 180, Toolkit.getDefaultToolkit().getScreenSize().height - 270);
		this.boutons = new ButtonsSynchronisation();
		this.boutons.setBounds(5, (int)this.getSize().getHeight()-120,170, 120);
		this.add(this.boutons);
		this.titre = new JLabel("Gestion des cartes");
		this.titre.setFont(new Font("Arial", 18, 18));
		this.titre.setBounds(10, 10, 160, 40);
		this.add(this.titre);
		this.model = new ModelArbreCarte(new File("cartes"));
		this.arbre = new ArbreCartes(this.boutons, model, map);
		this.add(this.arbre);
	}
	
	public ArbreCartes getArbre() {
		return arbre;
	}
	
	public void raffraichir(){
		int coordonésY;
		if ( (int)this.getSize().getHeight()-120 > 150 ){
			coordonésY = (int)this.getSize().getHeight()-120;
		}else{
			coordonésY = 150 ;
		}
		this.boutons.repaint();
		this.boutons.setBounds(5,coordonésY,170, 112);
		this.arbre.setBounds(5, 50, 170, this.getHeight() - this.boutons.getHeight() - 66);	
	}
}

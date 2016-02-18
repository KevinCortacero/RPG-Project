package application.interface_Graphique_Cr�ateur.PanelGestion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JLabel;

import application.interface_Graphique_Cr�ateur.BorderGray;
import application.interface_Graphique_Cr�ateur.FrameCr�ateur;
import application.interface_Graphique_Cr�ateur.SousPanel;

@SuppressWarnings("serial")
public class PanelGestionCr�ateur extends SousPanel {

	private JLabel titre;
	private ArbreCartes arbre;
	private ModelArbreCarte model;
	private ButtonsSynchronisation boutons;

	private static PanelGestionCr�ateur instance;
	
	public static PanelGestionCr�ateur getPanel(){
		if (instance == null){
			instance = new PanelGestionCr�ateur();
			instance.setBounds(10, 200, 180, FrameCr�ateur.getFrame().getHeight() - 270 );
		}
		return instance;
	}
	
	private PanelGestionCr�ateur() {
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
		this.arbre = new ArbreCartes(this.boutons, this.model);
		this.arbre.setCellRenderer(new MyTreeCellRenderer());
		this.add(this.arbre);
	}

	public ArbreCartes getArbre() {
		return arbre;
	}

	public void raffraichir(){}
}

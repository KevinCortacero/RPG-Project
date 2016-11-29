package application.interface_Graphique_Créateur.PanelGestion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JLabel;

import application.interface_Graphique_Créateur.BorderGray;
import application.interface_Graphique_Créateur.FrameCréateur;
import application.interface_Graphique_Créateur.SousPanel;

@SuppressWarnings("serial")
public class LevelManager extends SousPanel {

	private GameLevelTree arbre;
	private ModelArbreCarte model;
	private ButtonsSynchronisation boutons;

	private static LevelManager instance;
	
	public static LevelManager getPanel(){
		if (instance == null){
			instance = new LevelManager();
			instance.setBounds(10, 200, 180, FrameCréateur.getFrame().getHeight() - 270 );
		}
		return instance;
	}
	
	private LevelManager() {
		super();
		this.setLayout(null);
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245, 245, 245));
		this.setBounds(10, 200, 180, Toolkit.getDefaultToolkit().getScreenSize().height - 270);
		this.boutons = new ButtonsSynchronisation();
		this.boutons.setBounds(5, (int)this.getSize().getHeight()-120,170, 120);
		this.add(this.boutons);
		JLabel title = new JLabel("LEVEL MANAGER");
		title.setFont(new Font("Arial", 18, 18));
		title.setBounds(10, 10, 160, 40);
		this.add(title);
		this.model = new ModelArbreCarte(new File("levels"));
		this.arbre = new GameLevelTree(this.boutons, this.model);
		this.arbre.setCellRenderer(new MyTreeCellRenderer());
		this.add(this.arbre);
	}

	public GameLevelTree getArbre() {
		return arbre;
	}

	public void raffraichir(){}
}

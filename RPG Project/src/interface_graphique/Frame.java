package interface_graphique;

import javax.swing.JFrame;

public class Frame extends JFrame{

	public Frame(){
		super("Création de niveau");
		this.setSize(new DimensionsCréateurNiveau());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
}

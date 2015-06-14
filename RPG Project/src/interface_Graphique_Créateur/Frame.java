package interface_Graphique_Créateur;

import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame{

	public Frame(){
		super("Création de niveau");
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.add(new PanelCréateur());
	}
}

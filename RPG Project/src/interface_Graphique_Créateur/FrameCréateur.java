package interface_Graphique_Créateur;

import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FrameCréateur extends JFrame{

	public FrameCréateur(){
		super("Création de niveau");
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.add(new PanelCréateur());
	}
}

package interface_Graphique_Cr�ateur;

import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame{

	public Frame(){
		super("Cr�ation de niveau");
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.add(new PanelCr�ateur());
	}
}

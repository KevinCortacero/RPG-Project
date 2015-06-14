package interface_Graphique_Créateur;

import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FrameCréateur extends JFrame{

	public FrameCréateur(){
		super("Création de niveau");
		this.setLayout(null);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		
		this.getContentPane().add(new PanelPrincipalCréateur());
		this.getContentPane().add(new PanelGestionCréateur());	
		this.getContentPane().add(new PanelChoixObjetsCréateur());
		this.getContentPane().add(new PanelValidationCréateur());
	}
}

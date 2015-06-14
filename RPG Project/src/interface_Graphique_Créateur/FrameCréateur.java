package interface_Graphique_Cr�ateur;

import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FrameCr�ateur extends JFrame{

	public FrameCr�ateur(){
		super("Cr�ation de niveau");
		this.setLayout(null);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		
		this.getContentPane().add(new PanelPrincipalCr�ateur());
		this.getContentPane().add(new PanelGestionCr�ateur());	
		this.getContentPane().add(new PanelChoixObjetsCr�ateur());
		this.getContentPane().add(new PanelValidationCr�ateur());
	}
}

package interface_Graphique_Cr�ateur;

import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class FrameCr�ateur extends JFrame{

	public FrameCr�ateur(){
		super("Cr�ation de niveau");
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.add(new PanelCr�ateur());
	}
}

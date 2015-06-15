package interface_Graphique_Cr�ateur;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class FrameCr�ateur extends JFrame{

	public FrameCr�ateur(){
		super("Cr�ation de niveau");
		this.setLayout(null);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);

		this.getContentPane().add(new PanelPrincipalCr�ateur());
		this.getContentPane().add(new PanelGestionCr�ateur());	
		this.getContentPane().add(new PanelChoixObjetsCr�ateur());
		this.getContentPane().add(new PanelValidationCr�ateur());

		addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				fermer();
			}
		});}

	private void fermer() {
		int reponse = JOptionPane.showConfirmDialog(this,
                "Voulez-vous quitter le cr�ateur de cartes sans sauvegarder ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
		if(reponse == JOptionPane.YES_OPTION ){
			dispose();
		}

	}


}

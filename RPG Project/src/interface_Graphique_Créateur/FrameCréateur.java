package interface_Graphique_Créateur;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class FrameCréateur extends JFrame{

	public FrameCréateur(){
		super("Création de niveau");
		this.setLayout(null);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);

		this.getContentPane().add(new PanelPrincipalCréateur());
		this.getContentPane().add(new PanelGestionCréateur());	
		this.getContentPane().add(new PanelChoixObjetsCréateur());
		this.getContentPane().add(new PanelValidationCréateur());

		addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				fermer();
			}
		});}

	private void fermer() {
		int reponse = JOptionPane.showConfirmDialog(this,
                "Voulez-vous quitter le créateur de cartes sans sauvegarder ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
		if(reponse == JOptionPane.YES_OPTION ){
			dispose();
		}

	}


}

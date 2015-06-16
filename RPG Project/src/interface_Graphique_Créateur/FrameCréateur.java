package interface_Graphique_Cr�ateur;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class FrameCr�ateur extends JFrame{
	PanelChoixObjetsCr�ateur panelChoixObjetsCr�ateur; 
	PanelGestionCr�ateur panelGestionCr�ateur;
	PanelPrincipalCr�ateur panelPrincipalCr�ateur;
	
	public FrameCr�ateur(){
		super("Cr�ation de niveau");
		this.setLayout(null);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		
		panelPrincipalCr�ateur = new PanelPrincipalCr�ateur();
		panelPrincipalCr�ateur.setBounds(200, 200, this.getWidth() - 230, this.getHeight() - 270);
		this.getContentPane().add(panelPrincipalCr�ateur);
		
		panelGestionCr�ateur = new PanelGestionCr�ateur();
		panelGestionCr�ateur.setBounds(10, 200, 180, this.getHeight() - 270 );
		this.getContentPane().add(panelGestionCr�ateur);
		
		panelChoixObjetsCr�ateur =new PanelChoixObjetsCr�ateur();
		panelChoixObjetsCr�ateur.setBounds(200, 10 , this.getWidth() - 230, 200 );
		this.getContentPane().add(panelChoixObjetsCr�ateur);
		
		PanelValidationCr�ateur panelValidationCr�ateur =new PanelValidationCr�ateur();
		this.getContentPane().add(panelValidationCr�ateur);

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
	
	public void raffraichir(){
		// permet d'ancrer les composants
		this.panelGestionCr�ateur.setBounds(10, 200, 180, this.getHeight() - 270 );
		this.panelPrincipalCr�ateur.setBounds(200, 200, this.getWidth() - 230, this.getHeight() - 270 );
		this.panelChoixObjetsCr�ateur.setBounds(200, 10 , this.getWidth() - 230, 180 );
		this.panelGestionCr�ateur.raffraichir();
	}
}

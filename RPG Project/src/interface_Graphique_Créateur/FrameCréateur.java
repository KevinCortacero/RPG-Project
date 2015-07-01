package interface_Graphique_Créateur;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class FrameCréateur extends JFrame{
	
	PanelChoixObjetsCréateur panelChoixObjetsCréateur; 
	PanelGestionCréateur panelGestionCréateur;
	PanelPrincipalCréateur panelPrincipalCréateur;
	PanelValidationCréateur panelValidationCréateur;
	
	public FrameCréateur() throws IOException{
		super("Création de niveau");
		this.setLayout(null);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		
		this.panelPrincipalCréateur = new PanelPrincipalCréateur();
		this.panelPrincipalCréateur.setBounds(200, 200, this.getWidth() - 230, this.getHeight() - 270);
		this.getContentPane().add(panelPrincipalCréateur);
		
		this.panelGestionCréateur = new PanelGestionCréateur(this.panelPrincipalCréateur.getMap());
		this.panelGestionCréateur.setBounds(10, 200, 180, this.getHeight() - 270 );
		this.getContentPane().add(panelGestionCréateur);
		
		this.panelChoixObjetsCréateur = new PanelChoixObjetsCréateur(this.panelPrincipalCréateur);
		this.panelChoixObjetsCréateur.setBounds(200, 10 , this.getWidth() - 230, 200 );
		this.getContentPane().add(panelChoixObjetsCréateur);
		
		this.panelValidationCréateur = new PanelValidationCréateur(this.panelPrincipalCréateur.getMap());
		this.getContentPane().add(this.panelValidationCréateur);

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
			this.setEnabled(false);
			dispose();
		}
	}
	
	public void raffraichir(){
		// permet d'ancrer les composants
		this.panelGestionCréateur.setBounds(10, 200, 180, this.getHeight() - 270 );
		this.panelPrincipalCréateur.setBounds(200, 200, this.getWidth() - 230, this.getHeight() - 270 );
		this.panelChoixObjetsCréateur.setBounds(200, 10 , this.getWidth() - 230, 180 );
		this.panelGestionCréateur.raffraichir();
	}
}

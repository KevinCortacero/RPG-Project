package interface_Graphique_Cr�ateur;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class FrameCr�ateur extends JFrame implements KeyListener{

	private PanelChoixObjetsCr�ateur panelChoixObjetsCr�ateur; 
	private PanelGestionCr�ateur panelGestionCr�ateur;
	private PanelPrincipalCr�ateur panelPrincipalCr�ateur;
	private PanelValidationCr�ateur panelValidationCr�ateur;

	public FrameCr�ateur() throws IOException{
		super("Cr�ation de niveau");
		this.setLayout(null);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);

		this.panelPrincipalCr�ateur = PanelPrincipalCr�ateur.getPanel();
		this.panelPrincipalCr�ateur.setBounds(200, 200, this.getWidth() - 230, this.getHeight() - 270);


		this.panelChoixObjetsCr�ateur = PanelChoixObjetsCr�ateur.getPanel();
		this.panelChoixObjetsCr�ateur.setBounds(200, 10 , this.getWidth() - 230, 200 );
		
		this.getContentPane().add(panelPrincipalCr�ateur);
		this.getContentPane().add(panelChoixObjetsCr�ateur);

		this.panelGestionCr�ateur = new PanelGestionCr�ateur(this.panelPrincipalCr�ateur);
		this.panelGestionCr�ateur.setBounds(10, 200, 180, this.getHeight() - 270 );
		this.getContentPane().add(panelGestionCr�ateur);
		this.panelPrincipalCr�ateur.getMap().mapFile.chargerCarteActuelle();
		this.panelValidationCr�ateur = new PanelValidationCr�ateur(this.panelPrincipalCr�ateur.getMap());
		this.getContentPane().add(this.panelValidationCr�ateur);

		addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				fermer();
			}
		});
		this.setFocusable(true);
		this.addKeyListener(this);
	}

	private void fermer() {
		int reponse = JOptionPane.showConfirmDialog(this,
				"Voulez-vous quitter le cr�ateur de cartes sans sauvegarder ?",
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
		this.panelGestionCr�ateur.setBounds(10, 200, 180, this.getHeight() - 270 );
		this.panelPrincipalCr�ateur.setBounds(200, 200, ((int) ((this.getWidth()-210) / ObjetIcone.tailleImageJeu)) * ObjetIcone.tailleImageJeu, ((int) ((this.getHeight() -220) / ObjetIcone.tailleImageJeu)) * ObjetIcone.tailleImageJeu);
		this.panelChoixObjetsCr�ateur.setBounds(200, 10 , this.getWidth() - 230, 180 );
		this.panelChoixObjetsCr�ateur.raffraichir();
		this.panelGestionCr�ateur.raffraichir();
		this.panelPrincipalCr�ateur.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
		case KeyEvent.VK_S :
			Origin.setY(Origin.getY()-3.5F);
			break;
		case KeyEvent.VK_Z :
			if ( Origin.getY()<0)
			Origin.setY(Origin.getY()+3.5F);
			break;
		case KeyEvent.VK_D :
			Origin.setX(Origin.getX()-3.5F);
			break;
		case KeyEvent.VK_Q :
			if ( Origin.getX()<0)
			Origin.setX(Origin.getX()+3.5F);
			break;	
		}
		this.raffraichir();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}

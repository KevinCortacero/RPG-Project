package interface_Graphique_Créateur;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class FrameCréateur extends JFrame implements KeyListener{

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


		this.panelChoixObjetsCréateur = new PanelChoixObjetsCréateur(this.panelPrincipalCréateur);
		this.panelChoixObjetsCréateur.setBounds(200, 10 , this.getWidth() - 230, 200 );
		this.panelPrincipalCréateur.getMap().setPanel(this.panelChoixObjetsCréateur);
		this.getContentPane().add(panelPrincipalCréateur);
		this.getContentPane().add(panelChoixObjetsCréateur);

		this.panelGestionCréateur = new PanelGestionCréateur(this.panelPrincipalCréateur);
		this.panelGestionCréateur.setBounds(10, 200, 180, this.getHeight() - 270 );
		this.getContentPane().add(panelGestionCréateur);
		this.panelPrincipalCréateur.getMap().mapFile.chargerCarteActuelle();

		//ascenseur
		/*JScrollPane ascenseurs = new JScrollPane(this.panelPrincipalCréateur);
		ascenseurs.setPreferredSize(this.panelPrincipalCréateur.getSize());
		this.add(ascenseurs);*/

		this.panelValidationCréateur = new PanelValidationCréateur(this.panelPrincipalCréateur.getMap());
		this.getContentPane().add(this.panelValidationCréateur);

		addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				fermer();
			}
		});
		this.setFocusable(true);
		this.addKeyListener(this);
		//this.panelPrincipalCréateur.setFocusable(true);
		//this.panelPrincipalCréateur.addKeyListener(this);;
	}

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
		this.panelPrincipalCréateur.setBounds(200, 200, ((int) ((this.getWidth()-210) / ObjetIcone.tailleImageJeu)) * ObjetIcone.tailleImageJeu, ((int) ((this.getHeight() -220) / ObjetIcone.tailleImageJeu)) * ObjetIcone.tailleImageJeu);
		this.panelChoixObjetsCréateur.setBounds(200, 10 , this.getWidth() - 230, 180 );
		this.panelChoixObjetsCréateur.raffraichir();
		this.panelGestionCréateur.raffraichir();
		this.panelPrincipalCréateur.repaint();
		System.out.println(this.isFocusable());
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
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

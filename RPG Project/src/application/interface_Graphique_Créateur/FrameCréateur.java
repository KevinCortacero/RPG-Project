package application.interface_Graphique_Cr�ateur;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import application.fonction.Parametres;
import application.interface_Graphique_Cr�ateur.PanelGestion.PanelGestionCr�ateur;
import application.interface_Graphique_Cr�ateur.PanelObjets.PanelChoixObjetsCr�ateur;
import application.interface_Graphique_Cr�ateur.PanelPrincipal.PanelPrincipalCr�ateur;
import application.interface_Graphique_Cr�ateur.PanelValidation.PanelValidationCr�ateur;

@SuppressWarnings("serial")
public class FrameCr�ateur extends JFrame implements KeyListener{

	private static FrameCr�ateur instance;
	private List<SousPanel> liste;

	public static FrameCr�ateur getFrame(){
		if (instance == null){
			instance = new FrameCr�ateur();
			instance.ajouterComponents();
		}
		return instance;
	}
	
	private FrameCr�ateur(){
		super("Cr�ation de niveau");
		this.setLayout(null);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setFocusable(true);
		this.addKeyListener(this);
		this.liste = new ArrayList<SousPanel>();
		addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				fermer();
			}
		});
	}
	
	private void ajouterComponents(){
		this.liste.add(PanelPrincipalCr�ateur.getPanel());
		this.liste.add(PanelChoixObjetsCr�ateur.getPanel());
		this.liste.add(PanelGestionCr�ateur.getPanel());
		this.liste.add(PanelValidationCr�ateur.getPanel());
		for (JPanel panel : this.liste){
			this.getContentPane().add(panel);
		}
	}	

	private void fermer() {
		int reponse = JOptionPane.showConfirmDialog(this,
				"Voulez-vous quitter le cr�ateur de cartes sans sauvegarder ?",
				"Confirmation",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if(reponse == JOptionPane.YES_OPTION ){
			this.setEnabled(false);
			this.dispose();
		}
	}

	public void raffraichir(){
		for (SousPanel panel : this.liste){
			panel.raffraichir();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
		case KeyEvent.VK_S :
			Origin.setY(Origin.getY()-Parametres.VITESSE_DEPLACEMENT);
			break;
		case KeyEvent.VK_Z :
			if ( Origin.getY()<0)
			Origin.setY(Origin.getY()+Parametres.VITESSE_DEPLACEMENT);
			break;
		case KeyEvent.VK_D :
			Origin.setX(Origin.getX()-Parametres.VITESSE_DEPLACEMENT);
			break;
		case KeyEvent.VK_Q :
			if ( Origin.getX()<0)
			Origin.setX(Origin.getX()+Parametres.VITESSE_DEPLACEMENT);
			break;	
		}
		this.raffraichir();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}

package application.interface_Graphique_Cr�ateur;

import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import application.interface_Graphique_Cr�ateur.PanelGestion.LevelManager;
import application.interface_Graphique_Cr�ateur.PanelObjets.PanelChoixObjetsCr�ateur;
import application.interface_Graphique_Cr�ateur.PanelPrincipal.LevelDesigner;
import application.interface_Graphique_Cr�ateur.PanelValidation.PanelValidationCr�ateur;

@SuppressWarnings("serial")
public class FrameCr�ateur extends JFrame implements FocusListener{

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
		this.liste = new ArrayList<SousPanel>();
		this.addFocusListener(this);
	}

	private void ajouterComponents(){
		this.liste.add(LevelDesigner.getPanel());
		this.liste.add(PanelChoixObjetsCr�ateur.getPanel());
		this.liste.add(LevelManager.getPanel());
		this.liste.add(PanelValidationCr�ateur.getPanel());
		for (JPanel panel : this.liste){
			this.getContentPane().add(panel);
		}
		this.addKeyListener(new KeyBoard());
		this.addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				fermer();
			}
		});
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
	}
	
	public String toString(){
		return "Fen�tre principal";
	}

	@Override
	public void focusGained(FocusEvent e) {}

	@Override
	public void focusLost(FocusEvent e) {
		this.requestFocusInWindow();
	}
}

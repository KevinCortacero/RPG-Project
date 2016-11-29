package level_editor.desktop;

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

import level_editor.desktop.PanelValidation.PanelValidationCréateur;
import level_editor.desktop.level_designer.LevelDesigner;
import level_editor.desktop.level_manager.LevelManager;
import level_editor.desktop.toolbar.PanelChoixObjetsCréateur;

@SuppressWarnings("serial")
public class FrameCréateur extends JFrame implements FocusListener{

	private static FrameCréateur instance;
	private List<SousPanel> liste;

	public static FrameCréateur getFrame(){
		if (instance == null){
			instance = new FrameCréateur();
			instance.ajouterComponents();
		}
		return instance;
	}

	private FrameCréateur(){
		super("Création de niveau");
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
		this.liste.add(PanelChoixObjetsCréateur.getPanel());
		this.liste.add(LevelManager.getPanel());
		this.liste.add(PanelValidationCréateur.getPanel());
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
				"Voulez-vous quitter le créateur de cartes sans sauvegarder ?",
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
		return "Fenêtre principal";
	}

	@Override
	public void focusGained(FocusEvent e) {}

	@Override
	public void focusLost(FocusEvent e) {
		this.requestFocusInWindow();
	}
}

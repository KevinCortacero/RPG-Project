package application.interface_Graphique_Créateur.PanelValidation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import application.interface_Graphique_Créateur.PanelPrincipal.LevelDesigner;

@SuppressWarnings("serial")
public class ButtonSaveAndQuit extends JButton implements ActionListener {
	
	public ButtonSaveAndQuit(){
		super("Save and quit");
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			int reponse = JOptionPane.showConfirmDialog(this,
	                "Voulez-vous sauvegarder et quitter le créateur de cartes ?",
	                "Confirmation",
	                JOptionPane.YES_NO_OPTION,
	                JOptionPane.QUESTION_MESSAGE);
			if (reponse ==  JOptionPane.YES_NO_OPTION ){
				try {
					LevelDesigner.getPanel().getLevelContainer().getLevel().sauvegarder();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		}
	}
}

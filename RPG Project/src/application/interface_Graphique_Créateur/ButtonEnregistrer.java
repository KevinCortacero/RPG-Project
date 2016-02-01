package application.interface_Graphique_Cr�ateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import application.interface_Graphique_Cr�ateur.PanelPrincipal.LevelContainer;
import application.interface_Graphique_Cr�ateur.PanelPrincipal.PanelPrincipalCr�ateur;

@SuppressWarnings("serial")
public class ButtonEnregistrer extends JButton implements ActionListener {
	
	public ButtonEnregistrer(){
		super("Sauvegarder la carte");
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			PanelPrincipalCr�ateur.getPanel().getLevelContainer().getLevel().sauvegarder();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
}

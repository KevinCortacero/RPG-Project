package interface_Graphique_Créateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class ButtonSaveAndQuit extends JButton implements ActionListener {

	private Map map;
	
	public ButtonSaveAndQuit(Map map){
		super("Sauv. la carte et Quitter");
		this.addActionListener(this);
		this.map = map;
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
					this.map.mapFile.sauvegarder();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		}
	}
}

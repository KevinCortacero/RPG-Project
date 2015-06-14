package interface_Graphique_Créateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class ButtonQuit extends JButton implements ActionListener {

	public ButtonQuit(){
		super("Quitter");
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this){
			int reponse = JOptionPane.showConfirmDialog(this,
	                "Voulez-vous quitter le créateur de cartes sans sauvegarder ?",
	                "Confirmation",
	                JOptionPane.YES_NO_OPTION,
	                JOptionPane.QUESTION_MESSAGE);
			if (reponse ==  JOptionPane.YES_NO_OPTION )
				System.exit(0);
		}		
	}
}

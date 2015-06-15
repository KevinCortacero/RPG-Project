package interface_Graphique_Créateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonAjoutCarte extends JButton implements ActionListener {

	private boolean peutCréerCarte;
	
	public ButtonAjoutCarte(){
		super("Ajouter une Carte");
		this.setPeutCréerCarte(false);
		this.addActionListener(this);
		this.setBounds(10, 600, 160, 30);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this)
			this.setPeutCréerCarte(true);
		else
			this.setPeutCréerCarte(false);
		System.out.println(this.peutCréerCarte);
	}

	public boolean isPeutCréerCarte() {
		return peutCréerCarte;
	}

	public void setPeutCréerCarte(boolean peutCréerCarte) {
		this.peutCréerCarte = peutCréerCarte;
	}
}

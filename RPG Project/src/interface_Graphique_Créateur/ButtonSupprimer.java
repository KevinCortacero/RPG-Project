package interface_Graphique_Créateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonSupprimer extends JButton implements ActionListener {
	
	private boolean peutSupprimer;

	public ButtonSupprimer(){
		super("Supprimer");
		this.setPeutCréerCarte(false);
		this.addActionListener(this);
		this.setBounds(10, 680, 160, 30);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this)
			this.setPeutCréerCarte(true);
		else
			this.setPeutCréerCarte(false);
	}

	public boolean isPeutCréerCarte() {
		return peutSupprimer;
	}

	public void setPeutCréerCarte(boolean peutCréerCarte) {
		this.peutSupprimer = peutCréerCarte;
	}
}

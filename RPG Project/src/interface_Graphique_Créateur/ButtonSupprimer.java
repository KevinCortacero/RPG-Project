package interface_Graphique_Cr�ateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonSupprimer extends JButton implements ActionListener {
	
	private boolean peutSupprimer;

	public ButtonSupprimer(){
		super("Supprimer");
		this.setPeutCr�erCarte(false);
		this.addActionListener(this);
		this.setBounds(10, 680, 160, 30);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this)
			this.setPeutCr�erCarte(true);
		else
			this.setPeutCr�erCarte(false);
	}

	public boolean isPeutCr�erCarte() {
		return peutSupprimer;
	}

	public void setPeutCr�erCarte(boolean peutCr�erCarte) {
		this.peutSupprimer = peutCr�erCarte;
	}
}

package interface_Graphique_Cr�ateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonAjoutCarte extends JButton implements ActionListener {

	private boolean peutCr�erCarte;
	
	public ButtonAjoutCarte(){
		super("Ajouter une Carte");
		this.setPeutCr�erCarte(false);
		this.addActionListener(this);
		this.setBounds(10, 600, 160, 30);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this)
			this.setPeutCr�erCarte(true);
		else
			this.setPeutCr�erCarte(false);
		System.out.println(this.peutCr�erCarte);
	}

	public boolean isPeutCr�erCarte() {
		return peutCr�erCarte;
	}

	public void setPeutCr�erCarte(boolean peutCr�erCarte) {
		this.peutCr�erCarte = peutCr�erCarte;
	}
}

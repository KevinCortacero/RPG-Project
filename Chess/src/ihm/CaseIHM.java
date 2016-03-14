package ihm;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class CaseIHM extends JButton{

	private Color color;
	private int colonne;
	private int ligne;

	public CaseIHM(int ligne, int colonne, Color color) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.color = color;
		this.setBackground(this.color);
		super.setPreferredSize(new Dimension(100,100));
	}

	public int getColonne() {
		return this.colonne;
	}

	public int getLigne() {
		return this.ligne;
	}
}

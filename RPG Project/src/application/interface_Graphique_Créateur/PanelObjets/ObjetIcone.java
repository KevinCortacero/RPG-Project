package application.interface_Graphique_Créateur.PanelObjets;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import application.interface_Graphique_Créateur.PanelPrincipal.MapContainer;
import application.interface_Graphique_Créateur.PanelPrincipal.PanelPrincipalCréateur;
import application.jeu.ObjetCourant;


@SuppressWarnings("serial")
public class ObjetIcone extends JButton implements ActionListener {

	private Image imageTailleRéelle;
	private PanelPrincipalCréateur panel;
	private int numéro;

	public static final int tailleImageJeu = 50;
	public static final int tailleImageIcone = 46;

	public ObjetIcone(Image imageTailleRéelle,  int numéro) {
		this.setPreferredSize(new Dimension(ObjetIcone.tailleImageIcone,ObjetIcone.tailleImageIcone));
		this.setSize(ObjetIcone.tailleImageIcone,ObjetIcone.tailleImageIcone);
		this.panel = PanelPrincipalCréateur.getPanel();
		this.imageTailleRéelle = imageTailleRéelle;
		this.numéro = numéro;
		this.setIcon(new ImageIcon(this.imageTailleRéelle.getScaledInstance((int)this.getSize().getWidth(),(int) this.getSize().getHeight(),Image.SCALE_SMOOTH)));
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.numéro < 2 || this.numéro > 3){
			Image image = this.imageTailleRéelle.getScaledInstance(ObjetIcone.tailleImageJeu,ObjetIcone.tailleImageJeu, Image.SCALE_SMOOTH);
			this.panel.setObjetCourant(new ObjetCourant(image,this.numéro));
		}
		else {
			MapContainer.getMap().setBackground(this.imageTailleRéelle, this.numéro);
			this.panel.setObjetCourant(null);
			this.panel.repaint();
		}
	}
}
package application.interface_Graphique_Cr�ateur.PanelObjets;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import application.interface_Graphique_Cr�ateur.PanelPrincipal.PanelPrincipalCr�ateur;
import application.jeu.ObjetCourant;


@SuppressWarnings("serial")
public class ObjetIcone extends JButton implements ActionListener {

	private Image imageTailleR�elle;
	private PanelPrincipalCr�ateur panel;
	private int num�ro;

	public static final int tailleImageJeu = 10;
	public static final int tailleImageIcone = 30;

	public ObjetIcone(Image imageTailleR�elle,  int num�ro) {
		this.setPreferredSize(new Dimension(ObjetIcone.tailleImageIcone,ObjetIcone.tailleImageIcone));
		this.setSize(ObjetIcone.tailleImageIcone,ObjetIcone.tailleImageIcone);
		this.panel = PanelPrincipalCr�ateur.getPanel();
		this.imageTailleR�elle = imageTailleR�elle;
		this.num�ro = num�ro;
		this.setIcon(new ImageIcon(this.imageTailleR�elle.getScaledInstance((int)this.getSize().getWidth(),(int) this.getSize().getHeight(),Image.SCALE_SMOOTH)));
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.num�ro < 2 || this.num�ro > 3){
			Image image = this.imageTailleR�elle.getScaledInstance(ObjetIcone.tailleImageJeu,ObjetIcone.tailleImageJeu, Image.SCALE_SMOOTH);
			this.panel.setObjetCourant(new ObjetCourant(image,this.num�ro));
		}
		else {
			if (PanelPrincipalCr�ateur.getPanel().getLevelContainer().getLevel() != null)
				PanelPrincipalCr�ateur.getPanel().getLevelContainer().getLevel().setBackgroundNum(this.num�ro);
			this.panel.setObjetCourant(null);
		}
	}
}
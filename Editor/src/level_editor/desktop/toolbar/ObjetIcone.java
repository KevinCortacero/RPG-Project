package level_editor.desktop.toolbar;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import level_editor.desktop.ObjetCourant;
import level_editor.desktop.level_designer.LevelDesigner;


@SuppressWarnings("serial")
public class ObjetIcone extends JButton implements ActionListener {

	private Image imageTailleRéelle;
	private LevelDesigner panel;
	private int id;

	public static final int tailleImageJeu = 10;
	public static final int tailleImageIcone = 30;

	public ObjetIcone(Image imageTailleRéelle,  int id) {
		this.setPreferredSize(new Dimension(ObjetIcone.tailleImageIcone,ObjetIcone.tailleImageIcone));
		this.setSize(ObjetIcone.tailleImageIcone,ObjetIcone.tailleImageIcone);
		this.panel = LevelDesigner.getPanel();
		this.imageTailleRéelle = imageTailleRéelle;
		this.id = id;
		this.setIcon(new ImageIcon(this.imageTailleRéelle.getScaledInstance((int)this.getSize().getWidth(),(int) this.getSize().getHeight(),Image.SCALE_SMOOTH)));
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.id < 2 || this.id > 3){
			Image image = this.imageTailleRéelle.getScaledInstance(ObjetIcone.tailleImageJeu,ObjetIcone.tailleImageJeu, Image.SCALE_SMOOTH);
			this.panel.setObjetCourant(new ObjetCourant(image,this.id));
		}
		else {
			if (LevelDesigner.getPanel().getLevelContainer().getLevel() != null)
				LevelDesigner.getPanel().getLevelContainer().getLevel().setBackground(Integer.toString(this.id));
			this.panel.setObjetCourant(null);
		}
	}
}
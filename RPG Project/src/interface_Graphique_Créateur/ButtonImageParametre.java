package interface_Graphique_Créateur;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class ButtonImageParametre extends JButton {

	Image image;

	public ButtonImageParametre(Image image) {
		this.setPreferredSize(new Dimension(46,46));
		this.setSize(46,46);
		this.image = image.getScaledInstance((int)this.getSize().getWidth(),(int) this.getSize().getHeight(),
				Image.SCALE_SMOOTH);
		this.setIcon(new ImageIcon(this.image));
	}
}

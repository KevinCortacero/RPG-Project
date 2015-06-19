package interface_Graphique_Créateur;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class ButtonImageParametre extends JButton implements ActionListener {

	Image image;
	boolean isClicked;
	PanelPrincipalCréateur panel;
	public static final int tailleImageReelle = 50;
	
	public ButtonImageParametre(Image image, PanelPrincipalCréateur panel) {
		this.panel = panel;
		this.setPreferredSize(new Dimension(46,46));
		this.setSize(46,46);
		this.image = image;
		this.setIcon(new ImageIcon(this.image.getScaledInstance((int)this.getSize().getWidth(),(int) this.getSize().getHeight(),
				Image.SCALE_SMOOTH)));
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.panel.setImageCourante(new ImageIcon(this. image.getScaledInstance(ButtonImageParametre.tailleImageReelle,ButtonImageParametre.tailleImageReelle, Image.SCALE_SMOOTH)));
		
	}
}

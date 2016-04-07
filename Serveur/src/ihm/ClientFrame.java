package ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ClientFrame extends JFrame{

	public ClientFrame(String pseudo) {
		super("Multijoueur - " + pseudo);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setSize(600,600);
	}
}

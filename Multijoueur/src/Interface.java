import java.awt.Dimension;

import javax.swing.JFrame;


public class Interface extends JFrame{

	private Panel panel;
	
	public Interface(){
		
		this.setTitle("1vs1");
		this.setVisible(true);
		this.setPreferredSize(new Dimension(600, 600));
		this.getContentPane().add(new Panel());
	}
	
}

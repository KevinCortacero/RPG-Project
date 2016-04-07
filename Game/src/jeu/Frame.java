package 
jeu;

import javax.swing.JFrame;

import personnage.Héros;

public class Frame extends JFrame {

	private Panel panel = new Panel();
	
	public Frame() {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(1200, 700);
		frame.setTitle("ElemenS 1.0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setContentPane(panel);
		frame.addKeyListener(((Héros)this.panel.getJeu().getHéros()).getClavier());
		trame();
	}
	
	public void trame(){
		while(true){
			try 
			{
				Thread.sleep(8);
			} 
			catch (InterruptedException e) {}
			this.panel.getJeu().testPeutGrimper((Héros)this.panel.getJeu().getHéros(),this.panel.getJeu().getEchelle());
			((Héros)this.panel.getJeu().getHéros()).updateHéros();
			this.panel.getJeu().changementMap();
			this.panel.repaint();
		}
	}
	
	public Panel getPanel(){
		return this.panel;
	}
}

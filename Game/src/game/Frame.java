package 
game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import hero.Hero;

public class Frame extends JFrame {

	private Game game;
	
	public Frame() {
		super("ELEMENS 1.0");
		
		this.setVisible(true);
		this.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.game = new Game(dim);
		this.setSize(dim);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		JPanel pane = new JPanel();
		pane.setBackground(new Color(3,3,3));
		pane.add(this.game);
		pane.add(new JButton("HEY"));
		pane.add(new JButton("HEY"));
		pane.add(new JButton("HEY"));
		pane.add(new JButton("HEY"));
		this.add(pane);
		this.addKeyListener(((Hero)this.game.getHéros()).getClavier());
		trame();
	}
	
	public void trame(){
		while(true){
			try 
			{
				Thread.sleep(8);
			} 
			catch (InterruptedException e) {}
			this.game.testPeutGrimper((Hero)this.game.getHéros(),this.game.getEchelle());
			this.game.repaint();
		}
	}
}

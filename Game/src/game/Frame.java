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
	
		this.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(true);
		this.setVisible(true);
		JPanel pane = new JPanel();
		pane.setBackground(new Color(40,40,40));
		this.game = Game.createGame(dim);
		this.game.initHero();
		pane.add(this.game);
		pane.add(new JButton("STATISTIQUES"));
		pane.add(new JButton("STUFF"));
		pane.add(new JButton("MAP"));
		pane.add(new JButton("WORLD"));
		this.add(pane);
		KeyBoard kb = new KeyBoard(this.game.getHéros());
		this.addKeyListener(kb);
		this.validate();
		trame();
	}
	
	public void trame(){
		
		while(true){
			
			try 
			{
				Thread.sleep(1000 / 60);
				//this.game.testPeutGrimper((Hero)this.game.getHéros(),this.game.getEchelle());
				
			} 
			catch (InterruptedException e) {
				System.out.println("ERR");
			}	
			this.game.repaint();
		}
	}
}

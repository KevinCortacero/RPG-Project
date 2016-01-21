import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Panel extends JPanel{

	Personnage p1;
	Personnage p2;
	
	public Panel(){
		this.setPreferredSize(new Dimension(600, 600));
		this.p1 = new Personnage("Twarz");
		this.p2 = new Personnage("Koreuc");
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 600, 600);
		g.setColor(Color.BLUE);
		this.p1.afficherPersonnage(g);
		g.setColor(Color.RED);
		this.p2.afficherPersonnage(g);
	}
}

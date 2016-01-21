import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Panel extends JPanel{

	Personnage p1;
	Personnage p2;
	
	public Panel(){
		this.p1 = new Personnage("Twarz");
		this.p1 = new Personnage("Koreuc");
		this.repaint();
	}
	
	public void repaint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 600, 600);
		g.setColor(Color.BLUE);
		this.p1.afficherPersonnage(g);
		g.setColor(Color.RED);
		this.p2.afficherPersonnage(g);
	}
}

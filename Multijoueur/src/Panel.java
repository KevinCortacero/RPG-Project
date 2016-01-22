import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;


public class Panel extends JPanel implements KeyListener{

	Personnage p1;
	Personnage p2;
	Personnage courant;
	
	public Panel(){
		this.setPreferredSize(new Dimension(600, 600));
		this.p1 = new Personnage("Twarz");
		this.p2 = new Personnage("Koreuc");
		this.courant = this.p2; // Je choisis le perso Twarz
		this.addKeyListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 600, 600);
		g.setColor(Color.BLUE);
		this.p1.mettreAJour();
		this.p1.afficherPersonnage(g);
		g.setColor(Color.RED);
		this.p2.afficherPersonnage(g);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		
		
		switch (arg0.getKeyCode()){
		
		case KeyEvent.VK_RIGHT :
			this.courant.positionX +=2;
		break;
		
		case KeyEvent.VK_LEFT :
			this.courant.positionX -=2;
		break;
		
		case KeyEvent.VK_UP :
			this.courant.positionY -=2;
		break;
		
		case KeyEvent.VK_DOWN :
			this.courant.positionY +=2;
		break;
		}
	
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		System.out.println("Mise à jour");
		BDD.mettreAJourPersonnage(this.courant);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

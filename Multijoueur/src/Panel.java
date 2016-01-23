import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;


public class Panel extends JPanel implements KeyListener{

	private Personnage heros;
	private Map<String, Personnage> personnages;
	
	public Panel(){
		this.setPreferredSize(new Dimension(600, 600));
		this.personnages = new HashMap<String, Personnage>();
		BDD.getBDD().ajouterPersonnages(this.personnages);
		this.heros = this.personnages.get("Twarz");
		this.addKeyListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 600, 600);
		for(String pseudo : this.personnages.keySet()){
			if (this.personnages.get(pseudo).getPseudo() != this.heros.getPseudo())
				this.personnages.get(pseudo).mettreAJour();
			this.personnages.get(pseudo).afficherPersonnage(g);
		}
	}
	
	public void synchronisation(int millisecondes){
		try {
			Thread.sleep(millisecondes);
			System.out.println("Mise à jour toutes les 100ms");
			BDD.getBDD().mettreAJourPersonnage(this.heros);
			this.repaint();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

		switch (arg0.getKeyCode()){
		
		case KeyEvent.VK_RIGHT :
			this.heros.setPositionX(this.heros.getPositionX() + 2);
		break;
		
		case KeyEvent.VK_LEFT :
			this.heros.setPositionX(this.heros.getPositionX() - 2);
		break;
		
		case KeyEvent.VK_UP :
			this.heros.setPositionY(this.heros.getPositionY() - 2);
		break;
		
		case KeyEvent.VK_DOWN :
			this.heros.setPositionY(this.heros.getPositionY() + 2);
		break;
		}
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

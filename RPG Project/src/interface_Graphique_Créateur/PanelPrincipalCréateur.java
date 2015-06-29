package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JPanel;

import jeu.ObjetCourant;

@SuppressWarnings("serial")
public class PanelPrincipalCréateur extends JPanel implements MouseListener{

	private ObjetCourant objetCourant;
	private Map map;
	private int x;	
	private int y;

	public PanelPrincipalCréateur() throws IOException{
		super();
		this.x = 0;
		this.y = 0 ;
		this.map = new Map();
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(200,200, Toolkit.getDefaultToolkit().getScreenSize().width - 210 , Toolkit.getDefaultToolkit().getScreenSize().height - 270);
		this.addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.map.afficherCarte(g);
	} 

	public void setObjetCourant(ObjetCourant objetCourant){
		this.objetCourant = objetCourant;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// on récupère les coordonnées de la case ciblée
		this.x =(int)(e.getX()/ObjetIcone.tailleImageJeu);
		this.y = (int)(e.getY()/ObjetIcone.tailleImageJeu);
		
		// clic gauche
		if (e.getButton() == MouseEvent.BUTTON1){
			this.map.gestionClicGauche(this.x, this.y, this.objetCourant);
		}
		// clic droit
		if (e.getButton() == MouseEvent.BUTTON3 && this.map.getTile(this.x, this.y) != null)
			this.setObjetCourant(new ObjetCourant(this.map.getTile(this.x, this.y).getImageIcon().getImage(), this.map.getTile(this.x, this.y).getNuméro()));
		this.repaint();
	}

	public Map getMap(){
		return this.map;
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e){}
}

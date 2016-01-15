package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import jeu.ObjetCourant;

@SuppressWarnings("serial")
public class PanelPrincipalCréateur extends SousPanel implements MouseListener{

	private static PanelPrincipalCréateur instance;
	private ObjetCourant objetCourant;
	private Map map;
	private int x;	
	private int y;

	public static PanelPrincipalCréateur getPanel(){
		if (instance == null){
			try {
				instance = new PanelPrincipalCréateur();
				instance.setBounds(200, 200, FrameCréateur.getFrame().getWidth() - 230, FrameCréateur.getFrame().getHeight() - 270);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	private PanelPrincipalCréateur() throws IOException{
		super();
		this.x = 0;
		this.y = 0;
		this.map = new Map();
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(200,300, Toolkit.getDefaultToolkit().getScreenSize().width - 210 , Toolkit.getDefaultToolkit().getScreenSize().height - 270);
		this.addMouseListener(this);
	}
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.map != null){
			this.map.afficherCarte(g);
		}
		
	} 

	public void setObjetCourant(ObjetCourant objetCourant){
		this.objetCourant = objetCourant;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// on récupère les coordonnées de la case ciblée
		this.x = fonction.Util.getPixelToTileX(e.getX());
		this.y = fonction.Util.getPixelToTileY(e.getY());
		
		// clic gauche
		if (e.getButton() == MouseEvent.BUTTON1 && this.objetCourant != null){
			this.map.gestionClicGauche(this.x, this.y, this.objetCourant);
		}
		else if (e.getButton() == MouseEvent.BUTTON1 && this.objetCourant == null)
			this.map.mapFile.map.remove(this.map.mapFile.getTile(this.x, this.y));
		// clic droit
		else if (e.getButton() == MouseEvent.BUTTON3 && this.map.mapFile.getTile(this.x, this.y).getNuméro() != 0)
			this.setObjetCourant(new ObjetCourant(this.map.mapFile.getTile(this.x, this.y).getImageIcon().getImage(), this.map.mapFile.getTile(this.x, this.y).getNuméro()));
		else if (this.map.mapFile.getTile(this.x, this.y).getNuméro() == 0)
			this.setObjetCourant(null);
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

	@Override
	public void raffraichir() {
		this.setBounds(200, 200, ((int) ((FrameCréateur.getFrame().getWidth()-210) / ObjetIcone.tailleImageJeu)) * ObjetIcone.tailleImageJeu, ((int) ((FrameCréateur.getFrame().getHeight() -220) / ObjetIcone.tailleImageJeu)) * ObjetIcone.tailleImageJeu);
		this.repaint();
	}
	
}

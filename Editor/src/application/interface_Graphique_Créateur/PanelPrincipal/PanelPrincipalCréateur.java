package application.interface_Graphique_Créateur.PanelPrincipal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import application.fonction.Origine;
import application.fonction.Util;
import application.interface_Graphique_Créateur.BorderGray;
import application.interface_Graphique_Créateur.FrameCréateur;
import application.interface_Graphique_Créateur.SousPanel;
import application.jeu.ObjetCourant;

@SuppressWarnings("serial")
public class PanelPrincipalCréateur extends SousPanel implements MouseListener{

	private static PanelPrincipalCréateur instance;
	private ObjetCourant objetCourant;
	private LevelContainer levelContainer;

	public static PanelPrincipalCréateur getPanel(){
		if (instance == null){
			instance = new PanelPrincipalCréateur();
			instance.setBounds(200, 200, FrameCréateur.getFrame().getWidth() - 230, FrameCréateur.getFrame().getHeight() - 270);
		}
		return instance;
	}

	public LevelContainer getLevelContainer(){
		return this.levelContainer;
	}

	private PanelPrincipalCréateur(){
		super();
		this.levelContainer = new LevelContainer();
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(200,300, Toolkit.getDefaultToolkit().getScreenSize().width - 210 , Toolkit.getDefaultToolkit().getScreenSize().height - 270);
		this.addMouseListener(this);
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.levelContainer.getLevel() != null){
			this.levelContainer.getLevel().afficherCarte(g);
			//quadrillage
			g.setColor(Color.gray);
			for ( int i = 0 ; i<this.getSize().getWidth() ; i += 50){
				g.drawLine(i, 0, i, (int)this.getSize().getHeight());
				g.drawLine(0, i,(int)this.getSize().getWidth() , i);
			}	
		}
		else
			g.drawString("Veuillez sélectionner un level à éditer", Origine.getX()+200,Origine.getY()+200);
	} 

	public void setObjetCourant(ObjetCourant objetCourant){
		this.objetCourant = objetCourant;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (this.levelContainer.getLevel() != null){
			int x = Util.getPixelToTileX(e.getX());
			int y = Util.getPixelToTileY(e.getY());

			// clic gauche
			if (e.getButton() == MouseEvent.BUTTON1 && this.objetCourant != null){
				this.levelContainer.getLevel().gestionClicGauche(x, y, this.objetCourant);
			}
			else if (e.getButton() == MouseEvent.BUTTON1 && this.objetCourant == null)
				this.levelContainer.getLevel().getMap().remove(this.levelContainer.getLevel().getTile(x, y));
			// clic droit
			else if (e.getButton() == MouseEvent.BUTTON3 && this.levelContainer.getLevel().getTile(x, y).getNuméro() != 0)
				this.setObjetCourant(new ObjetCourant(this.levelContainer.getLevel().getTile(x, y).getImageIcon().getImage(), this.levelContainer.getLevel().getTile(x, y).getNuméro()));
			else if (this.levelContainer.getLevel().getTile(x, y).getNuméro() == 0)
				this.setObjetCourant(null);
			this.repaint();
		}
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

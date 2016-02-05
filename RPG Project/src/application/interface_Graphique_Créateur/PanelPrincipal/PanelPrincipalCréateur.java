package application.interface_Graphique_Cr�ateur.PanelPrincipal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import application.fonction.Origine;
import application.fonction.Util;
import application.interface_Graphique_Cr�ateur.BorderGray;
import application.interface_Graphique_Cr�ateur.FrameCr�ateur;
import application.interface_Graphique_Cr�ateur.SousPanel;
import application.jeu.ObjetCourant;

@SuppressWarnings("serial")
public class PanelPrincipalCr�ateur extends SousPanel implements MouseListener{

	private static PanelPrincipalCr�ateur instance;
	private ObjetCourant objetCourant;
	private LevelContainer levelContainer;

	public static PanelPrincipalCr�ateur getPanel(){
		if (instance == null){
			instance = new PanelPrincipalCr�ateur();
			instance.setBounds(200, 200, FrameCr�ateur.getFrame().getWidth() - 230, FrameCr�ateur.getFrame().getHeight() - 270);
		}
		return instance;
	}

	public LevelContainer getLevelContainer(){
		return this.levelContainer;
	}

	private PanelPrincipalCr�ateur(){
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
			g.drawString("Veuillez s�lectionner un level � �diter", Origine.getX()+200,Origine.getY()+200);
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
			else if (e.getButton() == MouseEvent.BUTTON3 && this.levelContainer.getLevel().getTile(x, y).getNum�ro() != 0)
				this.setObjetCourant(new ObjetCourant(this.levelContainer.getLevel().getTile(x, y).getImageIcon().getImage(), this.levelContainer.getLevel().getTile(x, y).getNum�ro()));
			else if (this.levelContainer.getLevel().getTile(x, y).getNum�ro() == 0)
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

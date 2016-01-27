package interface_Graphique_Cr�ateur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import jeu.ObjetCourant;

@SuppressWarnings("serial")
public class PanelPrincipalCr�ateur extends SousPanel implements MouseListener{

	private static PanelPrincipalCr�ateur instance;
	private ObjetCourant objetCourant;
	private int x;	
	private int y;

	public static PanelPrincipalCr�ateur getPanel(){
		if (instance == null){
			try {
				instance = new PanelPrincipalCr�ateur();
				instance.setBounds(200, 200, FrameCr�ateur.getFrame().getWidth() - 230, FrameCr�ateur.getFrame().getHeight() - 270);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	private PanelPrincipalCr�ateur() throws IOException{
		super();
		this.x = 0;
		this.y = 0;
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(200,300, Toolkit.getDefaultToolkit().getScreenSize().width - 210 , Toolkit.getDefaultToolkit().getScreenSize().height - 270);
		this.addMouseListener(this);
	}
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (MapContainer.getMap() != null){
			MapContainer.getMap().afficherCarte(g);
		}
		
		//quadrillage
		g.setColor(Color.gray);
		for ( int i = 0 ; i<this.getSize().getWidth(); i += 50){
				g.drawLine((int)Origin.getX()+i, 0, (int)Origin.getX()+i, (int)this.getSize().getHeight());
				g.drawLine(0, (int)Origin.getY()+i,(int)this.getSize().getWidth() , (int)Origin.getY()+i);
		}		
	} 

	public void setObjetCourant(ObjetCourant objetCourant){
		this.objetCourant = objetCourant;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// on r�cup�re les coordonn�es de la case cibl�e
		this.x = fonction.Util.getPixelToTileX(e.getX());
		this.y = fonction.Util.getPixelToTileY(e.getY());
		
		// clic gauche
		if (e.getButton() == MouseEvent.BUTTON1 && this.objetCourant != null){
			MapContainer.getMap().gestionClicGauche(this.x, this.y, this.objetCourant);
		}
		else if (e.getButton() == MouseEvent.BUTTON1 && this.objetCourant == null)
			MapContainer.getMap().getMapFileCourante().getMap().remove(MapContainer.getMap().getMapFileCourante().getTile(this.x, this.y));
		// clic droit
		else if (e.getButton() == MouseEvent.BUTTON3 && MapContainer.getMap().getMapFileCourante().getTile(this.x, this.y).getNum�ro() != 0)
			this.setObjetCourant(new ObjetCourant(MapContainer.getMap().getMapFileCourante().getTile(this.x, this.y).getImageIcon().getImage(), MapContainer.getMap().getMapFileCourante().getTile(this.x, this.y).getNum�ro()));
		else if (MapContainer.getMap().getMapFileCourante().getTile(this.x, this.y).getNum�ro() == 0)
			this.setObjetCourant(null);
		this.repaint();
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
		this.setBounds(200, 200, ((int) ((FrameCr�ateur.getFrame().getWidth()-210) / ObjetIcone.tailleImageJeu)) * ObjetIcone.tailleImageJeu, ((int) ((FrameCr�ateur.getFrame().getHeight() -220) / ObjetIcone.tailleImageJeu)) * ObjetIcone.tailleImageJeu);
		this.repaint();
	}
	
}

package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import jeu.ObjetCourant;

@SuppressWarnings("serial")
public class PanelPrincipalCréateur extends JPanel implements MouseListener{

	private ObjetCourant objetCourant;
	int x;	
	int y;
	private List<Tile> map;
	private int[][] mapFile;
	private ImageIcon background;

	public PanelPrincipalCréateur(){
		super();
		this.background = new ImageIcon("images\\animaux\\bg.jpg");
		this.x = 0;
		this.y = 0 ;
		this.map = new ArrayList<Tile>();
		this.mapFile = new int[100][100];
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(200,200, Toolkit.getDefaultToolkit().getScreenSize().width - 210 , Toolkit.getDefaultToolkit().getScreenSize().height - 270);
		this.addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.background.getImage(), 0, 0, this);
		for (Tile tile : this.map){
			g.drawImage(tile.getImageIcon().getImage(),tile.getX(),tile.getY(),this);
		}

		for(int y = 0; y <100; y ++){
			for(int x = 0; x <100; x ++){
				this.mapFile[x][y] = this.getTile(x,y).getNuméro();
			}
		}
		System.out.print("\n" +"***********************" + "\n");
		for(int y = 0; y <10; y ++){
			for(int x = 0; x <10; x ++){
				System.out.print(" " + this.mapFile[x][y] + " ");
			}
			System.out.print("\n");
		}
	} 

	public void setObjetCourant(ObjetCourant objetCourant){
		this.objetCourant = objetCourant;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (this.objetCourant != null){
			this.x =(int)(e.getX()/ObjetIcone.tailleImageJeu)*ObjetIcone.tailleImageJeu;
			this.y = (int)(e.getY()/ObjetIcone.tailleImageJeu)*ObjetIcone.tailleImageJeu;
			this.map.add(new Tile(this.x, this.y, this.objetCourant.getImageIcon(), this.objetCourant.getNuméro()));
		}
		this.repaint();
	}

	public Tile getTile(int x, int y){
		Tile resultat = new Tile(0);
		for (Tile tile : this.map){
			if (tile.getX() == x*ObjetIcone.tailleImageJeu && tile.getY() == y*ObjetIcone.tailleImageJeu)
				resultat = tile;
		}
		return resultat;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}


}

package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.Graphics;
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
	private Tile tileSize;
	private int x;	
	private int y;
	private List<Tile> map;
	private int[][] mapFile;
	private ImageIcon background;

	public PanelPrincipalCréateur(){
		super();
		this.x = 0;
		this.y = 0 ;
		this.map = new ArrayList<Tile>();
		this.tileSize = new Tile(1,1,new ImageIcon("images\\1.utilitaires\\angleMax.jpg"), 1);
		this.mapFile = new int[1000][1000];
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(200,200, Toolkit.getDefaultToolkit().getScreenSize().width - 210 , Toolkit.getDefaultToolkit().getScreenSize().height - 270);
		this.addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.background != null)
			g.drawImage(this.background.getImage(), 0, 0, this);
		for (Tile tile : this.map){
			g.drawImage(tile.getImageIcon().getImage(),tile.getX(),tile.getY(),this);
		}

		for(int y = 0; y < this.tileSize.getY() ; y ++){
			for(int x = 0; x < this.tileSize.getX(); x ++){
				this.mapFile[x][y] = this.getTile(x,y).getNuméro();
			}
		}
		System.out.print("\n" +"***********************" + "\n");
		for(int y = 0; y < this.tileSize.getY() ; y ++){
			for(int x = 0; x < this.tileSize.getX(); x ++){
				System.out.print(" " + this.mapFile[x][y] + " ");
			}
			System.out.print("\n");
		}

		for(int y = 0; y <= this.tileSize.getY() ; y ++){
			for(int x = 0; x <= this.tileSize.getX() ; x ++){
				if (y == this.tileSize.getY() && x != this.tileSize.getX() )
					g.drawImage(new ImageIcon("images\\1.utilitaires\\bordureV.jpg").getImage(), x*ObjetIcone.tailleImageJeu, y*ObjetIcone.tailleImageJeu, this);
				if (x == this.tileSize.getX() && y != this.tileSize.getY() )
					g.drawImage(new ImageIcon("images\\1.utilitaires\\bordureH.jpg").getImage(), x*ObjetIcone.tailleImageJeu, y*ObjetIcone.tailleImageJeu, this);
			}
		}

		g.drawImage(this.tileSize.getImageIcon().getImage(), this.tileSize.getX() *ObjetIcone.tailleImageJeu, this.tileSize.getY() *ObjetIcone.tailleImageJeu, this);
	} 

	public void setObjetCourant(ObjetCourant objetCourant){
		this.objetCourant = objetCourant;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.x =(int)(e.getX()/ObjetIcone.tailleImageJeu)*ObjetIcone.tailleImageJeu;
		this.y = (int)(e.getY()/ObjetIcone.tailleImageJeu)*ObjetIcone.tailleImageJeu;
		
		if (e.getButton() == MouseEvent.BUTTON1){

			if (this.objetCourant != null && this.objetCourant.getNuméro() != this.tileSize.getNuméro()){
				this.map.remove(this.getTile(this.x / ObjetIcone.tailleImageJeu, this.y / ObjetIcone.tailleImageJeu));
				this.map.add(new Tile(this.x, this.y, this.objetCourant.getImageIcon(), this.objetCourant.getNuméro()));
			}

			if (this.objetCourant != null && this.objetCourant.getNuméro() == this.tileSize.getNuméro()){
				this.tileSize.setX(this.x / ObjetIcone.tailleImageJeu);
				System.out.println(this.tileSize.getX());
				this.tileSize.setY(this.y / ObjetIcone.tailleImageJeu);
			}
		}
		if (e.getButton() == MouseEvent.BUTTON3 && this.getTile(this.x / ObjetIcone.tailleImageJeu, this.y / ObjetIcone.tailleImageJeu) != null)
			this.setObjetCourant(new ObjetCourant(this.getTile(this.x / ObjetIcone.tailleImageJeu, this.y / ObjetIcone.tailleImageJeu).getImageIcon().getImage(), this.getTile(this.x / ObjetIcone.tailleImageJeu, this.y / ObjetIcone.tailleImageJeu).getNuméro()));

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

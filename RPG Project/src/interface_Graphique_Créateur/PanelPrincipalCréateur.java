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

@SuppressWarnings("serial")
public class PanelPrincipalCréateur extends JPanel implements MouseListener{

	private ImageIcon imageCourante;
	int x;	
	int y;
	private List<Tile> map;

	public PanelPrincipalCréateur(){
		super();
		this.x = 0;
		this.y = 0 ;
		this.map = new ArrayList<Tile>();
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(200,200, Toolkit.getDefaultToolkit().getScreenSize().width - 210 , Toolkit.getDefaultToolkit().getScreenSize().height - 270);
		setImageCourante(new ImageIcon());
		this.addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Tile tile : this.map){
			g.drawImage(tile.getImageIcon().getImage(),tile.getX(),tile.getY(),this);
		}
	} 


	public void setImageCourante(ImageIcon image){
		this.imageCourante = image;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.x =(int)(e.getX()/ButtonImageParametre.tailleImageReelle)*ButtonImageParametre.tailleImageReelle;
		this.y = (int)(e.getY()/ButtonImageParametre.tailleImageReelle)*ButtonImageParametre.tailleImageReelle;
		this.map.add(new Tile(this.x, this.y, this.imageCourante, 0));
		this.repaint();

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

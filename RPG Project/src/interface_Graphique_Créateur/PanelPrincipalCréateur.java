package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelPrincipalCréateur extends JPanel implements MouseListener{
	
	private ImageIcon imageCourante;
	int x;	
    int y;

	public PanelPrincipalCréateur(){
		super();
		this.x = 0;
		this.y = 0 ;
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(200,200, Toolkit.getDefaultToolkit().getScreenSize().width - 210 , Toolkit.getDefaultToolkit().getScreenSize().height - 270);
		setImageCourante(new ImageIcon());
		this.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imageCourante.getImage(),this.x,this.y,this);
		//System.out.println("ReDessiner" );
		} 
	
	
	public void setImageCourante(ImageIcon image){
		this.imageCourante = image;
		//System.out.println("J'AI" ); 
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.x =(int)(e.getX()/ButtonImageParametre.tailleImageReelle)*ButtonImageParametre.tailleImageReelle;
		this.y = (int)(e.getY()/ButtonImageParametre.tailleImageReelle)*ButtonImageParametre.tailleImageReelle;
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

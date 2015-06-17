package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;



import jeu.Souris;

@SuppressWarnings("serial")
public class PanelChoixObjetsCréateur extends JPanel{

	private JLabel titre;
	private Souris souris = new Souris();
	private Rectangle logoImageRect;
	Rectangle logoImage = new Rectangle(50, 50, souris.getImageIcon().getIconWidth(), souris.getImageIcon().getIconHeight());
	
	public PanelChoixObjetsCréateur(){
		super();
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(200, 10, Toolkit.getDefaultToolkit().getScreenSize().width - 210 , 180);
		this.titre = new JLabel("Sélectionner un objet pour le placer sur la carte actuelle");
		this.titre.setFont(new Font("Arial", 18,18));
		this.add(this.titre);
		this.repaint();
	
	addMouseListener(new MouseListener(){
			
	           
	            
	            public void mouseClicked(MouseEvent e) {
	            	 int x = e.getX();	
			            int y= e.getY(); 
				}
	 
				public void mouseEntered(MouseEvent e) {
					
	 
				}
	 
				public void mouseExited(MouseEvent e) {

	 
				}
	 
				public void mousePressed(MouseEvent e) {
				       if (logoImage.contains(e.getX(), e.getY()))
		            	    PanelPrincipalCréateur.setImageCourante(souris.getImageIcon());
		            System.out.println("En mémoire" );
				}
	 
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
	 
				}
	

	});
	}
	
	public  void paintComponent( Graphics g){
		super.paintComponent(g);
		g.drawImage(souris.getImageIcon().getImage(),50,50,this);
		
		System.out.println("Dessiner" );
	}
			
	}


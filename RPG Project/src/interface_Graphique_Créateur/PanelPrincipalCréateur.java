package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelPrincipalCréateur extends JPanel{
	
	private static ImageIcon imageCourante;
	int x;	
    int y;

	public PanelPrincipalCréateur(){
		super();
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(200,200, Toolkit.getDefaultToolkit().getScreenSize().width - 210 , Toolkit.getDefaultToolkit().getScreenSize().height - 270);
		addMouseListener(new MouseListener(){
	
	           
            
            public void mouseClicked(MouseEvent e) {
                 x = e.getX();	
                 y= e.getY();
                repaint();
			}
 
			public void mouseEntered(MouseEvent e) {
				
 
			}
 
			public void mouseExited(MouseEvent e) {
 
			}
 
			public void mousePressed(MouseEvent e) {
				
				repaint();

			}	           
 
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
 
			}


});
	}
	
	/*public void paintComponent( Graphics g,int x,int y)
	{
		super.paintComponent(g);
	g.drawImage(imageCourante.getImage(),e.getX(),e.getY(),imageCourante.getImageObserver());
		
		System.out.println("ReDessiner" );

}*/
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imageCourante.getImage(),x-(imageCourante.getIconWidth()/2),y-(imageCourante.getIconHeight()/2),imageCourante.getImageObserver());
		System.out.println("ReDessiner" );
		} 
	
	
	public static void setImageCourante(ImageIcon image){
		imageCourante = image;
		System.out.println("J'AI" ); 
	}
	
	
}

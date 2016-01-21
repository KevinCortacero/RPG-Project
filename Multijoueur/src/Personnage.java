import java.awt.Graphics;


public class Personnage {

	String pseudo;
	int positionX;
	int positionY;
	
	public Personnage(String pseudo){
		this.pseudo = pseudo;
		this.positionX = BDD.getPositionX(pseudo);
		this.positionY = BDD.getPositionY(pseudo);
	}
	
	public void afficherPersonnage(Graphics g){
		g.fillRect(this.positionX, this.positionY, 20, 20);
	}
}

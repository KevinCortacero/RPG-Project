import java.awt.Color;
import java.awt.Graphics;


public class Personnage {

	String pseudo;
	int positionX;
	int positionY;
	
	public Personnage(String pseudo){
		this.pseudo = pseudo;
		this.mettreAJour();
	}
	
	public void mettreAJour() {
		this.positionX = BDD.getPositionX(this.pseudo);
		this.positionY = BDD.getPositionY(this.pseudo);
	}

	public void afficherPersonnage(Graphics g){
		g.fillRect(this.positionX, this.positionY, 20, 20);
		g.setColor(Color.BLACK);
		g.drawString(this.pseudo, this.positionX-6, this.positionY -3);
	}
	
	public String toString(){
		return this.pseudo + " : (" + this.positionX +";" + this.positionY + ")";
	}
}

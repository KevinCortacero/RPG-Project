package network2;
import java.awt.Graphics;
import java.io.Serializable;


public class Personnage implements Serializable{

	String pseudo;
	int positionX;
	int positionY;
	
	public Personnage(String pseudo, int x , int y){
		this.pseudo = pseudo;
		this.mettreAJour();
		this.positionX = x;
		this.positionY = y;
	}
	
	private void mettreAJour() {
		//this.positionX = BDD.getPositionX(this.pseudo);
		//this.positionY = BDD.getPositionY(this.pseudo);
		//System.out.println(this.pseudo + " : (" + this.positionX + ";" + this.positionY + ")");
	}

	public void afficherPersonnage(Graphics g){
		g.fillRect(this.positionX, this.positionY, 20, 20);
	}

	public String getPseudo() {
		return this.pseudo;
	}

	public int getPositionY() {
		return this.positionY;
	}
	
	public int getPositionX() {
		return this.positionX;
	}
	
	@Override
	public String toString() {
		return  pseudo + ", X=" + positionX
				+ ", Y=" + positionY;
	}
}

package network2;
import java.awt.Graphics;
import java.io.Serializable;


public class Personnage implements Serializable{

	String pseudo;
	int positionX;
	int positionY;
	
	public Personnage(String pseudo){
		this.pseudo = pseudo;
		this.initialiser();
	}
	
	private void initialiser(){
		this.positionX = 20; //BDD.getPositionX(this.pseudo);
		this.positionY = 20; //BDD.getPositionY(this.pseudo);
		System.out.println(this.toString() + " : initialisation terminée.");
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
		return  this.pseudo + "X=" + this.positionX	+ "; Y=" + this.positionY;
	}
}

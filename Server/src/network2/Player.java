package network2;
import java.awt.Graphics;
import java.io.Serializable;


public class Player implements Serializable{

	private String pseudo;
	private int positionX;
	private int positionY;
	
	public Player(String pseudo){
		this.pseudo = pseudo;
		this.initialize();
	}
	
	private void initialize(){
		this.positionX = 20;
		this.positionY = 20;
		System.out.println("Création de " + this);
	}

	public void drawPlayer(Graphics g){
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
		return  this.pseudo + " (" + this.positionX	+ ";" + this.positionY + ")";
	}
}

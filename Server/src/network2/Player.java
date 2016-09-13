package network2;
import java.awt.Graphics;
import java.io.Serializable;


public class Player implements Serializable{
	
	private String pseudo;
	private int positionX;
	private int positionY;
	
	public Player(String pseudo){
		this.pseudo = pseudo;
		this.positionX = 100;
		this.positionY = 100;
		System.out.println("Création de " + this);
	}
	
	public void setPseudo(String pseudo){
		this.pseudo = pseudo;
	}

	public void drawPlayer(Graphics g){
		g.fillRect(this.positionX, this.positionY, 100, 100);
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
	
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	
	@Override
	public String toString() {
		return  this.pseudo + " (" + this.positionX	+ ";" + this.positionY + ")";
	}
}

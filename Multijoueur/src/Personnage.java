import java.awt.Color;
import java.awt.Graphics;


public class Personnage {

	private String pseudo;
	private int positionX;
	private int positionY;
	private Color couleur;
	
	public Personnage(String pseudo, int positionX, int positionY){
		this.pseudo = pseudo;
		this.positionX = positionX;
		this.positionY = positionY;
		this.couleur = RandomColor.Create();
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void mettreAJour() {
		this.positionX = BDD.getPositionX(this.pseudo);
		this.positionY = BDD.getPositionY(this.pseudo);
	}

	public void afficherPersonnage(Graphics g){
		g.setColor(this.couleur);
		g.fillRect(this.positionX, this.positionY, 20, 20);
		g.setColor(Color.BLACK);
		g.drawString(this.pseudo, this.positionX-6, this.positionY -3);
	}
	
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public String toString(){
		return this.pseudo + " : (" + this.positionX +";" + this.positionY + ")";
	}
}

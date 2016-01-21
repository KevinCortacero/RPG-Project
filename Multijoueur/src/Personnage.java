import java.awt.Graphics;


public class Personnage {

	String pseudo;
	int positionX;
	int positionY;
	
	public Personnage(String pseudo){
		this.pseudo = pseudo;
		this.mettreAJour();
	}
	
	private void mettreAJour() {
		this.positionX = BDD.getPositionX(this.pseudo);
		this.positionY = BDD.getPositionY(this.pseudo);
		System.out.println(this.pseudo + " : (" + this.positionX + ";" + this.positionY + ")");
	}

	public void afficherPersonnage(Graphics g){
		g.fillRect(this.positionX, this.positionY, 20, 20);
	}
}

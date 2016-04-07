package jeu;

import javax.swing.JPanel;

import personnage.Element;
import personnage.GameObject;
import personnage.H�ros;
import personnage.Obstacle;

public class Jeu extends JPanel {
	
	private GameObject h�ros;
	private Carte carte;
	private Obstacle sol;
	private Obstacle murGauche;
	private Obstacle murDroit;
	private Echelle �chelle;
	
	public Jeu(){
		this.mettreAJourCarte(Carte.CARTE_BASE);
		this.h�ros = new H�ros(this.spawnH�rosX(),this.spawnH�rosY(),50,50,3,3);
		this.sol = new Obstacle(0, 500, 100, this.getWidth());
		this.murGauche = new Obstacle(0, 0, 500, 100);
		this.murDroit = new Obstacle(1100, 0, 500, 100);
		this.�chelle = new Echelle(1050, 200, 300, 50);
	}
	
	public void testCollisionSol(){
		if (this.getH�ros().sprite.hitbox.intersects(this.getSol().sprite.hitbox))
			((H�ros)this.getH�ros()).setEstAuSolTrue();
		else
			((H�ros)this.getH�ros()).setEstAuSolFalse();
	}
	
	public void changementMap(){
		if (((H�ros)this.h�ros).getElement() == Element.SISMA)
			this.mettreAJourCarte(Carte.CARTE_FEU);
		else
			this.mettreAJourCarte(Carte.CARTE_BASE);
	}
	
	public void testPeutGrimper(H�ros h�ros, Echelle �chelle){
		if (h�ros.sprite.coordonn�e2D.getX().getComposante() > �chelle.sprite.coordonn�e2D.getX().getComposante()
		 && h�ros.sprite.coordonn�e2D.getX().getComposante() < �chelle.sprite.coordonn�e2D.getX().getComposante() + �chelle.sprite.hitbox.width
		 && h�ros.sprite.coordonn�e2D.getY().getComposante() + 20 > �chelle.sprite.coordonn�e2D.getY().getComposante()
		 && h�ros.sprite.coordonn�e2D.getY().getComposante() + h�ros.sprite.hitbox.height-1 < �chelle.sprite.coordonn�e2D.getY().getComposante() + �chelle.sprite.hitbox.height
		 && this.getH�ros().estAuSol)
			((H�ros)this.getH�ros()).setPeutGrimper(true);
		else
			((H�ros)this.getH�ros()).setPeutGrimper(false);
				
	}
	
	public GameObject getH�ros(){
		return this.h�ros;
	}

	public Obstacle getSol() {
		return this.sol;
	}
	
	public Obstacle getMurDroit() {
		return this.murDroit;
	}
	
	public Obstacle getMurGauche() {
		return this.murGauche;
	}
	
	public Echelle getEchelle() {
		return this.�chelle;
	}
	
	public Carte getCarte() {
		return this.carte;
	}
	
	public void mettreAJourCarte(Carte carte){
		this.carte = carte;
	}	
	
	public int spawnH�rosX(){
		return this.carte.getSpawn().getX().getComposante();
	}
	
	public int spawnH�rosY(){
		return this.carte.getSpawn().getY().getComposante();
	}
}
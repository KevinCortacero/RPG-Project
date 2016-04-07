package jeu;

import javax.swing.JPanel;

import personnage.Element;
import personnage.GameObject;
import personnage.Héros;
import personnage.Obstacle;

public class Jeu extends JPanel {
	
	private GameObject héros;
	private Carte carte;
	private Obstacle sol;
	private Obstacle murGauche;
	private Obstacle murDroit;
	private Echelle échelle;
	
	public Jeu(){
		this.mettreAJourCarte(Carte.CARTE_BASE);
		this.héros = new Héros(this.spawnHérosX(),this.spawnHérosY(),50,50,3,3);
		this.sol = new Obstacle(0, 500, 100, this.getWidth());
		this.murGauche = new Obstacle(0, 0, 500, 100);
		this.murDroit = new Obstacle(1100, 0, 500, 100);
		this.échelle = new Echelle(1050, 200, 300, 50);
	}
	
	public void testCollisionSol(){
		if (this.getHéros().sprite.hitbox.intersects(this.getSol().sprite.hitbox))
			((Héros)this.getHéros()).setEstAuSolTrue();
		else
			((Héros)this.getHéros()).setEstAuSolFalse();
	}
	
	public void changementMap(){
		if (((Héros)this.héros).getElement() == Element.SISMA)
			this.mettreAJourCarte(Carte.CARTE_FEU);
		else
			this.mettreAJourCarte(Carte.CARTE_BASE);
	}
	
	public void testPeutGrimper(Héros héros, Echelle échelle){
		if (héros.sprite.coordonnée2D.getX().getComposante() > échelle.sprite.coordonnée2D.getX().getComposante()
		 && héros.sprite.coordonnée2D.getX().getComposante() < échelle.sprite.coordonnée2D.getX().getComposante() + échelle.sprite.hitbox.width
		 && héros.sprite.coordonnée2D.getY().getComposante() + 20 > échelle.sprite.coordonnée2D.getY().getComposante()
		 && héros.sprite.coordonnée2D.getY().getComposante() + héros.sprite.hitbox.height-1 < échelle.sprite.coordonnée2D.getY().getComposante() + échelle.sprite.hitbox.height
		 && this.getHéros().estAuSol)
			((Héros)this.getHéros()).setPeutGrimper(true);
		else
			((Héros)this.getHéros()).setPeutGrimper(false);
				
	}
	
	public GameObject getHéros(){
		return this.héros;
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
		return this.échelle;
	}
	
	public Carte getCarte() {
		return this.carte;
	}
	
	public void mettreAJourCarte(Carte carte){
		this.carte = carte;
	}	
	
	public int spawnHérosX(){
		return this.carte.getSpawn().getX().getComposante();
	}
	
	public int spawnHérosY(){
		return this.carte.getSpawn().getY().getComposante();
	}
}
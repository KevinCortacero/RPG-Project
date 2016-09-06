package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import hero.Coord2D;
import hero.FireBall;
import hero.Hero;
import hero.Obstacle;

public class Game extends JPanel {
	
	private Hero hero;
	private Carte carte;
	private Obstacle sol;
	private Obstacle murGauche;
	private Obstacle murDroit;
	private Echelle �chelle;
	
	public Game(Dimension dim){
		super();
		this.setPreferredSize(new Dimension((int)dim.getWidth()-50, (int)dim.getHeight() - 200));
		
		this.carte = Carte.CARTE_BASE;
		this.hero = new Hero(this.spawnHeroe(),50,50,3,3);
		this.sol = new Obstacle(0, 500, 100, this.getWidth());
		this.murGauche = new Obstacle(0, 0, 100, 500);
		this.murDroit = new Obstacle(1100, 0, 100, 500);
		this.�chelle = new Echelle(1050, 200, 50, 300);
	}
	
	public void testCollisionSol(){
		if (this.getH�ros().sprite.hitbox.intersects(this.getSol().sprite.hitbox))
			this.getH�ros().setEstAuSolTrue();
		else
			this.getH�ros().setEstAuSolFalse();
	}

	public void testPeutGrimper(Hero hero, Echelle �chelle){
		if (hero.sprite.coordonn�e2D.getX() > �chelle.sprite.coordonn�e2D.getX()
		 && hero.sprite.coordonn�e2D.getX() < �chelle.sprite.coordonn�e2D.getX() + �chelle.sprite.hitbox.width
		 && hero.sprite.coordonn�e2D.getY() + 20 > �chelle.sprite.coordonn�e2D.getY()
		 && hero.sprite.coordonn�e2D.getY() + hero.sprite.hitbox.height-1 < �chelle.sprite.coordonn�e2D.getY() + �chelle.sprite.hitbox.height
		 && this.getH�ros().estAuSol)
			this.getH�ros().setPeutGrimper(true);
		else
			this.getH�ros().setPeutGrimper(false);				
	}
	
	public Hero getH�ros(){
		return this.hero;
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

	public Coord2D spawnHeroe(){
		return this.carte.getSpawn();
	}
	
	public void paintComponent(Graphics g){
		g.setColor(new Color(255,255,255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		g.setColor(new Color(0,0,0));
		g.drawString("Element : " + ((Hero)this.getH�ros()).getElement(), 100, 200);
		g.drawString("Etat : " + ((Hero)this.getH�ros()).getEtat(), 100, 220);
		g.drawString("Direction : " + this.getH�ros().direction, 100, 240);
		g.drawString("au sol ? : " + this.getH�ros().estAuSol, 100, 260);
		g.drawString("vecteur Y : " + this.getH�ros().vecteurY, 100, 280);
		g.drawString("peut grimper ? : " + ((Hero)this.getH�ros()).getPeutGrimper(), 100, 300);
		g.drawString("x : " + this.getH�ros().sprite.coordonn�e2D.getX(), 100, 320);
		g.drawString("y : " + this.getH�ros().sprite.coordonn�e2D.getY(), 100, 340);
		g.drawString("nb fire : " + ((Hero)this.getH�ros()).fireBalls.size(), 100, 360);

		g.fillRect(this.getSol().sprite.coordonn�e2D.getX(),this.getSol().sprite.coordonn�e2D.getY(),this.getWidth(),this.getSol().sprite.hitbox.height);
		g.setColor(new Color(255,0,0));
		g.fillRect(this.getMurGauche().sprite.coordonn�e2D.getX(), this.getMurGauche().sprite.coordonn�e2D.getY(), this.getMurGauche().sprite.hitbox.width, this.getMurGauche().sprite.hitbox.height);
		g.fillRect(1100, this.getMurDroit().sprite.coordonn�e2D.getY(), this.getMurDroit().sprite.hitbox.width, this.getMurDroit().sprite.hitbox.height);
		g.setColor(new Color(0,255,0));
		g.fillRect(1050, this.getEchelle().sprite.coordonn�e2D.getY(), this.getEchelle().sprite.hitbox.width, this.getEchelle().sprite.hitbox.height);
		g.drawImage(this.getH�ros().sprite.image, (int)this.getH�ros().sprite.coordonn�e2D.getX(), (int)this.getH�ros().sprite.coordonn�e2D.getY(), this);
		g.setColor(new Color(0,0,255));
		g.drawRect(this.getH�ros().sprite.coordonn�e2D.getX(), this.getH�ros().sprite.coordonn�e2D.getY(), 50, 50);
		for (FireBall f : ((Hero)this.getH�ros()).fireBalls){
			g.drawImage(f.sprite.image, f.sprite.coordonn�e2D.getX().getComposante(), f.sprite.coordonn�e2D.getY().getComposante(), this);
		}
		this.afficherCarte(g);
	}

	public void afficherCarte(Graphics g){
		g.setColor(new Color(120,120,120));
		for (int i=0; i <= this.getCarte().getTailleMaxX()-1; i++){
			for (int j=0; j <= this.getCarte().getTailleMaxY()-1; j++){
				if (this.getCarte().getMap()[i][j].getComposante() == 1)
					g.fillRect(20*i, 20*j, 20, 20);
			}
		}
	}
}
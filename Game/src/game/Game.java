package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	private Echelle échelle;
	
	public Game(Dimension dim){
		super();
		this.setPreferredSize(new Dimension((int)dim.getWidth()-50, (int)dim.getHeight() - 200));
		
		this.carte = Carte.CARTE_BASE;
		this.hero = new Hero(this.spawnHeroe(),50,50,3,3);
		this.sol = new Obstacle(new Coord2D(0, 500), 100, this.getWidth());
		this.murGauche = new Obstacle(new Coord2D(0, 0), 100, 500);
		this.murDroit = new Obstacle(new Coord2D(1100, 0), 100, 500);
		this.échelle = new Echelle(new Coord2D(1250, 300), 50, 300);
	}
	
	public void testCollisionSol(){
		if (this.getHéros().sprite.hitbox.intersects(this.getSol().sprite.hitbox))
			this.getHéros().setEstAuSolTrue();
		else
			this.getHéros().setEstAuSolFalse();
	}

	public void testPeutGrimper(Hero hero, Echelle échelle){
		if (hero.getX() > échelle.getX()
		 && hero.getX() < échelle.getX() + échelle.sprite.hitbox.width
		 && hero.getY() + 20 > échelle.getY()
		 && hero.getY() + hero.sprite.hitbox.height-1 < échelle.getY() + échelle.sprite.hitbox.height
		 && this.getHéros().estAuSol)
			this.getHéros().setPeutGrimper(true);
		else
			this.getHéros().setPeutGrimper(false);				
	}
	
	public Hero getHéros(){
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
		return this.échelle;
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
		g.drawString("Element : " + ((Hero)this.getHéros()).getElement(), 100, 200);
		g.drawString("Etat : " + ((Hero)this.getHéros()).getEtat(), 100, 220);
		g.drawString("Direction : " + this.getHéros().direction, 100, 240);
		g.drawString("au sol ? : " + this.getHéros().estAuSol, 100, 260);
		g.drawString("vecteur Y : " + this.getHéros().vecteurY, 100, 280);
		g.drawString("peut grimper ? : " + ((Hero)this.getHéros()).getPeutGrimper(), 100, 300);
		g.drawString("x : " + this.getHéros().getX(), 100, 320);
		g.drawString("y : " + this.getHéros().getY(), 100, 340);
		g.drawString("nb fire : " + ((Hero)this.getHéros()).fireBalls.size(), 100, 360);

		g.fillRect(this.getSol().getX(),this.getSol().getY(),this.getWidth(),this.getSol().sprite.hitbox.height);
		g.setColor(new Color(255,0,0));
		g.fillRect(this.getMurGauche().getX(), this.getMurGauche().getY(), this.getMurGauche().sprite.hitbox.width, this.getMurGauche().sprite.hitbox.height);
		g.fillRect(1100, this.getMurDroit().getY(), this.getMurDroit().sprite.hitbox.width, this.getMurDroit().sprite.hitbox.height);
		g.setColor(new Color(0,255,0));
		g.fillRect(1050, this.getEchelle().getY(), this.getEchelle().sprite.hitbox.width, this.getEchelle().sprite.hitbox.height);
		
		g.drawImage(this.getHéros().sprite.image, this.getHéros().getX(), this.getHéros().getY(), this);
		g.setColor(new Color(0,0,255));
		g.drawRect(this.getHéros().getX(), this.getHéros().getY(), 50, 50);
		for (FireBall f : ((Hero)this.getHéros()).fireBalls){
			g.drawImage(f.sprite.image, f.getX(), f.getY(), this);
		}
		g.setColor(new Color(0,0,0));
		g.drawLine(this.getHéros().getX()+25, this.getHéros().getY()+25, this.getHéros().getX()+25 , this.getHéros().getY()+25 + (int) this.getHéros().vecteurY * 15);
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
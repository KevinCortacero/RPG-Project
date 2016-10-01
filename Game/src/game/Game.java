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
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.carte.draw(g);
		
		/*
		
		// DEBUG PARAMETERS
		g.setColor(new Color(0,0,0));
		g.drawString("ELEMENT   : " + ((Hero)this.getHéros()).getElement(), 100, 200);
		g.drawString("STATE     : " + ((Hero)this.getHéros()).getEtat(), 100, 225);
		g.drawString("DIRECTION : " + this.getHéros().direction, 100, 250);
		g.drawString("ON GROUNG : " + this.getHéros().estAuSol, 100, 275);
		g.drawString("Y VECTOR  : " + this.getHéros().vecteurY, 100, 300);
		g.drawString("CAN CLIMB : " + ((Hero)this.getHéros()).getPeutGrimper(), 100, 325);
		g.drawString("X         : " + this.getHéros().getX(), 100, 350);
		g.drawString("Y         : " + this.getHéros().getY(), 100, 375);
		g.drawString("NB BALLS  : " + ((Hero)this.getHéros()).fireBalls.size(), 100, 400);

		// Draw Obstacles
		this.getSol().draw(g);
		this.getMurDroit().draw(g);
		this.getMurGauche().draw(g);
		
		this.getEchelle().draw(g);
		*/
		for (FireBall f : ((Hero)this.getHéros()).fireBalls){
			f.draw(g);
		}
		
		this.getHéros().draw(g);
		
	}
}
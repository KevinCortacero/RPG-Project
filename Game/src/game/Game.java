package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import hero.Coord2D;
import hero.FireBall;
import hero.Hero;

public class Game extends JPanel {

	private Hero hero;
	private Carte carte;
	private static Game instance;

	public static Game createGame(Dimension dim){
		if (instance == null){
			instance = new Game(dim);
		}
		return instance;
	}

	public static boolean collideH(int x, int y){
		for (int i = 0; i < 5; i++){
			if (instance.carte.getMapValue(x, y+i) != 0)
				return true;
		}
		return false;
	}

	public static boolean collideV(int x, int y){
		for (int i = 0; i < 5; i++){
			if (instance.carte.getMapValue(x + i, y) != 0){
				return true;
			}
		}
		return false;
	}

	private Game(Dimension dim){
		super();
		this.setPreferredSize(new Dimension((int)dim.getWidth()-50, (int)dim.getHeight() - 100));
		this.carte = Carte.CARTE_BASE;
	}
	
	public void initHero(){
		this.hero = new Hero(this.spawnHeroe(),50,50,3,3);
	}

	public void testPeutGrimper(Hero hero, Echelle �chelle){
		if (hero.getX() > �chelle.getX()
				&& hero.getX() < �chelle.getX() + �chelle.sprite.hitbox.width
				&& hero.getY() + 20 > �chelle.getY()
				&& hero.getY() + hero.sprite.hitbox.height-1 < �chelle.getY() + �chelle.sprite.hitbox.height
				&& this.getH�ros().estAuSol)
			this.getH�ros().setPeutGrimper(true);
		else
			this.getH�ros().setPeutGrimper(false);				
	}

	public Hero getH�ros(){
		return this.hero;
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

		// DEBUG PARAMETERS
		g.setColor(new Color(0,0,0));
		g.drawString("ELEMENT   : " + ((Hero)this.getH�ros()).getElement(), 100, 200);
		g.drawString("STATE     : " + ((Hero)this.getH�ros()).getEtat(), 100, 225);
		g.drawString("DIRECTION : " + this.getH�ros().direction, 100, 250);
		g.drawString("ON GROUNG : " + this.getH�ros().estAuSol, 100, 275);
		g.drawString("Y VECTOR  : " + this.getH�ros().getVelocityY(), 100, 300);
		g.drawString("CAN CLIMB : " + ((Hero)this.getH�ros()).getPeutGrimper(), 100, 325);
		g.drawString("X         : " + this.getH�ros().getX(), 100, 350);
		g.drawString("Y         : " + this.getH�ros().getY(), 100, 375);
		g.drawString("NB BALLS  : " + ((Hero)this.getH�ros()).fireBalls.size(), 100, 400);
		
		for (FireBall f : ((Hero)this.getH�ros()).fireBalls){
			f.draw(g);
		}

		this.getH�ros().draw(g);

	}
}
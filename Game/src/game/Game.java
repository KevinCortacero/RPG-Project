package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

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

	public static boolean collideV(float x, float y){
		for (int i = 1; i < 4; i++){
			if (instance.carte.getMapValue((int)(x + i), (int) y) != 0){
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
		this.hero = new Hero(this.carte.getSpawnX(), this.carte.getSpawnY(),50,50);
	}

	public Hero getHéros(){
		return this.hero;
	}

	public Carte getCarte() {
		return this.carte;
	}	

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.carte.draw(g);

		// DEBUG PARAMETERS
		g.setColor(new Color(0,0,0));
		g.drawString("ELEMENT   : " + ((Hero)this.getHéros()).getElement(), 100, 200);
		g.drawString("STATE     : " + ((Hero)this.getHéros()).getEtat(), 100, 225);
		g.drawString("DIRECTION : " + this.getHéros().direction, 100, 250);
		g.drawString("ON GROUNG : " + this.getHéros().estAuSol, 100, 275);
		g.drawString("Y VECTOR  : " + this.getHéros().getVelocityY(), 100, 300);
		g.drawString("X VECTOR  : " + this.getHéros().getVelocityX(), 100, 325);
		g.drawString("X         : " + this.getHéros().getX(), 100, 375);
		g.drawString("Y         : " + this.getHéros().getY(), 100, 400);
		g.drawString("NB BALLS  : " + ((Hero)this.getHéros()).fireBalls.size(), 100, 425);
		
		for (FireBall f : ((Hero)this.getHéros()).fireBalls){
			f.draw(g);
		}

		this.getHéros().draw(g);

	}
}
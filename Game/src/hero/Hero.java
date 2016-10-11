
package hero;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import game.Game;
import game.KeyBoard;


public class Hero extends GameObject implements Alive{

	protected Element element;
	protected State state;
	protected int  nbFireBalls;
	public List<FireBall> fireBalls;
	protected int nbAquaBalls;
	protected int nbSautsActuels;
	protected int nbSautsMax;
	protected boolean peutGrimper;

	public Hero(Coord2D spawnHeroe, int hauteur, int largeur, int nbFireBalls, int nbAquaballs){
		super(spawnHeroe, hauteur, largeur);
		this.element = Element.FONDAMENTAL;
		this.state = State.IMMOBILE;
		this.nbSautsMax = 0;
		this.nbSautsActuels = 0;
		this.nbFireBalls = nbFireBalls;
		this.nbAquaBalls = nbAquaballs;
		this.peutGrimper = false;
		this.fireBalls = new ArrayList<FireBall>();
		this.live();

		this.updateSprite();
		this.sprite.animate(largeur, hauteur);
	}
	
	public void setElement(Element e){
		this.element = e;
	}
	@Override
	public void live() {
		Thread live = new Thread(){
			public void run() {
				while(true){
					try {
						Thread.sleep(8);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Hero.this.updateHéros();
				}
			}
		};
		live.start();
	}

	public void updateHéros(){
		this.updateAction();
		this.updateÉtat();
		this.updateGravité();
		this.updateDéplacement();
		this.updateSprite();
		this.updateSauvegardeDirection();
	}

	private void updateAction() {
		for (FireBall f : this.fireBalls){
			f.update();
		}
	}

	public void tirer() {
		if (this.element == Element.BLIZZ || this.element == Element.IGNIS){
			this.fireBalls.add(new FireBall(this.getVelocityY(), this.sprite.coordonnée2D,this.direction, this.element));
		}
	}

	public void updateÉtat(){
		//if (this.clavier.bas && this.element == Element.SISMA)
		//	this.state = State.BOUCLIER;
		if (!this.estAuSol && this.element != Element.SISMA && this.element != Element.FONDAMENTAL && this.nbSautsActuels == 0)
			this.fall();
		if (!this.estAuSol && this.element == Element.SISMA)
			this.state = State.ENCLUME;
	}

	public void updateDéplacement(){

		if (this.isIdle())
			this.state = State.IMMOBILE;

		if (this.state == State.ENCLUME)
			this.activerEnclume(2);
	}

	public void updateGravité(){
		if (!Game.collideV(this.getX() / 10,  (this.getY() + 50 + (int)this.getVelocityY()) / 10)){
			this.applyGravity();
			this.sprite.coordonnée2D.setY(this.getY()+(int)this.getVelocityY());
			this.setEstAuSolFalse();
		}
		else {
			this.setEstAuSolTrue();
		}	
	} 



	public void sauter(){
		if (this.nbSautsActuels < this.nbSautsMax)
		{
			this.applyForceY(-10.0F);
			this.sprite.coordonnée2D.setY(this.getY()+(int)this.getVelocityY());
			this.nbSautsActuels ++ ;
		}
	}

	public void moveRight(int vitesse){

		if (! Game.collideH((this.getX() + 50 + vitesse) / 10, this.getY() / 10)){
			this.sprite.coordonnée2D.setX(this.getX()+vitesse);
		}
		this.direction = Direction.DROITE;
		this.state = State.WALK;
	}
	public void fall(){
		this.state = State.TOMBE;
		this.direction = Direction.BAS;
	}

	public void moveLeft(int vitesse){
		if (this.getX() - vitesse > 0 && ! Game.collideH((this.getX() - vitesse) / 10, this.getY() / 10)){
			this.sprite.coordonnée2D.setX(this.getX()-vitesse);
		}
		this.direction = Direction.GAUCHE;
		this.state = State.WALK;
	}

	public void moveUp(int vitesse){
		this.sprite.coordonnée2D.setY(this.getY()+vitesse);
		this.direction = Direction.HAUT;
	}

	public void moveDown(int vitesse){
		if (!Game.collideV(this.getX() / 10, (this.getY() + 50 + vitesse) / 10)){
			this.sprite.coordonnée2D.setY(this.getY()+vitesse);
			this.setEstAuSolFalse();
		}	
		else
			this.setEstAuSolTrue();
	}

	public void activerEnclume(int vitesse){
		this.sprite.coordonnée2D.setY(this.getY()+vitesse);
		this.direction = Direction.BAS;
	}

	public void updateSauvegardeDirection(){
		if (this.direction == Direction.DROITE || this.direction == Direction.GAUCHE)
			this.direction.sauvegarde(this.direction);
	}

	public void updateSprite(){
		this.sprite.setPath("images/" + this.element + this.state + this.direction + ".png");
	}

	public int getNbFireBalls() {
		return this.nbFireBalls;
	}

	public void setNbFireBalls(int nbFireBalls) {
		this.nbFireBalls = nbFireBalls;
	}

	public int getNbAquaBalls() {
		return this.nbAquaBalls;
	}

	public void setNbAquaBalls(int nbAquaBalls) {
		this.nbAquaBalls = nbAquaBalls;
	}
	public int getNbSautsActuels() {
		return this.nbSautsActuels;
	}

	public void setNbSautsActuels(int nbSautsActuels) {
		this.nbSautsActuels = nbSautsActuels;
	}

	public int getNbSautsMax() {
		return this.nbSautsMax;
	}

	public void setNbSautsMax(int nbSautsMax) {
		this.nbSautsMax = nbSautsMax;
	}

	public void setEstAuSolTrue() {
		if (!this.estAuSol)
		{
			this.estAuSol = true;	
			this.setNbSautsActuels(0);
			this.resetForceY();
		}
	}

	public void setEstAuSolFalse() {
		if (this.estAuSol)
			this.estAuSol = false;	
	}

	public boolean getPeutGrimper() {
		return this.peutGrimper;
	}

	public void setPeutGrimper(boolean peutGrimper) {
		this.peutGrimper = peutGrimper;
	}

	public Element getElement() {
		return this.element;
	}

	public State getEtat() {
		return this.state;
	}

	@Override
	public void draw(Graphics g) {
		// Draw image
		g.drawImage(this.sprite.getImage(), this.getX(), this.getY(), null);

		// Draw hitbox as a blue rectangle
		//g.setColor(new Color(0,0,255));
		//g.drawRect(this.getX(), this.getY(), 50, 50);

		// Draw vector as a black line
		g.setColor(new Color(0,0,0));
		g.drawLine(this.getX()+25, this.getY()+25, this.getX()+25 , this.getY()+25 + (int) this.getVelocityY() * 15);
	}
}
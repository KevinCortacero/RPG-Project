
package hero;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.Frame;
import game.Game;
import game.KeyBoard;


public class Hero extends GameObject implements Alive{

	protected Element element;
	protected State state;
	public List<FireBall> fireBalls;
	protected int nbJumpCurrent;
	public KeyBoard kb;

	public Hero(float x, float y, int width, int height){
		super(x, y, width, height);
		this.element = Element.FONDAMENTAL;
		this.state = State.IMMOBILE;
		this.nbJumpCurrent = 0;
		this.fireBalls = new ArrayList<FireBall>();
		this.kb = new KeyBoard();
		this.live();

		this.updateSprite();
		this.sprite.animate(width, height);
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
						Thread.sleep((int) (1000 * Frame.DELTA_TIME));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Hero.this.update();
				}
			}
		};
		live.start();
	}

	public void update(){

		this.updateInput();
		this.updateGravity();
		//update coords
		this.updatePosition();

		//this.updateSavedDirection();

		this.updateState();
		this.updateFireballs();

		this.updateSprite();
	}

	private void updateInput(){
		if (this.kb.a){
			this.setElement(Element.BLIZZ);
		}
		else if (this.kb.z){
			this.setElement(Element.IGNIS);
		}
		else if (this.kb.e){
			this.setElement(Element.ZEPHYR);
		}
		else if (this.kb.r){
			this.setElement(Element.SISMA);
		}

		if (this.kb.droite){
			this.moveRight(3);
		}
		else if (this.kb.gauche){
			this.moveLeft(3);
		}
		else
			this.resetForceX();

		if (this.kb.tir){
			this.fire();
		}
		if (this.kb.saut){
			this.jump();
		}
	}
	private void updateFireballs() {
		for (Iterator<FireBall> iter = this.fireBalls.listIterator(); iter.hasNext(); ) {
			FireBall f = iter.next();
			if (f.getX() < -50 || f.getX() > 2000){
				iter.remove();
			}
			else
				f.update();
		}
	}

	public void fire() {
		if (this.element == Element.BLIZZ){
			//TODO: Aquaball
		}

		else if (this.element == Element.IGNIS){
			this.fireBalls.add(new FireBall(this.getX(), this.getY(),this.direction));
		}
		this.kb.tir = false;
	}

	public void updateState(){
		//TODO:
		if (this.isIdle()){
			this.state = State.IMMOBILE;
		}
	}

	public void updatePosition(){

		// update position
		this.addX(this.getVelocityX());
		this.addY(this.getVelocityY());

		// update velocity
		if (this.estAuSol){
			this.velocity.resetY();
		}
		else
			this.velocity.applyForce(0.0F, Game.GRAVITY);

	}

	private boolean isMovingDown(){
		return (this.getVelocityY() > 0.0F);
	}
	private boolean isMovingUp(){
		return (this.getVelocityY() < 0.0F);
	}
	private boolean isCollidingDown(){
		return (Game.collideV(this.getX() / 10, (this.getY() + 50 + this.getVelocityY()) / 10));
	}
	private boolean isCollidingUp(){
		return (Game.collideV(this.getX() / 10,  (int) ((this.getY() + (int)(this.getVelocityY())) / 10)));
	}
	public void updateGravity(){

		// move down
		if (this.isMovingDown()){
			if (this.isCollidingDown()){
				this.putOnGround();
			}
			else {
				this.setEstAuSolFalse();
			}	
		}
		// move up
		else if (this.isMovingUp()){
			if (this.isCollidingUp()){
				this.resetForceY();
			}
		}
	} 



	public void jump(){
		if (this.nbJumpCurrent < this.element.nbJumpMax)
		{
			this.setForceY(-10.0F);
			this.nbJumpCurrent ++ ;
			this.kb.setSautFalse();
			this.setEstAuSolFalse();
		}
	}

	private boolean isCollidingRight(int vitesse){
		return (Game.collideH(((int) (this.getX() + 50 + vitesse -10)) / 10, (int) (this.getY() / 10)));
	}
	public void moveRight(int vitesse){

		if (this.isCollidingRight(vitesse)){
			this.resetForceX();
		}
		else{
			this.setForceX(vitesse);
		}
		if (!this.isCollidingDown())
			this.setEstAuSolFalse();
		this.direction = Direction.DROITE;
		this.state = State.WALK;
	}

	private boolean isCollidingLeft(int vitesse){
		return (Game.collideH((int)(this.getX() - vitesse +10) / 10, (int)(this.getY() / 10)));
	}
	public void moveLeft(int vitesse){

		if (isCollidingLeft(vitesse)){
			this.resetForceX();
		}
		else{
			this.setForceX(-vitesse);
		}
		if (!this.isCollidingDown())
			this.setEstAuSolFalse();
		this.direction = Direction.GAUCHE;
		this.state = State.WALK;
	}


	public void updateSavedDirection(){
		if (this.direction == Direction.DROITE || this.direction == Direction.GAUCHE)
			this.direction.sauvegarde(this.direction);
	}

	public void updateSprite(){
		this.sprite.setPath("images/" + this.element.name + this.state + this.direction + ".png");
	}

	public int getNbSautsActuels() {
		return this.nbJumpCurrent;
	}

	public void setNbSautsActuels(int nbSautsActuels) {
		this.nbJumpCurrent = nbSautsActuels;
	}

	public void putOnGround() {
		this.addY(Math.round((this.getY() + 6) / 10) * 10 - this.getY());
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

	public String getElement() {
		return this.element.name;
	}

	public State getEtat() {
		return this.state;
	}

	@Override
	public void draw(Graphics g) {
		// Draw image
		g.drawImage(this.sprite.getImage(), (int) this.getX(), (int) this.getY(), null);

		// Draw hitbox as a blue rectangle
		//g.setColor(new Color(0,0,255));
		//g.drawRect(this.getX(), this.getY(), 50, 50);

		// Draw vector as a black line
		g.setColor(new Color(0,0,0));
		g.drawString("TWARZ",(int) this.getX() +10 , (int) this.getY() -10);
	}

}

package hero;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

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
	protected KeyBoard clavier;

	public Hero(Coord2D spawnHeroe, int hauteur, int largeur, int nbFireBalls, int nbAquaballs){
		super(spawnHeroe, hauteur, largeur);
		this.element = Element.FONDAMENTAL;
		this.state = State.IMMOBILE;
		this.nbSautsMax = 0;
		this.nbSautsActuels = 0;
		this.nbFireBalls = nbFireBalls;
		this.nbAquaBalls = nbAquaballs;
		this.peutGrimper = false;
		this.clavier = new KeyBoard();
		this.fireBalls = new ArrayList<FireBall>();
		this.live();
		
		this.updateSprite();
		this.sprite.animate(largeur, hauteur);
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
		this.updateElement();
		this.updateAction();
		this.updateÉtat();
		this.updateGravité();
		this.updateDéplacement();
		this.updateSprite();
		this.updateSauvegardeDirection();
	}

	private void updateAction() {
		if (this.clavier.tir && (this.getElement() == Element.BLIZZ || this.getElement() == Element.IGNIS)){
			this.tirer();
		}
		for (FireBall f : this.fireBalls){
			f.update();
		}
	}

	private void tirer() {
		this.fireBalls.add(new FireBall(this.vecteurY, this.sprite.coordonnée2D,this.direction, this.element));
		this.clavier.tir = false;
	}

	public void updateElement(){
		if (this.clavier.a && this.state != State.TOMBE && this.element != Element.BLIZZ){
			this.element = Element.BLIZZ;
			this.nbSautsMax = 1;
		}
		if (this.clavier.z && this.state != State.TOMBE){
			this.element = Element.IGNIS;
			this.nbSautsMax = 1;
		}
		if (this.clavier.e && this.state != State.TOMBE){
			this.element = Element.ZEPHYR;
			this.nbSautsMax = 2;
		}
		if (this.clavier.r){
			this.element = Element.SISMA;
			this.nbSautsMax = 0;
		}
		
	}

	public void updateÉtat(){
		if (this.clavier.bas && this.element == Element.SISMA)
			this.state = State.BOUCLIER;
		if (!this.estAuSol && this.element != Element.SISMA && this.element != Element.FONDAMENTAL && this.nbSautsActuels == 0)
			this.tomber();
		if (!this.estAuSol && this.element == Element.SISMA)
			this.state = State.ENCLUME;
	}

	public void updateDéplacement(){

		if (this.clavier.droite && !this.clavier.gauche && this.state != State.ENCLUME)
			this.déplacerDroite(2);

		if (this.clavier.gauche && !this.clavier.droite && this.state != State.ENCLUME)
			this.déplacerGauche(2);

		if (this.clavier.haut && this.element != Element.SISMA && this.element != Element.FONDAMENTAL && this.getPeutGrimper())
			this.déplacerHaut(1);

		if (this.clavier.bas && this.element != Element.SISMA && this.element != Element.FONDAMENTAL && this.getPeutGrimper())
			this.déplacerBas(2);

		if (this.aucuneTouche() && this.estAuSol)
			this.nePasBouger();

		if (this.state == State.ENCLUME)
			this.activerEnclume(2);

		if (this.clavier.saut)
			this.sauter();
	}

	public void updateGravité(){
		if (!this.getPeutGrimper()){
			if (this.getY() < 450){
				this.vecteurY += 0.15F * 2;
				this.sprite.coordonnée2D.setY(this.getY()+(int)this.vecteurY);
				this.setEstAuSolFalse();
			}
			else {
				this.sprite.coordonnée2D.setY(450);
				this.setEstAuSolTrue();
			}	
		}			
	}

	public void sauter(){
		if (this.nbSautsActuels < this.nbSautsMax)
		{
			this.vecteurY = -10.0F;
			this.sprite.coordonnée2D.setY(this.getY()+(int)this.vecteurY);
			this.nbSautsActuels ++ ;
		}
		this.clavier.setSautFalse();
	}
	public boolean aucuneTouche(){
		return (!this.clavier.droite && !this.clavier.gauche &&
				!this.clavier.haut   && !this.clavier.bas);
	}
	public void nePasBouger(){
		this.state = State.IMMOBILE;
		//this.direction = this.direction.getSauvegarde();
	}
	public void déplacerDroite(int vitesse){
		this.sprite.coordonnée2D.setX(this.getX()+vitesse);
		this.direction = Direction.DROITE;
		this.state = State.WALK;
	}
	public void tomber(){
		this.state = State.TOMBE;
		this.direction = Direction.BAS;
	}

	public void déplacerGauche(int vitesse){
		this.sprite.coordonnée2D.setX(this.getX()-vitesse);
		this.direction = Direction.GAUCHE;
		this.state = State.WALK;
	}

	public void déplacerHaut(int vitesse){
		this.sprite.coordonnée2D.setY(this.getY()-vitesse);
		this.direction = Direction.HAUT;
		this.state = State.GRIMPE;
	}

	public void déplacerBas(int vitesse){
		if (!this.estAuSol){
			this.sprite.coordonnée2D.setY(this.getY()+vitesse);
			this.direction = Direction.BAS;
			this.state = State.GRIMPE;
		}	
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
			this.vecteurY = 0.0F;
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

	public KeyListener getClavier() {
		return this.clavier;
	}

	@Override
	public void draw(Graphics g) {
		// Draw image
		g.drawImage(this.sprite.getImage(), this.getX(), this.getY(), null);
		
		// Draw hitbox as a blue rectangle
		g.setColor(new Color(0,0,255));
		g.drawRect(this.getX(), this.getY(), 50, 50);
		
		// Draw vector as a black line
		g.setColor(new Color(0,0,0));
		g.drawLine(this.getX()+25, this.getY()+25, this.getX()+25 , this.getY()+25 + (int) this.vecteurY * 15);
	}
}
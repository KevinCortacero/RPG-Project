
package hero;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import game.KeyBoard;


public class Hero extends GameObject implements Alive{

	protected Element �l�ment;
	protected State �tat;
	protected int  nbFireBalls;
	public List<FireBall> fireBalls;
	protected int nbAquaBalls;
	protected int nbSautsActuels;
	protected int nbSautsMax;
	protected boolean peutGrimper;
	protected KeyBoard clavier;

	public Hero(Coord2D spawnHeroe, int hauteur, int largeur, int nbFireBalls, int nbAquaballs){
		super(spawnHeroe, hauteur, largeur);
		this.�l�ment = Element.FONDAMENTAL;
		this.�tat = State.IMMOBILE;
		this.nbSautsMax = 0;
		this.nbSautsActuels = 0;
		this.nbFireBalls = nbFireBalls;
		this.nbAquaBalls = nbAquaballs;
		this.peutGrimper = false;
		this.clavier = new KeyBoard();
		this.fireBalls = new ArrayList<FireBall>();
		this.updateH�ros();
		this.live();
		this.sprite.animate();
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
					Hero.this.updateH�ros();
				}
			}
		};
		live.start();
	}
	
	public void updateH�ros(){
		this.updateEl�ment();
		this.updateAction();
		this.update�tat();
		this.updateGravit�();
		this.updateD�placement();
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
		this.fireBalls.add(new FireBall(this.vecteurY, this.sprite.coordonn�e2D,this.direction, this.�l�ment));
		this.clavier.tir = false;
	}

	public void updateEl�ment(){
		if (this.clavier.a && this.�tat != State.TOMBE){
			this.�l�ment = Element.BLIZZ;
			this.nbSautsMax = 1;
		}
		if (this.clavier.z && this.�tat != State.TOMBE){
			this.�l�ment = Element.IGNIS;
			this.nbSautsMax = 1;
		}
		if (this.clavier.e && this.�tat != State.TOMBE){
			this.�l�ment = Element.ZEPHYR;
			this.nbSautsMax = 2;
		}
		if (this.clavier.r){
			this.�l�ment = Element.SISMA;
			this.nbSautsMax = 0;
		}
	}

	public void update�tat(){
		if (this.clavier.bas && this.�l�ment == Element.SISMA)
			this.�tat = State.BOUCLIER;
		if (!this.estAuSol && this.�l�ment != Element.SISMA && this.�l�ment != Element.FONDAMENTAL && this.nbSautsActuels == 0)
			this.tomber();
		if (!this.estAuSol && this.�l�ment == Element.SISMA)
			this.�tat = State.ENCLUME;
	}

	public void updateD�placement(){

		if (this.clavier.droite && !this.clavier.gauche && this.�tat != State.ENCLUME)
			this.d�placerDroite(3);

		if (this.clavier.gauche && !this.clavier.droite && this.�tat != State.ENCLUME)
			this.d�placerGauche(3);

		if (this.clavier.haut && this.�l�ment != Element.SISMA && this.�l�ment != Element.FONDAMENTAL && this.getPeutGrimper())
			this.d�placerHaut(1);

		if (this.clavier.bas && this.�l�ment != Element.SISMA && this.�l�ment != Element.FONDAMENTAL && this.getPeutGrimper())
			this.d�placerBas(2);

		if (this.aucuneTouche() && this.estAuSol)
			this.nePasBouger();

		if (this.�tat == State.ENCLUME)
			this.activerEnclume(2);

		if (this.clavier.saut)
			this.sauter();
	}

	public void updateGravit�(){
		if (!this.getPeutGrimper()){
			if (this.getY() < 450){
				this.vecteurY += 0.25F;
				this.sprite.coordonn�e2D.setY(this.getY()+(int)this.vecteurY);
				this.setEstAuSolFalse();
			}
			else {
				this.sprite.coordonn�e2D.setY(450);
				this.setEstAuSolTrue();
			}	
		}			
	}

	public void sauter(){
		if (this.nbSautsActuels < this.nbSautsMax)
		{
			this.vecteurY = -10.0F;
			this.sprite.coordonn�e2D.setY(this.getY()+(int)this.vecteurY);
			this.nbSautsActuels ++ ;
		}
		this.clavier.setSautFalse();
	}
	public boolean aucuneTouche(){
		return (!this.clavier.droite && !this.clavier.gauche &&
				!this.clavier.haut   && !this.clavier.bas);
	}
	public void nePasBouger(){
		this.�tat = State.IMMOBILE;
		//this.direction = this.direction.getSauvegarde();
	}
	public void d�placerDroite(int vitesse){
		this.sprite.coordonn�e2D.setX(this.getX()+vitesse);
		this.direction = Direction.DROITE;
		this.�tat = State.WALK;
	}
	public void tomber(){
		this.�tat = State.TOMBE;
		this.direction = Direction.BAS;
	}

	public void d�placerGauche(int vitesse){
		this.sprite.coordonn�e2D.setX(this.getX()-vitesse);
		this.direction = Direction.GAUCHE;
		this.�tat = State.WALK;
	}

	public void d�placerHaut(int vitesse){
		this.sprite.coordonn�e2D.setY(this.getY()-vitesse);
		this.direction = Direction.HAUT;
		this.�tat = State.GRIMPE;
	}

	public void d�placerBas(int vitesse){
		if (!this.estAuSol){
			this.sprite.coordonn�e2D.setY(this.getY()+vitesse);
			this.direction = Direction.BAS;
			this.�tat = State.GRIMPE;
		}	
	}

	public void activerEnclume(int vitesse){
		this.sprite.coordonn�e2D.setY(this.getY()+vitesse);
		this.direction = Direction.BAS;
	}

	public void updateSauvegardeDirection(){
		if (this.direction == Direction.DROITE || this.direction == Direction.GAUCHE)
			this.direction.sauvegarde(this.direction);
	}

	public void updateSprite(){
		this.sprite.setSprite("images/" + this.�l�ment + this.�tat + this.direction + ".png");
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
		return this.�l�ment;
	}
	
	public State getEtat() {
		return this.�tat;
	}

	public KeyListener getClavier() {
		return this.clavier;
	}
}
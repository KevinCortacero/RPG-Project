
package personnage;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import jeu.Clavier;


public class Héros extends GameObject {

	protected Element élément;
	protected Etat état;
	protected int  nbFireBalls;
	public List<FireBall> fireBalls;
	protected int nbAquaBalls;
	protected int nbSautsActuels;
	protected int nbSautsMax;
	protected boolean peutGrimper;
	protected Clavier clavier;

	public Héros(int x, int y, int hauteur, int largeur, int nbFireBalls, int nbAquaballs){
		super(x,y, hauteur, largeur);
		this.élément = Element.FONDAMENTAL;
		this.état = Etat.IMMOBILE;
		this.nbSautsMax = 0;
		this.nbSautsActuels = 0;
		this.nbFireBalls = nbFireBalls;
		this.nbAquaBalls = nbAquaballs;
		this.peutGrimper = false;
		this.clavier = new Clavier();
		this.fireBalls = new ArrayList<FireBall>();
	}

	public void updateHéros(){
		this.updateElément();
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
		this.fireBalls.add(new FireBall(this.vecteurY, this.sprite.coordonnée2D,this.direction, this.élément));
		this.clavier.tir = false;
	}

	public void updateElément(){
		if (this.clavier.a && this.état != Etat.TOMBE){
			this.élément = Element.BLIZZ;
			this.nbSautsMax = 1;
		}
		if (this.clavier.z && this.état != Etat.TOMBE){
			this.élément = Element.IGNIS;
			this.nbSautsMax = 1;
		}
		if (this.clavier.e && this.état != Etat.TOMBE){
			this.élément = Element.ZEPHYR;
			this.nbSautsMax = 2;
		}
		if (this.clavier.r){
			this.élément = Element.SISMA;
			this.nbSautsMax = 0;
		}
	}

	public void updateÉtat(){
		if (this.clavier.bas && this.élément == Element.SISMA)
			this.état = Etat.BOUCLIER;
		if (!this.estAuSol && this.élément != Element.SISMA && this.élément != Element.FONDAMENTAL && this.nbSautsActuels == 0)
			this.tomber();
		if (!this.estAuSol && this.élément == Element.SISMA)
			this.état = Etat.ENCLUME;
	}

	public void updateDéplacement(){

		if (this.clavier.droite && !this.clavier.gauche && this.état != Etat.ENCLUME)
			this.déplacerDroite(3);

		if (this.clavier.gauche && !this.clavier.droite && this.état != Etat.ENCLUME)
			this.déplacerGauche(3);

		if (this.clavier.haut && this.élément != Element.SISMA && this.élément != Element.FONDAMENTAL && this.getPeutGrimper())
			this.déplacerHaut(1);

		if (this.clavier.bas && this.élément != Element.SISMA && this.élément != Element.FONDAMENTAL && this.getPeutGrimper())
			this.déplacerBas(2);

		if (this.aucuneTouche() && this.estAuSol)
			this.nePasBouger();

		if (this.état == Etat.ENCLUME)
			this.activerEnclume(2);

		if (this.clavier.saut)
			this.sauter();
	}

	public void updateGravité(){
		if (!this.getPeutGrimper()){
			if (this.sprite.coordonnée2D.getY().getComposante() < 450){
				this.vecteurY += 0.25F;
				this.sprite.coordonnée2D.setY(this.sprite.coordonnée2D.getY().getComposante()+(int)this.vecteurY);
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
			this.sprite.coordonnée2D.setY(this.sprite.coordonnée2D.getY().getComposante()+(int)this.vecteurY);
			this.nbSautsActuels ++ ;
		}
		this.clavier.setSautFalse();
	}
	public boolean aucuneTouche(){
		return (!this.clavier.droite && !this.clavier.gauche &&
				!this.clavier.haut   && !this.clavier.bas);
	}
	public void nePasBouger(){
		this.état = Etat.IMMOBILE;
		//this.direction = this.direction.getSauvegarde();
	}
	public void déplacerDroite(int vitesse){
		this.sprite.coordonnée2D.setX(this.sprite.coordonnée2D.getX().getComposante()+vitesse);
		this.direction = Direction.DROITE;
		this.état = Etat.MARCHE;
	}
	public void tomber(){
		this.état = Etat.TOMBE;
		this.direction = Direction.BAS;
	}

	public void déplacerGauche(int vitesse){
		this.sprite.coordonnée2D.setX(this.sprite.coordonnée2D.getX().getComposante()-vitesse);
		this.direction = Direction.GAUCHE;
		this.état = Etat.MARCHE;
	}

	public void déplacerHaut(int vitesse){
		this.sprite.coordonnée2D.setY(this.sprite.coordonnée2D.getY().getComposante()-vitesse);
		this.direction = Direction.HAUT;
		this.état = Etat.GRIMPE;
	}

	public void déplacerBas(int vitesse){
		if (!this.estAuSol){
			this.sprite.coordonnée2D.setY(this.sprite.coordonnée2D.getY().getComposante()+vitesse);
			this.direction = Direction.BAS;
			this.état = Etat.GRIMPE;
		}	
	}

	public void activerEnclume(int vitesse){
		this.sprite.coordonnée2D.setY(this.sprite.coordonnée2D.getY().getComposante()+vitesse);
		this.direction = Direction.BAS;
	}

	public void updateSauvegardeDirection(){
		if (this.direction == Direction.DROITE || this.direction == Direction.GAUCHE)
			this.direction.sauvegarde(this.direction);
	}

	public void updateSprite(){
		this.sprite.setSprite("images/"/*+ this.élément + this.état + this.direction + ".png*/+"oiseau.gif");
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
		return this.élément;
	}
	
	public Etat getEtat() {
		return this.état;
	}

	public KeyListener getClavier() {
		return this.clavier;
	}
}
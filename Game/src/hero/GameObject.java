package hero;

public abstract class GameObject implements Drawable{
	
	public Direction direction;
	public boolean estAuSol;
	public Sprite sprite;
	private Velocity velocity;
	
	public GameObject(Coord2D coord, int hauteur, int largeur){
		this.velocity = new Velocity(0.0F, 0.0F);
		this.sprite = new Sprite(coord,hauteur,largeur);
		this.direction = Direction.DROITE;
		this.estAuSol = false;
	}
	
	public int getX(){
		return this.sprite.coordonnée2D.getX();
	}
	
	public int getY(){
		return this.sprite.coordonnée2D.getY();
	}
	
	public float getVelocityX() {
		return this.velocity.getVelocityX();
	}
	
	public float getVelocityY() {
		return this.velocity.getVelocityY();
	}
	
	public void applyGravity(){
		this.velocity.applyGravity();
	}
	
	public void resetForceY() {
		this.velocity.resetY();
	}
	
	public void applyForceY(float forceY){
		this.velocity.applyForceY(forceY);
	}
	
	public void applyForceX(float forceX){
		this.velocity.applyForceX(forceX);
	}
	
	public boolean isIdle(){
		return this.velocity.isIdle();
	}
}

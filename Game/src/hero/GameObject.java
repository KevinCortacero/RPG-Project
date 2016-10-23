package hero;

public abstract class GameObject implements Drawable{
	
	public Direction direction;
	public boolean estAuSol;
	public Sprite sprite;
	protected Velocity velocity;
	
	public GameObject(float x, float y, int width, int height){
		this.velocity = new Velocity(0.0F, 0.0F);
		this.sprite = new Sprite(x, y, width, height);
		this.direction = Direction.DROITE;
		this.estAuSol = false;
	}
	
	public float getX(){
		return this.sprite.getX();
	}
	
	public float getY(){
		return this.sprite.getY();
	}
	
	public void addX(float x){
		this.sprite.addX(x);
	}
	
	public void addY(float y){
		this.sprite.addY(y);
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
	
	public void resetForceX() {
		this.velocity.resetX();
	}

	public void setForceX(float forceX){
		this.velocity.setForceX(forceX);
	}
	
	public void setForceY(float forceY){
		this.velocity.setForceY(forceY);
	}
	
	public void applyForce(float forceX, float forceY){
		this.velocity.applyForce(forceX, forceY);
	}
	
	public boolean isIdle(){
		return this.velocity.isIdle();
	}
}

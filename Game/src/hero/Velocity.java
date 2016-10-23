package hero;

public class Velocity {
	
	private float x;
	private float y;
	
	public Velocity(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void applyForce(float forceX, float forceY){
		this.x += forceX;
		this.y += forceY;
	}
	
	public void applyGravity(){
		this.applyForce(0.0F, +0.5F);
	}

	public float getVelocityX() {
		return this.x;
	}
	
	public float getVelocityY() {
		return this.y;
	}

	public void resetY() {
		this.y = 0.0F;
	}
	
	public boolean isIdle(){
		return (this.x == 0.0F && this.y == 0.0F);
	}

	public void resetX(){
		this.x = 0.0F; 
	}

	public void setForceX(float forceX) {
		this.x = forceX;
	}
	
	public void setForceY(float forceY) {
		this.y = forceY;
	}
}

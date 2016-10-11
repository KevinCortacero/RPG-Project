package hero;

public class Velocity {
	
	private float x;
	private float y;
	
	public Velocity(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void applyForceX(float forceX){
		this.x += forceX;
	}
	
	public void applyForceY(float forceY){
		this.y += forceY;
	}
	
	public void applyGravity(){
		this.applyForceY(+0.30F);
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
}

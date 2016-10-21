package hero;

import java.awt.Image;

public class Sprite{
	
	public Image image;
	private Thread animation;
	private Animation anim;
	public String path;
	private float x;
	private float y;

	public Sprite (float x, float y, int width, int height){
		this.x = x;
		this.y = y;
		this.anim = new Animation(this, width, height);
	}

	public void animate(int width, int height) {
		this.animation = new Thread(this.anim);
		this.animation.start();
	}

	public Image getImage(){
		return this.anim.getCurrentTile();
	}
	
	public void setPath(String path){
		this.anim.setPath(path);
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
	
	public void addX(float x){
		this.x += x;
	}
	
	public void addY(float y){
		this.y += y;
	}
}

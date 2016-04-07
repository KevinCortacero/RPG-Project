package personnage;

public class Coordonnée2D {
	private Coordonnée x;
	private Coordonnée y;
	
	public Coordonnée2D(int x, int y){
		this.x = new Coordonnée(x);
		this.y = new Coordonnée(y);
	}

	public Coordonnée getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x.setComposante( x);
	}

	public Coordonnée getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y.setComposante(y);
	}
}

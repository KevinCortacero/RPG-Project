package personnage;

public class Coordonn�e2D {
	private Coordonn�e x;
	private Coordonn�e y;
	
	public Coordonn�e2D(int x, int y){
		this.x = new Coordonn�e(x);
		this.y = new Coordonn�e(y);
	}

	public Coordonn�e getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x.setComposante( x);
	}

	public Coordonn�e getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y.setComposante(y);
	}
}

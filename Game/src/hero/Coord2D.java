package hero;

public class Coord2D {
	private Coord x;
	private Coord y;
	
	public Coord2D(int x, int y){
		this.x = new Coord(x);
		this.y = new Coord(y);
	}

	public int getX() {
		return this.x.getComposante();
	}

	public void setX(int x) {
		this.x.setComposante(x);
	}

	public int getY() {
		return this.y.getComposante();
	}

	public void setY(int y) {
		this.y.setComposante(y);
	}
}

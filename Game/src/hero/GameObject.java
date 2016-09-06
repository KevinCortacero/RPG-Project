package hero;

public abstract class GameObject {
	
	public float vecteurY;
	public Direction direction;
	public boolean estAuSol;
	public Sprite sprite;
	
	public GameObject(Coord2D coord, int hauteur, int largeur){
		this.sprite = new Sprite(coord,hauteur,largeur);
		this.vecteurY = 0.0F;
		this.direction = Direction.DROITE;
		this.estAuSol = false;
	}
}

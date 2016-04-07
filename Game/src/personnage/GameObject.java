package personnage;

public abstract class GameObject {
	
	public float vecteurY;
	public Direction direction;
	public boolean estAuSol;
	public Sprite sprite;
	
	public GameObject(int x, int y, int hauteur, int largeur){
		this.sprite = new Sprite(x,y,hauteur,largeur);
		this.vecteurY = 0.0F;
		this.direction = Direction.DROITE;
		this.estAuSol = false;
	}
}

package interface_Graphique_Créateur;

public class Origin {

	private static float x;
	private static float y;
	private static Origin instanceOrigin;

	public static Origin getOrigin(){
		if ( instanceOrigin == null){
			synchronized (Origin.class){
				if ( instanceOrigin == null )
					instanceOrigin = new Origin();
			}
		}
		return instanceOrigin;
	}

	private Origin(){
		Origin.x=0;
		Origin.y=0;
	}
	
	public static float getX() {
		return Origin.x;
	}

	public static void setX(float x) {
		Origin.x = x;
	}

	public static float getY() {
		return Origin.y;
	}

	public static void setY(float y) {
		Origin.y = y;
	}
}

package application.interface_Graphique_Créateur;

public class Origin {

	private static float x;
	private static float y;
	
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
	
	public static void reset(){
		Origin.x = 0;
		Origin.y = 0;
	}
}

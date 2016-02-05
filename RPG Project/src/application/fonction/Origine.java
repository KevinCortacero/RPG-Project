package application.fonction;

public final class Origine {

	private static int x;
	private static int y;
	
	public static int getX() {
		return Origine.x;
	}
	public static void setX(int x) {
		Origine.x = x;
	}
	public static int getY() {
		return Origine.y;
	}
	public static void setY(int y) {
		Origine.y = y;
	}
	public static void reset() {
		Origine.x = 0;
		Origine.y = 0;
	}
}

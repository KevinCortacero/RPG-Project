import java.awt.Color;


public class RandomColor extends Color{

	private RandomColor(int r, int g, int b) {
		super(r, g, b);
		// TODO Auto-generated constructor stub
	}
	
	public static Color Create(){
		return new RandomColor(randomNuance(), randomNuance(), randomNuance());
	}
	
	private static int randomNuance(){
		return (int)(Math.random() * 256);
	}

}

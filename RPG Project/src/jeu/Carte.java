package jeu;

public class Carte {

	private int y;
	
	public Carte(int y){
		this.y = y;
	}
	
	public String toString(){
		return "Robot" + this.y;
	}
}

package jeu;

import java.util.ArrayList;
import java.util.List;

public class Héros {
	private int vie;
	private int x;
	private int o;
	private List<Carte> listeRobots;
	
	
	public Héros(int vie, int x, int o){
		this.vie = vie;
		this.x = x;
		this.o = o;
		this.listeRobots = new ArrayList<Carte>();
		this.listeRobots.add(new Carte(1));
		this.listeRobots.add(new Carte(2));
		this.listeRobots.add(new Carte(3));
		this.listeRobots.add(new Carte(4));
	}
	
	public int getVie(){
		return this.vie;
	}
	
	public String toString(){
		return "Héros" + this.vie;
	}
	
	public List<Carte> getListeRobot(){
		return this.listeRobots;
	}
}

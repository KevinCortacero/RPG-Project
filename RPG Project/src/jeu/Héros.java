package jeu;

import java.util.ArrayList;
import java.util.List;

public class H�ros {
	private int vie;
	private int x;
	private int o;
	private List<Robot> listeRobots;
	
	
	public H�ros(int vie, int x, int o){
		this.vie = vie;
		this.x = x;
		this.o = o;
		this.listeRobots = new ArrayList<Robot>();
		this.listeRobots.add(new Robot(1));
		this.listeRobots.add(new Robot(2));
		this.listeRobots.add(new Robot(3));
		this.listeRobots.add(new Robot(4));
	}
	
	public int getVie(){
		return this.vie;
	}
	
	public String toString(){
		return "H�ros" + this.vie;
	}
	
	public List<Robot> getListeRobot(){
		return this.listeRobots;
	}
}

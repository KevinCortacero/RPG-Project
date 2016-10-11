package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import hero.Element;
import hero.Hero;

public class KeyBoard extends KeyAdapter {
	
	private Hero hero;

	public KeyBoard(Hero hero) {
		this.hero = hero;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			this.hero.sauter();
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			this.hero.moveRight(3);
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			this.hero.moveLeft(3);
		
		if (e.getKeyCode() == KeyEvent.VK_A){
			this.hero.setElement(Element.BLIZZ);
			this.hero.setNbSautsMax(1);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_Z){
			this.hero.setElement(Element.IGNIS);
			this.hero.setNbSautsMax(1);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_E){
			this.hero.setElement(Element.ZEPHYR);
			this.hero.setNbSautsMax(2);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_R){
			this.hero.setElement(Element.SISMA);
			this.hero.setNbSautsMax(0);
		}
			
		if (e.getKeyCode() == KeyEvent.VK_V)
			this.hero.tirer();
	}
}

package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {
	
	public boolean droite = false;
	public boolean gauche = false;
	public boolean haut = false;
	public boolean bas = false;
	public boolean a = false;
	public boolean z = false;
	public boolean e = false;
	public boolean r = false;
	public boolean saut = false;
	public boolean tir = false;

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			this.saut = true;
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			this.droite = true;
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			this.gauche = true;
		
		if (e.getKeyCode() == KeyEvent.VK_UP)
			this.haut = true;
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			this.bas = true;
		
		if (e.getKeyCode() == KeyEvent.VK_A)
			this.a = true;
		
		if (e.getKeyCode() == KeyEvent.VK_Z)
			this.z = true;
		
		if (e.getKeyCode() == KeyEvent.VK_E)
			this.e = true;
		
		if (e.getKeyCode() == KeyEvent.VK_R)
			this.r = true;
		if (e.getKeyCode() == KeyEvent.VK_V)
			this.tir = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			this.setSautFalse();
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			this.droite = false;
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			this.gauche = false;
		
		if (e.getKeyCode() == KeyEvent.VK_UP)
			this.haut = false;
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			this.bas = false;
		
		if (e.getKeyCode() == KeyEvent.VK_A)
			this.a = false;
		
		if (e.getKeyCode() == KeyEvent.VK_Z)
			this.z = false;
		
		if (e.getKeyCode() == KeyEvent.VK_E)
			this.e = false;
		
		if (e.getKeyCode() == KeyEvent.VK_R)
			this.r = false;
		
		if (e.getKeyCode() == KeyEvent.VK_V)
			this.tir = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setSautFalse(){
		this.saut = false;
	}

}

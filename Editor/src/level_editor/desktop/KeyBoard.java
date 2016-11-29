package level_editor.desktop;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import level_editor.desktop.level_designer.LevelDesigner;
import level_editor.tools.Origine;
import level_editor.tools.Parametres;

public class KeyBoard extends KeyAdapter{

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_D)
			Origine.setX(Origine.getX() - Parametres.VITESSE_DEPLACEMENT);
		if (e.getKeyCode() == KeyEvent.VK_S)
			Origine.setY(Origine.getY() - Parametres.VITESSE_DEPLACEMENT);
		if (e.getKeyCode() == KeyEvent.VK_Q)
			Origine.setX(Origine.getX() + Parametres.VITESSE_DEPLACEMENT);
		if (e.getKeyCode() == KeyEvent.VK_Z)
			Origine.setY(Origine.getY() + Parametres.VITESSE_DEPLACEMENT);
		
		LevelDesigner.getPanel().repaint();
	}
}

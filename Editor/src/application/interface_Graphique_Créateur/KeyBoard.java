package application.interface_Graphique_Créateur;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import application.fonction.Origine;
import application.fonction.Parametres;
import application.interface_Graphique_Créateur.PanelPrincipal.LevelDesigner;

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

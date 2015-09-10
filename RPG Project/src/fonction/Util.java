package fonction;

import interface_Graphique_Créateur.Origin;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Util {

	private Origin origin = Origin.getOrigin();

	public static int getPixelToTileX(float mouse){
		return (int)((mouse - Origin.getX() )/ interface_Graphique_Créateur.ObjetIcone.tailleImageJeu);
	}
	
	public static int getPixelToTileY(float mouse){
		return (int)((mouse - Origin.getY() )/ interface_Graphique_Créateur.ObjetIcone.tailleImageJeu);
	}
}

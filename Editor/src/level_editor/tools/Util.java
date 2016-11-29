package level_editor.tools;

import level_editor.desktop.toolbar.ObjetIcone;

public class Util {

	public static int getPixelToTileX(float mouse){
		return (int)((mouse - Origine.getX())/ ObjetIcone.tailleImageJeu) ;
	}
	
	public static int getPixelToTileY(float mouse){
		return (int)((mouse - Origine.getY())/ ObjetIcone.tailleImageJeu);
	}
}

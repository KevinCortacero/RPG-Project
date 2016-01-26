package fonction;

import interface_Graphique_Créateur.Origin;

public class Util {

	public static int getPixelToTileX(float mouse){
		return (int)((mouse - Origin.getX() )/ interface_Graphique_Créateur.ObjetIcone.tailleImageJeu);
	}
	
	public static int getPixelToTileY(float mouse){
		return (int)((mouse - Origin.getY() )/ interface_Graphique_Créateur.ObjetIcone.tailleImageJeu);
	}
}

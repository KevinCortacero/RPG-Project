package application.fonction;

import application.interface_Graphique_Créateur.Origin;
import application.interface_Graphique_Créateur.PanelObjets.ObjetIcone;

public class Util {

	public static int getPixelToTileX(float mouse){
		return (int)((mouse - Origin.getX() )/ ObjetIcone.tailleImageJeu);
	}
	
	public static int getPixelToTileY(float mouse){
		return (int)((mouse - Origin.getY() )/ ObjetIcone.tailleImageJeu);
	}
}

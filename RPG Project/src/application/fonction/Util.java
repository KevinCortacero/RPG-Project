package application.fonction;

import application.interface_Graphique_Cr�ateur.PanelObjets.ObjetIcone;

public class Util {

	public static int getPixelToTileX(float mouse){
		return (int)((mouse)/ ObjetIcone.tailleImageJeu);
	}
	
	public static int getPixelToTileY(float mouse){
		return (int)((mouse)/ ObjetIcone.tailleImageJeu);
	}
}

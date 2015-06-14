package interface_Graphique_Créateur;

import java.awt.Dimension;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class DimensionsCréateurNiveau extends Dimension {
	
	public DimensionsCréateurNiveau(){
		
		this.height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.width	= (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();	
	}
}

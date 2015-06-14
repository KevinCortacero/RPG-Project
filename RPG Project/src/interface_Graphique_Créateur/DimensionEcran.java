package interface_Graphique_Créateur;

import java.awt.Dimension;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class DimensionEcran extends Dimension {

	public DimensionEcran(){

		this.height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.width	= (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();	
	}
}

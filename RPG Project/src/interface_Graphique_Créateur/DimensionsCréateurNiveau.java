package interface_Graphique_Cr�ateur;

import java.awt.Dimension;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class DimensionsCr�ateurNiveau extends Dimension {
	
	public DimensionsCr�ateurNiveau(){
		
		this.height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.width	= (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();	
	}
}

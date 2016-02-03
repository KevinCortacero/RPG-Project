package application.interface_Graphique_Créateur.PanelPrincipal;

import java.awt.Canvas;
import java.io.File;

public class Level2 extends Canvas{

	public File getFile() {
		File file = null;
		file = new File("cartes/Base/Base-2.txt");
		return file;
	}

	public String getName() {
		return "base-2";
	}

}

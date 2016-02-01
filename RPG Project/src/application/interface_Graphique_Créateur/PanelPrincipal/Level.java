package application.interface_Graphique_Créateur.PanelPrincipal;

import java.io.File;

public class Level {

	public File getFile() {
		File file = null;
		file = new File("cartes/Base/Base-2.txt");
		return file;
	}

	public String getName() {
		return "base-2";
	}

	public int getBackground(){
		return 2;
	}

}

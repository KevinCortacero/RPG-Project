package interface_Graphique_Créateur;

import java.io.File;

public class Level {

	public File getFile() {
		return new File("cartes/Base/Base-2.txt");
	}

	public String getName() {
		return "base-2";
	}

	public int getBackground(){
		return 2;
	}

}

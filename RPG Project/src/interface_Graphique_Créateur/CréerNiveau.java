package interface_Graphique_Cr�ateur;

import java.io.IOException;

import dataBase.DataBase;

public class Cr�erNiveau {

	public static void main(String[] args) throws IOException {
		FrameCr�ateur f = FrameCr�ateur.getFrame();
		f.setVisible(true);
		DataBase.getBDD().addLevel(new Level());
		while ( f.isEnabled() ){
			f.raffraichir();		
			try {
			      Thread.sleep(100);
			    } catch (InterruptedException e) {
			      e.printStackTrace();
			    }
		}
	}
}

package interface_Graphique_Créateur;

import java.io.IOException;

public class CréerNiveau {

	public static void main(String[] args) throws IOException {
		FrameCréateur f = FrameCréateur.getFrame();
		f.setVisible(true);
		
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
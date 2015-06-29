package interface_Graphique_Créateur;

import java.io.IOException;

public class CréerNiveau {

	public static void main(String[] args) throws IOException {
		FrameCréateur f = new FrameCréateur();
		f.setVisible(true);
		
		int i = 0;
		while ( i < 60 ){
			f.raffraichir();		

			try {
			      Thread.sleep(100);
			    } catch (InterruptedException e) {
			      e.printStackTrace();
			    }
			i++;
		}
	}
}

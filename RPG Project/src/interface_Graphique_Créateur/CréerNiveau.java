package interface_Graphique_Cr�ateur;

import java.io.IOException;

public class Cr�erNiveau {

	public static void main(String[] args) throws IOException {
		FrameCr�ateur f = new FrameCr�ateur();
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

package application;

import application.interface_Graphique_Cr�ateur.FrameCr�ateur;

public class Cr�erNiveau {

	public static void main(String[] args){
		FrameCr�ateur f = FrameCr�ateur.getFrame();
		f.setVisible(true);

		while ( f.isEnabled() ){
			f.raffraichir();		
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

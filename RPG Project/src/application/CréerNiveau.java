package application;

import application.interface_Graphique_Créateur.FrameCréateur;

public class CréerNiveau {

	public static void main(String[] args){
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

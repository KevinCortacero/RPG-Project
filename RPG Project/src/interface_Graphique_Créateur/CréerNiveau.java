package interface_Graphique_Créateur;

public class CréerNiveau {

	public static void main(String[] args) {
		FrameCréateur f = new FrameCréateur();
		f.setVisible(true);
		
		int i = 0;
		while ( i < 60 ){
			f.raffraichir();			
			try {
			      Thread.sleep(10000);
			    } catch (InterruptedException e) {
			      e.printStackTrace();
			    }
			i++;
		}
	}
}
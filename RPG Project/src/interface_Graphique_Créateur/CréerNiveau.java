package interface_Graphique_Cr�ateur;

public class Cr�erNiveau {

	public static void main(String[] args) {
		FrameCr�ateur f = new FrameCr�ateur();
		f.setVisible(true);
		
		int i = 0;
		while ( i < 2000 ){
			f.raffraichir();
			
			try {
			      Thread.sleep(1000);
			    } catch (InterruptedException e) {
			      e.printStackTrace();
			    }
			i++;
		}
	}
}
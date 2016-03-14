package ihm;

import chessquito.PartieChessquito;
import chessquito.PartieNonInitialiseeException;

public class ControleurChessQuito {

	public VueChessquito vue;
	private PartieChessquito modele;

	public ControleurChessQuito() {
		this.vue = new VueChessquito();
		this.modele = new PartieChessquito();
		this.modele.initialiser();
		try {
			this.modele.remplirEchiquier();
		} catch (PartieNonInitialiseeException e) {
			e.printStackTrace();
		}
	}

	public void rafraichir() {         
		try {             
			// pour chaque case du plateau du modele
			for (int i = 0; i < VueChessquito.NB_CASES; i++) { 
				for (int j = 0; j < VueChessquito.NB_CASES; j++) { 
					// verifier s'il existe une piece sur cette case  
					String nom = this.modele.getNomPiece(j,i);
					if (nom != null) {
						// si c'est le cas positionner la piece sur le plateau graphique                      
						String couleur = this.modele.getCouleurPiece(j,i);    
						vue.positionnerPiece(new PieceIHM(couleur, nom, "Fixe"), i+1, j+1);   
					} else {                         
						vue.positionnerPiece(null, i+1, j+1);   
					}               
				}          
			}         
		} catch (Exception ex) {        
			ex.printStackTrace(); 
		}     
	}
}

package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import chessquito.PartieChessquito;
import chessquito.PartieNonInitialiseeException;

public class ControleurChessQuito implements ActionListener{

	public VueChessquito vue;
	private PartieChessquito modele;
	private Etat etat;

	private enum Etat{
		CHOISIR,
		DEPLACER
	}
	
	public ControleurChessQuito() {
		this.vue = new VueChessquito();
		this.modele = new PartieChessquito();
		this.modele.initialiser();
		this.etat = Etat.CHOISIR;
		try {
			this.modele.remplirEchiquier();
		} catch (PartieNonInitialiseeException e) {
			e.printStackTrace();
		}
		
		((JButton)this.vue.panneauJeu.getComponent(1)).addActionListener(this);
	}

	public void rafraichir(){         
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

	@Override
	public void actionPerformed(ActionEvent e) {
		CaseIHM piece = (CaseIHM) e.getSource();
		if (this.etat == Etat.CHOISIR){
			piece.s
		}
	}
}

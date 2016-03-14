package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VueChessquito extends JPanel{

	public static final int NB_CASES = 4;
	
	private JTextArea messages;
	private JPanel panneauJeu;
	
	public VueChessquito() {
		super();
		this.setPreferredSize(new Dimension(800, 800));
		this.panneauJeu = new JPanel(new GridLayout(NB_CASES,NB_CASES));
		for(int i = 0; i < NB_CASES ; i++){
			for(int j = 0; j < NB_CASES ; j++){
				Color color = ((i+j) % 2 == 0) ? Color.GRAY : Color.WHITE;
				this.panneauJeu.add(new CaseIHM(i,j,color));
			}
		}
		JPanel panneauMessage = new JPanel();
		this.messages = new JTextArea(4, 40);        
		this.messages.setEditable(false);
		panneauMessage.add(new JScrollPane(this.messages));
		this.add(this.panneauJeu);
		this.add(panneauMessage);
		this.validate();
	}
	
	public void positionnerPiece(PieceIHM piece, int i, int j){
		int position = (j*NB_CASES) - (NB_CASES-i) - 1;
		if (piece != null){
			System.out.println("On place : " + piece.getNom());
			((JButton)this.panneauJeu.getComponent(position)).setIcon(new ImageIcon("images/" + piece.getNom() + piece.getCouleur() + piece.getTypeImage() + ".gif"));
		}
		
		else{
			System.out.println("case null");
			((JButton)this.panneauJeu.getComponent(position)).setIcon(null);
		}
	}
	
	 public void afficherMessage(String message) {
		 this.messages.append(message);
		 this.messages.setSelectionEnd(this.messages.getText().length());     
	} 
	
	public static void main(String[] args) {
		JFrame f = new JFrame("Chess Quito");
		f.setPreferredSize(new Dimension(100*(VueChessquito.NB_CASES+1), 100*(VueChessquito.NB_CASES+2)));
		f.setVisible(true);
		ControleurChessQuito ctrl = new ControleurChessQuito();
		f.add(ctrl.vue);
		ctrl.rafraichir();
		f.pack();
	}
	
}

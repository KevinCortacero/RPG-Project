package communicationJoueur;

import java.rmi.registry.LocateRegistry;

public class TicTacTocServer {
	
	public static void main(String[] args) throws Exception {
			
		LocateRegistry.createRegistry(10666) ;
		TicTacToc p1 = new TicTacTocImpl(true, "Twarz") ;
		TicTacToc p2 = new TicTacTocImpl(false, "Koreuc") ;	
		
		p1.setAdversaire(p2);
		p2.setAdversaire(p1);
	}

}

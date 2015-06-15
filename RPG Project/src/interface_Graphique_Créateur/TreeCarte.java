package interface_Graphique_Créateur;

import java.awt.Color;
import java.util.List;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import jeu.LieuDeCarte;
import jeu.Carte;

@SuppressWarnings("serial")
public class TreeCarte extends JTree{
	
	private JTree treeCartes;

	public TreeCarte(List<LieuDeCarte> cartes){
		this.setRootVisible(true);
		this.setBackground(Color.BLUE);
		DefaultMutableTreeNode listeCartes = new DefaultMutableTreeNode("Liste des Cartes");
		
		for (LieuDeCarte carte : cartes){
			DefaultMutableTreeNode typeCarte = new DefaultMutableTreeNode(carte.toString());
			
			for (Carte robot : carte.getListeRobot()){
				DefaultMutableTreeNode numéroCarte = new DefaultMutableTreeNode(robot.toString());
				typeCarte.add(numéroCarte);
			}
			listeCartes.add(typeCarte);
		}

		this.treeCartes = new JTree(listeCartes);
		// SALUUUUUUUUUUUUUUUUUUUUUUUUUUUUT
		//Inch'allah on mange des bananes on bas des cités !
	}
	
	public JTree getTree(){
		return this.treeCartes;
	}
	
}

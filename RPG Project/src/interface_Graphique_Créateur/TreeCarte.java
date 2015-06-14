package interface_Graphique_Cr�ateur;

import java.awt.Color;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import jeu.H�ros;
import jeu.Robot;

@SuppressWarnings("serial")
public class TreeCarte extends JTree{
	
	private JTree treeCartes;

	public TreeCarte(List<H�ros> cartes){
		this.setRootVisible(true);
		this.setBackground(Color.BLUE);
		DefaultMutableTreeNode listeCartes = new DefaultMutableTreeNode("Liste des Cartes");
		
		for (H�ros carte : cartes){
			DefaultMutableTreeNode typeCarte = new DefaultMutableTreeNode(carte.toString());
			
			for (Robot robot : carte.getListeRobot()){
				DefaultMutableTreeNode num�roCarte = new DefaultMutableTreeNode(robot.toString());
				typeCarte.add(num�roCarte);
			}
			listeCartes.add(typeCarte);
		}

		this.treeCartes = new JTree(listeCartes);
		// SALUUUUUUUUUUUUUUUUUUUUUUUUUUUUT
		//Inch'allah on mange des bananes on bas des cit�s !
	}
	
	
	public JTree getTree(){
		return this.treeCartes;
	}
	
}

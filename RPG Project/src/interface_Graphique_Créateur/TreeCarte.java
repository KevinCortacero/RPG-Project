package interface_Graphique_Créateur;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class TreeCarte extends JTree{

	private JTree treeCartes;

	public TreeCarte(Map<String, TypeDeCarte> cartes){
		this.setRootVisible(true);
		this.setBackground(Color.BLUE);
		
		DefaultMutableTreeNode type = new DefaultMutableTreeNode("Liste des Cartes");

		for (TypeDeCarte typeDeCarte : cartes){
			DefaultMutableTreeNode lieu = new DefaultMutableTreeNode(typeDeCarte.toString());

			for (ZoneDeCarte lieuCarte : typeDeCarte.getListeCarte()){
				DefaultMutableTreeNode zone = new DefaultMutableTreeNode(lieuCarte.toString());

				for (Carte carte : lieuCarte.getListeCarte()){
					DefaultMutableTreeNode numéroCarte = new DefaultMutableTreeNode(carte.toString());
					zone.add(numéroCarte);
				}
				lieu.add(zone);
			}
			type.add(lieu);
		}
		this.treeCartes = new JTree(type);
	}
	
	// ligne 1
	// ligne 3
}
package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class PanelGestionCréateur extends JPanel {

	private JLabel titre;
	private JTree arbre;
	private File carte;
	private DefaultMutableTreeNode racine;

	public PanelGestionCréateur() {
		super();
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245, 245, 245));
		this.setBounds(10, 200, 180, Toolkit.getDefaultToolkit().getScreenSize().height - 270);
		this.titre = new JLabel("Gestion des cartes");
		this.titre.setFont(new Font("Arial", 18, 18));
		this.carte = new File("cartes");
		this.add(this.titre);
		this.listRoot();

	}

	private void listRoot() {
		this.racine = new DefaultMutableTreeNode("Liste des cartes :");
		int count = 0;

		for (String file : this.carte.list()) {

			System.out.println(this.carte.getAbsolutePath() + "\\" + file);
			
     		DefaultMutableTreeNode lecteur = new DefaultMutableTreeNode(file); 
     		
			File fileEnfant = new File(this.carte.getAbsolutePath()+ "\\" + file);
			if ( fileEnfant.isDirectory()){
				try { 
					 for(String nom : fileEnfant.list()){ 
						 DefaultMutableTreeNode node = new DefaultMutableTreeNode(nom );
						 lecteur.add(this.listFile( new File( this.carte.getAbsolutePath() + "\\" + file + "\\" + nom), node  ));
						 }
					 } catch
					 (NullPointerException e) {}
			}

			this.racine.add(lecteur);
		}
		// Nous créons, avec notre hiérarchie, un arbre
		arbre = new JTree(this.racine);
		// Que nous plaçons sur le ContentPane de notre JFrame à l'aide d'un
		// scroll
		this.add(new JScrollPane(arbre));
	}

	private DefaultMutableTreeNode listFile(File file,
			DefaultMutableTreeNode node) {
		int count = 0;

		if (file.isFile())
			return new DefaultMutableTreeNode(file.getName());
		else {
			File[] list = file.listFiles();
			if (list == null)
				return new DefaultMutableTreeNode(file.getName());

			for (File nom : list) {
				count++;
				// Pas plus de 5 enfants par noeud
				if (count < 5) {
					DefaultMutableTreeNode subNode;
					if (nom.isDirectory()) {
						subNode = new DefaultMutableTreeNode(nom.getName()
								+ "\\");
						node.add(this.listFile(nom, subNode));
					} else {
						subNode = new DefaultMutableTreeNode(nom.getName());
					}
					node.add(subNode);
				}
			}
			return node;
		}
	}
}

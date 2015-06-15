package interface_Graphique_Cr�ateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class PanelGestionCr�ateur extends JPanel {

	private JLabel titre;
	private JTree arbre;
	private File carte;
	private DefaultMutableTreeNode racine;

	public PanelGestionCr�ateur() {
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

		for (String file : this.carte.list()) {

			DefaultMutableTreeNode lecteur = new DefaultMutableTreeNode(file);
			File fileEnfant = new File(this.carte.getAbsolutePath()+ "\\" + file);

			if ( fileEnfant.isDirectory()){
				for(String nom : fileEnfant.list()){ 
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(nom);
					lecteur.add(this.listFile( new File( this.carte.getAbsolutePath() + "\\" + file + "\\" + nom), node  ));
				}
			}
			this.racine.add(lecteur);
		}

		this.arbre = new JTree(this.racine);
		this.arbre.setBackground(new Color(245, 245, 245));
		this.add(arbre);
	}

	private DefaultMutableTreeNode listFile(File file, DefaultMutableTreeNode node) {

		if (file.isFile())
			return new DefaultMutableTreeNode(file.getName());

		File[] list = file.listFiles();
		if (list == null)
			return new DefaultMutableTreeNode(file.getName());

		for (File nom : list) {
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

		return node;
	}
}


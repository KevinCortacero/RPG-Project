package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class PanelGestionCréateur extends JPanel implements TreeSelectionListener{

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
		this.add(arbre);
		this.arbre.addTreeSelectionListener(this);
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
	}

	private DefaultMutableTreeNode listFile(File file, DefaultMutableTreeNode node) {

		if (file.isFile() || file.listFiles() == null)
			return new DefaultMutableTreeNode(file.getName());

		for (File nom : file.listFiles()) {

			DefaultMutableTreeNode subNode = new DefaultMutableTreeNode(nom.getName());

			if (nom.isDirectory()) 
				node.add(this.listFile(nom, subNode));
			else
				node.add(subNode);	
		}
		return node;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		if (this.arbre.getLastSelectedPathComponent() != null) {
			System.out.println(this.arbre.getLastSelectedPathComponent().toString());
		}
		
		
	}
}



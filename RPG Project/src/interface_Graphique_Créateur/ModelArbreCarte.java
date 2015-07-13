package interface_Graphique_Cr�ateur;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class ModelArbreCarte extends  DefaultMutableTreeNode{

	private File carte;
	
	public ModelArbreCarte(File d�part){
		super("Liste des cartes");
		this.carte = d�part;
		this.listRoot();
	}
	
	public void listRoot() {

		for (String file : this.carte.list()) {

			DefaultMutableTreeNode lecteur = new DefaultMutableTreeNode(file);

			File fileEnfant = new File(this.carte.getAbsolutePath()+ "\\" + file);

			if ( fileEnfant.isDirectory()){
				for(String nom : fileEnfant.list()){ 
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(nom);
					lecteur.add(this.listFile( new File( this.carte.getAbsolutePath() + "\\" + file + "\\" + nom), node  ));
				}
			}
			this.add(lecteur);
		}
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
}

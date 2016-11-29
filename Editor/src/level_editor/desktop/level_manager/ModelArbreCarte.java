package level_editor.desktop.level_manager;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class ModelArbreCarte extends DefaultMutableTreeNode{

	private File carte;
	
	public ModelArbreCarte(File root){
		super("levels");
		this.carte = root;
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

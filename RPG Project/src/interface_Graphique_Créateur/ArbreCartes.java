package interface_Graphique_Créateur;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class ArbreCartes extends JTree implements TreeSelectionListener {

	private ButtonsSynchronisation boutons;
	private ModelArbreCarte model;

	public ArbreCartes(ButtonsSynchronisation boutons, ModelArbreCarte model){
		super(model);
		this.model = model;
		this.boutons = boutons;
		this.addTreeSelectionListener(this);
		this.setBackground(new Color(245, 245, 245));
		this.setBounds(5,50,170,500);
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		String nom = "";
		
		if (this.getLastSelectedPathComponent() != null) {
			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.getLastSelectedPathComponent();
			if (this.boutons.buttonAjoutCarte.isPeutCréerCarte()){

				nom = JOptionPane.showInputDialog(null, "Veuillez entrer le nom de la carte à créer : ", "Création de carte !", JOptionPane.QUESTION_MESSAGE);
				File file = new File(fileName(node, nom + ".txt"));
				try {
					file.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (this.boutons.buttonAjoutDossier.isPeutCréerDossier()){

				nom = JOptionPane.showInputDialog(null, "Veuillez entrer le nom du dossier à créer : ", "Création de dossier !", JOptionPane.QUESTION_MESSAGE);
				File file = new File(fileName(node, nom));
				file.mkdir();
			}
			
			if (this.boutons.buttonSupprimer.isPeutSupprimer()){
				new File(fileName(node,nom)).delete();
			}
		}
	}

	public String fileName(DefaultMutableTreeNode node, String fileName){
		if (node == this.model.getRoot())
			return "cartes" + "\\" + fileName;
		return fileName((DefaultMutableTreeNode) node.getParent(), node.toString() + "\\" + fileName);
	}
}

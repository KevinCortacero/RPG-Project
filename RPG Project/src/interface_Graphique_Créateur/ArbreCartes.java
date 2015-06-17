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
	protected ModelArbreCarte model;

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

		if (this.getLastSelectedPathComponent() != null) {

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.getLastSelectedPathComponent();

			if (this.boutons.buttonAjoutCarte.isPeutCréerCarte()){
				if (!node.toString().contains(".txt"))
					this.creerCarte(node);
				else
					JOptionPane.showMessageDialog(null, "Impossible d'ajouter une carte dans un fichier .txt", "Erreur", JOptionPane.ERROR_MESSAGE);
			}

			if (this.boutons.buttonAjoutDossier.isPeutCréerDossier()){
				if (!node.toString().contains(".txt"))
					this.creerDossier(node);
				else
					JOptionPane.showMessageDialog(null, "Impossible d'ajouter un dossier dans un fichier .txt", "Erreur", JOptionPane.ERROR_MESSAGE);
			}

			if (this.boutons.buttonSupprimer.isPeutSupprimer()){
				new File(fileName(node,"")).delete();
			}
			
			this.boutons.buttonAjoutCarte.setPeutCréerCarte(false);
			this.boutons.buttonAjoutDossier.setPeutCréerDossier(false);
			this.boutons.buttonAjoutCarte.setPeutCréerCarte(false);
		}
	}

	public String fileName(DefaultMutableTreeNode node, String fileName){
		if (node == this.model.getRoot())
			return "cartes" + "\\" + fileName;
		return fileName((DefaultMutableTreeNode) node.getParent(), node.toString() + "\\" + fileName);
	}

	public void creerCarte(DefaultMutableTreeNode node){
		String nom = JOptionPane.showInputDialog(null, "Veuillez entrer le nom de la carte à créer dans " + node.toString(), "Création de carte !", JOptionPane.QUESTION_MESSAGE);
		System.out.println("|"+nom+"|");
		

//		if (nom.is)

//			JOptionPane.showMessageDialog(null, "L'ajout de la carte à été annulé", "Information", JOptionPane.INFORMATION_MESSAGE);
		
		if (nom.trim().isEmpty())
			JOptionPane.showMessageDialog(null, "Le nom de Carte entré n'est pas valide (nul)", "Information", JOptionPane.INFORMATION_MESSAGE);	

		if (!nom.isEmpty() && nom != null) {
			try {
				new File(fileName(node, nom + ".txt")).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void creerCarteDepuisNouveauDossier(String nom){
		try {
			new File(nom + ".txt").createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void creerDossier(DefaultMutableTreeNode node){
		String nom = JOptionPane.showInputDialog(null, "Veuillez entrer le nom du dossier à créer : ", "Création de dossier !", JOptionPane.QUESTION_MESSAGE);
		if (nom != "" && nom != "null"){
			File file = new File(fileName(node, nom));
			file.mkdir();
			this.creerCarteDepuisNouveauDossier(file.getAbsolutePath() + "\\" + file.getName() + "-1");
		}
	}
}

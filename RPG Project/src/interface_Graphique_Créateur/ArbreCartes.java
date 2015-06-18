package interface_Graphique_Créateur;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

@SuppressWarnings("serial")
public class ArbreCartes extends JTree implements TreeSelectionListener {

	private ButtonsSynchronisation boutons;
	protected ModelArbreCarte model;
	private int[] tableauRow;

	public ArbreCartes(ButtonsSynchronisation boutons, ModelArbreCarte model){
		super(model);
		this.model = model;
		this.boutons = boutons;
		this.tableauRow = new int[40];
		this.sauvegarder();
		this.expandAll();
		this.addTreeSelectionListener(this);
		this.setBackground(new Color(245, 245, 245));
		this.setBounds(5,50,170,500);
	}

	public void expandAll() {  
		for (int row = 0; row < this.getRowCount() ; row++) {
			this.expandRow(row); 
		}
	}

	private void sauvegarder() {

		for (int row = 0; row < this.getRowCount() ; row++) {
			if (this.isExpanded(row))
				this.tableauRow[row] = 1;
			if (this.isCollapsed(row))
				this.tableauRow[row] = 0;
		}
	}

	private void mettreAJour() {
		((DefaultTreeModel) this.getModel()).setRoot(new ModelArbreCarte(new File("cartes")));

		for (int row = 0; row < this.getRowCount() ; row++) {

			if (this.tableauRow[row] == 1)
				this.expandRow(row);
			if (this.tableauRow[row] == 0)
				this.collapseRow(row);
		}
	}	

	public void reconstruireAjoutCarte(int rowClicked){

		this.mettreAJour();

		for (int i = this.getRowCount() ; i > rowClicked + 1 ; i--){
			this.tableauRow[i] = this.tableauRow[i-1];
		}
		this.tableauRow[rowClicked] = 1;
		this.tableauRow[rowClicked + 1] = 0;

		for (int row = 0; row < this.getRowCount() ; row++) {

			if (this.tableauRow[row] == 1)
				this.expandRow(row);
			if (this.tableauRow[row] == 0)
				this.collapseRow(row);
		}
	}

	public void reconstruireAjoutDossier(int rowClicked){

		this.mettreAJour();

		for (int i = this.getRowCount() ; i > rowClicked + 2 ; i--){
			this.tableauRow[i] = this.tableauRow[i-2];
		}
		this.tableauRow[rowClicked] = 1;
		this.tableauRow[rowClicked + 1] = 1;
		this.tableauRow[rowClicked + 2] = 0;

		for (int row = 0; row < this.getRowCount() ; row++) {

			if (this.tableauRow[row] == 1)
				this.expandRow(row);
			if (this.tableauRow[row] == 0)
				this.collapseRow(row);
		}
	}


	@Override
	public void valueChanged(TreeSelectionEvent e) {

		if (this.getLastSelectedPathComponent() != null) {

			this.sauvegarder();
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.getLastSelectedPathComponent();

			if (this.boutons.buttonAjoutCarte.isPeutCréerCarte()){
				if (node.toString().contains(".txt"))
					JOptionPane.showMessageDialog(null, "Impossible d'ajouter une carte dans un fichier .txt", "Erreur", JOptionPane.ERROR_MESSAGE);
				else {
					this.creerCarte(node);
					this.reconstruireAjoutCarte(this.getRowForPath(e.getPath()));
				}
			}

			if (this.boutons.buttonAjoutDossier.isPeutCréerDossier()){
				if (!node.toString().contains(".txt")){
					this.creerDossier(node);
					this.reconstruireAjoutDossier(this.getRowForPath(e.getPath()));
				}
				else
					JOptionPane.showMessageDialog(null, "Impossible d'ajouter un dossier dans un fichier .txt", "Erreur", JOptionPane.ERROR_MESSAGE);;
			}

			if (this.boutons.buttonSupprimer.isPeutSupprimer()){
				this.supprimer(node);
			}

			this.boutons.buttonAjoutCarte.setPeutCréerCarte(false);
			this.boutons.buttonAjoutDossier.setPeutCréerDossier(false);
			this.boutons.buttonSupprimer.setPeutSupprimer(false);
			this.sauvegarder();
			this.mettreAJour();
		}
	}

	private void supprimer(DefaultMutableTreeNode node) {

		int reponse = JOptionPane.showConfirmDialog(this,
				"Voulez-vous vraiment supprimer " + node.toString() + " ?",
				"Confirmation",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if(reponse == JOptionPane.YES_OPTION ){
			new File(fileName(node,"")).delete();	
		}	
	}

	public String fileName(DefaultMutableTreeNode node, String fileName){
		if (node.toString() == "Liste des cartes ")
			return "cartes" + "\\" + fileName;
		return fileName((DefaultMutableTreeNode) node.getParent(), node.toString() + "\\" + fileName);
	}

	public void creerCarte(DefaultMutableTreeNode node){
		String nom = JOptionPane.showInputDialog(null, "Veuillez entrer le nom de la carte à créer dans " + node.toString(), "Création de carte !", JOptionPane.QUESTION_MESSAGE);

		if (nom == null)
			JOptionPane.showMessageDialog(null, "L'ajout de la carte a été annulé", "Information", JOptionPane.INFORMATION_MESSAGE);

		else if (nom.trim().isEmpty())
			JOptionPane.showMessageDialog(null, "Le nom de Carte entré n'est pas valide (nul)", "Information", JOptionPane.INFORMATION_MESSAGE);	

		else if (!nom.isEmpty() && nom != null) {
			try {
				new File(fileName(node, nom + ".txt")).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void creerDossier(DefaultMutableTreeNode node){
		String nom = JOptionPane.showInputDialog(null, "Veuillez entrer le nom du dossier à créer : ", "Création de dossier !", JOptionPane.QUESTION_MESSAGE);

		if (nom == null)
			JOptionPane.showMessageDialog(null, "L'ajout du dossier a été annulé", "Information", JOptionPane.INFORMATION_MESSAGE);

		else if (nom.trim().isEmpty())
			JOptionPane.showMessageDialog(null, "Le nom de Carte entré n'est pas valide (nul)", "Information", JOptionPane.INFORMATION_MESSAGE);	

		else if (!nom.isEmpty() && nom != null) {
			File file = new File(fileName(node, nom));
			file.mkdir();
			this.creerCarteDepuisNouveauDossier(file.getAbsolutePath() + "\\" + file.getName() + "-1");
		}
	}

	public void creerCarteDepuisNouveauDossier(String nom){
		try {
			new File(nom + ".txt").createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
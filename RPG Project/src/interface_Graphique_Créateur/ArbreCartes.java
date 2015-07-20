package interface_Graphique_Cr�ateur;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

@SuppressWarnings("serial")
public class ArbreCartes extends JTree implements TreeSelectionListener, MouseListener {

	private ButtonsSynchronisation boutons;
	protected ModelArbreCarte model;
	private int[] tableauRow;
	private PanelPrincipalCr�ateur panel;

	public ArbreCartes(ButtonsSynchronisation boutons, ModelArbreCarte model, PanelPrincipalCr�ateur panel){
		super(model);
		this.model = model;
		this.boutons = boutons;
		this.tableauRow = new int[40];
		this.panel = panel;
		this.sauvegarder();
		this.expandAll();
		this.addTreeSelectionListener(this);
		this.setBackground(new Color(245, 245, 245));
		this.setBounds(5,50,170,500);
		this.addMouseListener(this);
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

			if (!this.boutons.buttonAjoutCarte.isPeutCr�erCarte() && !this.boutons.buttonAjoutDossier.isPeutCr�erDossier() && !this.boutons.buttonSupprimer.isPeutSupprimer() && this.getLastSelectedPathComponent().toString() != "Liste des cartes"){
				if (!this.panel.getMap().listeMapFile.containsKey(this.fileName((DefaultMutableTreeNode) node.getParent(), node.toString()))){
					File fileCarte = new File(this.fileName((DefaultMutableTreeNode) node.getParent(), node.toString()));
					if (fileCarte.isFile()){
						this.panel.getMap().listeMapFile.put(this.fileName((DefaultMutableTreeNode) node.getParent(), node.toString()), new MapFile(this.panel.getMap(),fileCarte));
						this.panel.getMap().changerMapFile(fileCarte);
						this.panel.getMap().mapFile.chargerCarteActuelle();
						this.panel.repaint();
					}
				}
				else {
					
					this.panel.getMap().changerMapFile(this.panel.getMap().listeMapFile.get(this.fileName((DefaultMutableTreeNode) node.getParent(), node.toString())).currentFile);
					this.panel.getMap().mapFile.chargerCarteActuelle();
					this.panel.repaint();
				}
			}

			if (this.boutons.buttonAjoutCarte.isPeutCr�erCarte()){
				if (node.toString().contains(".txt"))
					JOptionPane.showMessageDialog(null, "Impossible d'ajouter une carte dans un fichier .txt", "Erreur", JOptionPane.ERROR_MESSAGE);
				else {
					this.creerCarte(node);
					this.reconstruireAjoutCarte(this.getRowForPath(e.getPath()));
					this.mettreAJour();
				}
			}

			if (this.boutons.buttonAjoutDossier.isPeutCr�erDossier()){
				if (!node.toString().contains(".txt")){
					this.creerDossier(node);
					this.reconstruireAjoutDossier(this.getRowForPath(e.getPath()));
					this.mettreAJour();
				}
				else
					JOptionPane.showMessageDialog(null, "Impossible d'ajouter un dossier dans un fichier .txt", "Erreur", JOptionPane.ERROR_MESSAGE);
			}

			if (this.boutons.buttonSupprimer.isPeutSupprimer()){
				this.supprimer(node);
				this.mettreAJour();
			}
			this.boutons.setFalse();
			this.sauvegarder();
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
		if (node.toString() == "Liste des cartes")
			return "cartes" + "\\" + fileName;
		return fileName((DefaultMutableTreeNode) node.getParent(), node.toString() + "\\" + fileName);
	}

	public void creerCarte(DefaultMutableTreeNode node){
		String nom = JOptionPane.showInputDialog(null, "Veuillez entrer le nom de la carte � cr�er dans " + node.toString(), "Cr�ation de carte !", JOptionPane.QUESTION_MESSAGE);
		if (nom == null)

			JOptionPane.showMessageDialog(null, "L'ajout de la carte � �t� annul�", "Information", JOptionPane.INFORMATION_MESSAGE);

		else if (nom.trim().isEmpty())
			JOptionPane.showMessageDialog(null, "Le nom de Carte entr� n'est pas valide (nul)", "Information", JOptionPane.INFORMATION_MESSAGE);	

		else if (!nom.isEmpty() && nom != null) {
			try {
				new File(fileName(node, nom + ".txt")).createNewFile();
				 FileWriter writer = new FileWriter(fileName(node, nom + ".txt"));
		     		   try {
			                writer.write("0  0");

			            } finally {
			                writer.close();
			            }
				} catch (IOException e) {
					e.printStackTrace();
				}
//				try {
//					lireFichierTexte(nom + ".txt");
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			
		}
	}

	public void creerDossier(DefaultMutableTreeNode node){
		String nom = JOptionPane.showInputDialog(null, "Veuillez entrer le nom du dossier � cr�er : ", "Cr�ation de dossier !", JOptionPane.QUESTION_MESSAGE);

		if (nom == null)
			JOptionPane.showMessageDialog(null, "L'ajout du dossier a �t� annul�", "Information", JOptionPane.INFORMATION_MESSAGE);

		else if (nom.trim().isEmpty())
			JOptionPane.showMessageDialog(null, "Le nom de Carte entr� n'est pas valide (nul)", "Information", JOptionPane.INFORMATION_MESSAGE);	

		else if (!nom.isEmpty() && nom != null) {
			File file = new File(fileName(node, nom));
			file.mkdir();
			this.creerCarteDepuisNouveauDossier(file.getAbsolutePath() + "\\" + file.getName() + "-1");
		}
	}

	public void creerCarteDepuisNouveauDossier(String nom){
		try {
			new File(nom + ".txt").createNewFile();
			 FileWriter writer = new FileWriter(nom + ".txt");
			   try {
	                writer.write("0  0");

	            } finally {
	                writer.close();
	            }
		} catch (IOException e) {
			e.printStackTrace();
		}
//		try {
//			lireFichierTexte(nom + ".txt");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
	
//	public void lireFichierTexte(String nom) throws IOException{
//		BufferedReader in = new BufferedReader(new FileReader(nom));
//		String line;
//		while ((line = in.readLine()) != null)
//		{
//			  System.out.println (line);
//		}
//		in.close();
//
//	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3)
			this.boutons.setFalse();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

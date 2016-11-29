package level_editor.desktop.level_manager;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import level_editor.desktop.level_designer.LevelDesigner;

@SuppressWarnings("serial")
public class GameLevelTree extends JTree implements TreeSelectionListener, MouseListener {

	private ButtonsSynchronisation boutons;
	protected ModelArbreCarte model;
	private int[] tableauRow;

	public GameLevelTree(ButtonsSynchronisation boutons, ModelArbreCarte model) {
		super(model);
		this.model = model;
		this.boutons = boutons;
		this.tableauRow = new int[40];
		this.sauvegarder();
		this.expandAll();
		this.addTreeSelectionListener(this);
		this.setBackground(new Color(245, 245, 245));
		this.setBounds(5, 50, 170, 500);
		this.addMouseListener(this);
	}

	public void expandAll() {
		for (int row = 0; row < this.getRowCount(); row++) {
			this.expandRow(row);
		}
	}

	private void sauvegarder() {

		for (int row = 0; row < this.getRowCount(); row++) {
			if (this.isExpanded(row))
				this.tableauRow[row] = 1;
			if (this.isCollapsed(row))
				this.tableauRow[row] = 0;
		}
	}

	private void mettreAJour() {
		((DefaultTreeModel) this.getModel()).setRoot(new ModelArbreCarte(new File("cartes")));

		for (int row = 0; row < this.getRowCount(); row++) {

			if (this.tableauRow[row] == 1)
				this.expandRow(row);
			if (this.tableauRow[row] == 0)
				this.collapseRow(row);
		}
	}

	public void reconstruireAjoutCarte(int rowClicked) {

		this.mettreAJour();

		for (int i = this.getRowCount(); i > rowClicked + 1; i--) {
			this.tableauRow[i] = this.tableauRow[i - 1];
		}
		this.tableauRow[rowClicked] = 1;
		this.tableauRow[rowClicked + 1] = 0;

		for (int row = 0; row < this.getRowCount(); row++) {

			if (this.tableauRow[row] == 1)
				this.expandRow(row);
			if (this.tableauRow[row] == 0)
				this.collapseRow(row);
		}
	}

	public void reconstruireAjoutDossier(int rowClicked) {

		this.mettreAJour();

		for (int i = this.getRowCount(); i > rowClicked + 2; i--) {
			this.tableauRow[i] = this.tableauRow[i - 2];
		}
		this.tableauRow[rowClicked] = 1;
		this.tableauRow[rowClicked + 1] = 1;
		this.tableauRow[rowClicked + 2] = 0;

		for (int row = 0; row < this.getRowCount(); row++) {

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

			if (!this.boutons.buttonAjoutCarte.isPeutCréerCarte()
					&& !this.boutons.buttonAjoutDossier.isPeutCréerDossier()
					&& !this.boutons.buttonSupprimer.isPeutSupprimer()
					&& this.getLastSelectedPathComponent().toString() != "levels") {
				String path = this.fileName((DefaultMutableTreeNode) node.getParent(), node.toString());
				if (!LevelDesigner.getPanel().getLevelContainer().getListeLevel().containsKey(path)) {
					File fileCarte = new File(path);
					if (fileCarte.isFile()) {
						LevelDesigner.getPanel().getLevelContainer().addGameLevel(path);
						LevelDesigner.getPanel().getLevelContainer().changerLevel(path);
					}
				} 
				else{
					LevelDesigner.getPanel().getLevelContainer().changerLevel(path);
				}
				LevelDesigner.getPanel().repaint();
			}

			if (this.boutons.buttonAjoutCarte.isPeutCréerCarte()) {
				if (node.toString().contains(".txt"))
					JOptionPane.showMessageDialog(null, "Impossible d'ajouter une carte dans un fichier .txt", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				else {
					try {
						this.createGameLevel(node);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					this.reconstruireAjoutCarte(this.getRowForPath(e.getPath()));
					this.mettreAJour();
				}
			}

			if (this.boutons.buttonAjoutDossier.isPeutCréerDossier()) {
				if (!node.toString().contains(".txt")) {
					this.creerDossier(node);
					this.reconstruireAjoutDossier(this.getRowForPath(e.getPath()));
					this.mettreAJour();
				} else
					JOptionPane.showMessageDialog(null, "Impossible d'ajouter un dossier dans un fichier .txt",
							"Erreur", JOptionPane.ERROR_MESSAGE);
			}

			if (this.boutons.buttonSupprimer.isPeutSupprimer()) {
				this.supprimer(node);
				this.mettreAJour();
			}
			this.boutons.setFalse();
			this.sauvegarder();
		}
	}

	private void supprimer(DefaultMutableTreeNode node) {

		int reponse = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment supprimer " + node.toString() + " ?",
				"Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (reponse == JOptionPane.YES_OPTION) {
			new File(fileName(node, "")).delete();
		}
	}

	public String fileName(DefaultMutableTreeNode node, String fileName) {
		if (node.toString() == "levels")
			return "levels" + "\\" + fileName;
		return fileName((DefaultMutableTreeNode) node.getParent(), node.toString() + "\\" + fileName);
	}

	public void createGameLevel(DefaultMutableTreeNode node) throws Exception {
		String nom = JOptionPane.showInputDialog(null, "LEVEL NAME IN " + node.toString(), "CREATE LEVEL",
				JOptionPane.QUESTION_MESSAGE);
		if (nom == null)
			JOptionPane.showMessageDialog(null, "CANCELED...", "CANCELED...", JOptionPane.INFORMATION_MESSAGE);

		else if (nom.trim().isEmpty())
			JOptionPane.showMessageDialog(null, "EMPTY NAME", "CANCELED...", JOptionPane.INFORMATION_MESSAGE);

		else {
			String path = fileName(node, nom + ".txt");
			LevelDesigner.getPanel().getLevelContainer().addGameLevel(path);
		}
	}

	public void creerDossier(DefaultMutableTreeNode node) {
		String nom = JOptionPane.showInputDialog(null, "Veuillez entrer le nom du dossier à créer : ",
				"Création de dossier !", JOptionPane.QUESTION_MESSAGE);

		if (nom == null)
			JOptionPane.showMessageDialog(null, "L'ajout du dossier a été annulé", "Information",
					JOptionPane.INFORMATION_MESSAGE);

		else if (nom.trim().isEmpty())
			JOptionPane.showMessageDialog(null, "Le nom de Carte entré n'est pas valide (nul)", "Information",
					JOptionPane.INFORMATION_MESSAGE);

		else {
			File file = new File(fileName(node, nom));
			file.mkdir();
			LevelDesigner.getPanel().getLevelContainer().addGameLevel(file.getAbsolutePath() + "\\" + file.getName() + "-1");
		}
	}

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
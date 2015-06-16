package interface_Graphique_Cr�ateur;

import java.awt.Color;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

@SuppressWarnings("serial")
public class ArbreCartes extends JTree implements TreeSelectionListener   {

	private ButtonsSynchronisation boutons;
	
	public ArbreCartes(ButtonsSynchronisation boutons){
		super(new ModelArbreCarte());
		this.boutons = boutons;
		this.addTreeSelectionListener(this);
		this.setBackground(new Color(245, 245, 245));
		this.setBounds(5,50,170,500);
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		if (this.getLastSelectedPathComponent() != null) {
			if (this.boutons.buttonAjoutCarte.isPeutCr�erCarte())
				System.out.println("On peut cr�er une carte ici : " + this.getLastSelectedPathComponent().toString());
			if (this.boutons.buttonAjoutDossier.isPeutCr�erDossier())
				System.out.println("On peut cr�er un dossier ici : " + this.getLastSelectedPathComponent().toString());
			if (this.boutons.buttonSupprimer.isPeutSupprimer())
				System.out.println("On peut supprimer  : " + this.getLastSelectedPathComponent().toString());
		}
	}
}


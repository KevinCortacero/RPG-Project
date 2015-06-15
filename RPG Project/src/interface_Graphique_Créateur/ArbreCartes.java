package interface_Graphique_Créateur;

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
		this.setBounds(5,100,170,500);


	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		if (this.getLastSelectedPathComponent() != null) {
			System.out.println(this.getLastSelectedPathComponent().toString());
		}
	}

}

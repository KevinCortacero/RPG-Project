package interface_Graphique_Créateur;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Onglet extends JPanel {

	private GridBagConstraints gridBagConstraints;
	private File file;

	public Onglet(String fileName){
		super();
		this.setLayout(new GridBagLayout());
		this.file = new File("images" + "\\" + fileName);
		this.gridBagConstraints = new GridBagConstraints();
		this.gridBagConstraints.gridx = 0;
		this.gridBagConstraints.gridy = 0;  
		this.gridBagConstraints.gridheight = 1;
		this.gridBagConstraints.gridwidth = 1;
		this.gridBagConstraints.insets = new Insets(5, 5, 5, 5);
		PanelChoixObjetsCréateur panelObjets = PanelChoixObjetsCréateur.getPanel();
		
		for (File sousfile : this.file.listFiles()){
			if (sousfile.isFile()){
				panelObjets.listeImageNuméro.put(panelObjets.getNbObjets(), new ImageIcon("images" + "\\" + this.file.getName() + "\\" + sousfile.getName()));
				this.add(new ObjetIcone(new ImageIcon("images" + "\\" + this.file.getName() + "\\" + sousfile.getName()).getImage(), panelObjets.getNbObjets()), this.gridBagConstraints);
				
				this.gridBagConstraints.gridx ++;
				panelObjets.setNbObjets(panelObjets.getNbObjets() + 1);
				if (this.gridBagConstraints.gridx >= 20){
					this.gridBagConstraints.gridx = 0;
					this.gridBagConstraints.gridy ++;
				}
			}
		}
	}
}
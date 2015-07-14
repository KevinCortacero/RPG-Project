package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PanelChoixObjetsCréateur extends JPanel{

	private int nbObjets;
	protected JTabbedPane onglets;
	protected java.util.Map<Integer, ImageIcon> listeImageNuméro;

	public PanelChoixObjetsCréateur(PanelPrincipalCréateur panel){
		super();
		this.listeImageNuméro = new HashMap<Integer, ImageIcon>();
		this.setBorder(new BorderGray());
		this.nbObjets = 1;
		this.setBackground(new Color(245, 245, 245));
		this.setBounds(200, 10, Toolkit.getDefaultToolkit().getScreenSize().width - 210, 180);
		onglets = new JTabbedPane(SwingConstants.TOP);
		onglets.setPreferredSize(new Dimension(this.getWidth()-20, this.getHeight()-20));
		for (File file : new File("images").listFiles()){
			if (file.isDirectory())
				onglets.addTab(file.getName(), new Onglet(panel, file.getName(), this));
		}
		this.add(onglets);
	}

	public int getNbObjets() {
		return this.nbObjets;
	}

	public void setNbObjets(int nb) {
		this.nbObjets = nb;	
	}
	
	public void raffraichir(){
		onglets.setPreferredSize(new Dimension(this.getWidth()-20, this.getHeight()-20));
	}
}

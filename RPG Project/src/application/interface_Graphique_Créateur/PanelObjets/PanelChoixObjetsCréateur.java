package application.interface_Graphique_Créateur.PanelObjets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import application.interface_Graphique_Créateur.BorderGray;
import application.interface_Graphique_Créateur.FrameCréateur;
import application.interface_Graphique_Créateur.SousPanel;
import application.interface_Graphique_Créateur.PanelPrincipal.LevelContainer;
import application.interface_Graphique_Créateur.PanelPrincipal.PanelPrincipalCréateur;

@SuppressWarnings("serial")
public class PanelChoixObjetsCréateur extends SousPanel{

	private static PanelChoixObjetsCréateur instance;
	private int nbObjets;
	private JTabbedPane onglets;
	private Map<Integer, ImageIcon> listeImageNuméro;

	
	public static PanelChoixObjetsCréateur getPanel(){
		if (instance == null){
			instance = new PanelChoixObjetsCréateur();
			instance.setBounds(200, 10 , FrameCréateur.getFrame().getWidth() - 230, 200 );
			instance.créerOnglets();
		}
		return instance;
	}

	private PanelChoixObjetsCréateur(){
		super();
		this.listeImageNuméro = new HashMap<Integer, ImageIcon>();
		this.setBorder(new BorderGray());
		this.nbObjets = 1;
		this.setBackground(new Color(245, 245, 245));
		this.setBounds(200, 10, Toolkit.getDefaultToolkit().getScreenSize().width - 210, 180);
		this.onglets = new JTabbedPane(SwingConstants.TOP);
		this.onglets.setPreferredSize(new Dimension(this.getWidth()-20, this.getHeight()-20));
	}
	
	private void créerOnglets(){
		for (File file : new File("images").listFiles()){
			if (file.isDirectory())
				this.onglets.addTab(file.getName(), new Onglet(file.getName()));
		}
		this.add(this.onglets);
	}
	
	public Map<Integer, ImageIcon> getListeImageNuméro() {
		return listeImageNuméro;
	}

	public int getNbObjets() {
		return this.nbObjets;
	}

	public void setNbObjets(int nb) {
		this.nbObjets = nb;	
	}

	public void raffraichir(){
		this.setBounds(200, 10 , FrameCréateur.getFrame().getWidth() - 230, 180 );
		this.onglets.setPreferredSize(new Dimension(this.getWidth()-20, this.getHeight()-20));
		this.repaint();
	}
}
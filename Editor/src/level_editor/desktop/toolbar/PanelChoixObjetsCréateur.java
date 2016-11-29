package level_editor.desktop.toolbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import level_editor.desktop.BorderGray;
import level_editor.desktop.FrameCr�ateur;
import level_editor.desktop.SousPanel;

@SuppressWarnings("serial")
public class PanelChoixObjetsCr�ateur extends SousPanel{

	private static PanelChoixObjetsCr�ateur instance;
	private int nbObjets;
	private JTabbedPane onglets;
	private Map<Integer, ImageIcon> listeImageNum�ro;

	
	public static PanelChoixObjetsCr�ateur getPanel(){
		if (instance == null){
			instance = new PanelChoixObjetsCr�ateur();
			instance.setBounds(200, 10 , FrameCr�ateur.getFrame().getWidth() - 230, 200 );
			instance.cr�erOnglets();
		}
		return instance;
	}

	private PanelChoixObjetsCr�ateur(){
		super();
		this.listeImageNum�ro = new HashMap<Integer, ImageIcon>();
		this.setBorder(new BorderGray());
		this.nbObjets = 1;
		this.setBackground(new Color(245, 245, 245));
		this.setBounds(200, 10, Toolkit.getDefaultToolkit().getScreenSize().width - 210, 180);
		this.onglets = new JTabbedPane(SwingConstants.TOP);
		this.onglets.setPreferredSize(new Dimension(this.getWidth()-20, this.getHeight()-20));
	}
	
	private void cr�erOnglets(){
		for (File file : new File("images").listFiles()){
			if (file.isDirectory())
				this.onglets.addTab(file.getName(), new Onglet(file.getName()));
		}
		this.add(this.onglets);
	}
	
	public Map<Integer, ImageIcon> getListeImageNum�ro() {
		return listeImageNum�ro;
	}

	public int getNbObjets() {
		return this.nbObjets;
	}

	public void setNbObjets(int nb) {
		this.nbObjets = nb;	
	}
}
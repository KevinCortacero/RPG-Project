package interface_Graphique_Cr�ateur;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class PanelChoixObjetsCr�ateur extends JPanel{

	private JTextArea titre;
	
	public PanelChoixObjetsCr�ateur(){
		super(null);
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(200, 10, Toolkit.getDefaultToolkit().getScreenSize().width - 210 , 180);
		this.titre = new JTextArea(100,100);
		this.titre.append("SALUT");
		this.titre.setBorder(new BorderGray());
		this.add(this.titre);
	}
}

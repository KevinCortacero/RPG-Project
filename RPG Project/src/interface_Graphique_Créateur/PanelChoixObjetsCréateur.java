package interface_Graphique_Cr�ateur;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelChoixObjetsCr�ateur extends JPanel{

	public PanelChoixObjetsCr�ateur(){
		super(null);
		this.setBorder(new BorderGray(1));
		this.setBackground(new Color(250,250,250));
		this.setBounds(200, 10, Toolkit.getDefaultToolkit().getScreenSize().width - 210 , 180);
	}
}

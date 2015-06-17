package interface_Graphique_Cr�ateur;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelGestionCr�ateur extends JPanel {

	private JLabel titre;
	private ArbreCartes arbre;
	private ButtonsSynchronisation boutons;
	
	public PanelGestionCr�ateur() {
		super();
		this.setLayout(null);
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245, 245, 245));
		this.setBounds(10, 200, 180, Toolkit.getDefaultToolkit().getScreenSize().height - 270);
		this.boutons = new ButtonsSynchronisation();
		this.boutons.setBounds(5, (int)this.getSize().getHeight()-120,170, 120);
		this.add(this.boutons);
		this.titre = new JLabel("Gestion des cartes");
		this.titre.setFont(new Font("Arial", 18, 18));
		this.titre.setBounds(10, 10, 160, 40);
		this.add(this.titre);
		this.arbre = new ArbreCartes(this.boutons, new ModelArbreCarte(new File("cartes")));
		this.add(this.arbre);
	}
	
	
	public void raffraichir(){
		int coordon�sY;
		if ( (int)this.getSize().getHeight()-120 > 150 ){
			coordon�sY = (int)this.getSize().getHeight()-120;
		}else{
			coordon�sY = 150 ;
		}
		this.boutons.repaint();
		this.boutons.setBounds(5,coordon�sY,170, 112);
		this.arbre.setBounds(5, 50, 170, this.getHeight() - this.boutons.getHeight() - 66);
	}
}

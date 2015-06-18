package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import jeu.ImageTest;


@SuppressWarnings("serial")
public class PanelChoixObjetsCréateur extends JPanel implements ActionListener{

	public PanelChoixObjetsCréateur() {
		super();
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245, 245, 245));
		this.setBounds(200, 10,
				Toolkit.getDefaultToolkit().getScreenSize().width - 210, 180);
	
		this.repaint();
		
		// onglet 1 
		JTabbedPane onglets = new JTabbedPane(SwingConstants.TOP);
		JPanel onglet1 = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		onglet1.setLayout(gridBagLayout);
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;  
		gridBagConstraints.gridheight = 1;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);
	    for ( int i = 0; i<2; i++){
	    	gridBagConstraints.gridy = i;
	    	for ( int j = 0 ; j < 20; j++){
	    		gridBagConstraints.gridx = j;
	    		if ( i == 1 ){
	    			onglet1.add(new ButtonImageParametre(new ImageTest("souris").getImageIcon().getImage()),gridBagConstraints);
	    		}else{
	    			onglet1.add(new ButtonImageParametre(new ImageTest("lion").getImageIcon().getImage()),gridBagConstraints);
	    		}
	    	}
	    }

		onglet1.setPreferredSize(new Dimension((int)this.getSize().getWidth()-20, (int)this.getSize().getHeight()-40));
		onglets.addTab("onglet1", onglet1);

		//onglet 2
		JPanel onglet2 = new JPanel();
		JLabel titreOnglet2 = new JLabel("Onglet 2");
		onglet2.add(titreOnglet2);
		onglet2.setPreferredSize(new Dimension(300, 80));
		onglets.addTab("onglet2", onglet2);
		this.add(onglets);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


	
}

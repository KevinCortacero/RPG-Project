package interface_Graphique_Créateur;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelValidationCréateur extends JPanel{

	private JButton enregister;
	private JButton enregistrerEtQuitter;
	private PanelPrincipalCréateur panel;
	
	public PanelValidationCréateur(PanelPrincipalCréateur panel){
		super();
		this.panel = panel;
		this.setLayout(new GridLayout(3,1));
		this.enregister = new JButton("Enregistrer");
		this.enregister.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					panel.getMap().sauvegarder();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		this.enregistrerEtQuitter = new JButton("Enregistrer et Quitter");
		this.add(this.enregister);
		this.add(this.enregistrerEtQuitter);
		this.add( new ButtonQuit());
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(10, 10, 180 , 180);
	}
}

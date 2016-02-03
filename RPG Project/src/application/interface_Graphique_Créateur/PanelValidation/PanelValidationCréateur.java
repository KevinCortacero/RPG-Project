package application.interface_Graphique_Créateur.PanelValidation;

import java.awt.Color;
import java.awt.GridLayout;

import application.interface_Graphique_Créateur.BorderGray;
import application.interface_Graphique_Créateur.SousPanel;

@SuppressWarnings("serial")
public class PanelValidationCréateur extends SousPanel{
	
	private static PanelValidationCréateur instance;
	
	public static PanelValidationCréateur getPanel(){
		if (instance == null)
			instance = new PanelValidationCréateur();
		return instance;
	}
	
	private PanelValidationCréateur(){
		super();
		this.setLayout(new GridLayout(3,1));
		this.add(new ButtonEnregistrer());
		this.add(new ButtonSaveAndQuit());
		this.add( new ButtonQuit());
		this.setBorder(new BorderGray());
		this.setBackground(new Color(245,245,245));
		this.setBounds(10, 10, 180 , 180);
	}

	@Override
	public void raffraichir() {
		this.repaint();		
	}
}

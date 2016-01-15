package interface_Graphique_Cr�ateur;

import java.awt.Color;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class PanelValidationCr�ateur extends SousPanel{
	
	private static PanelValidationCr�ateur instance;
	
	public static PanelValidationCr�ateur getPanel(){
		if (instance == null)
			instance = new PanelValidationCr�ateur();
		return instance;
	}
	
	private PanelValidationCr�ateur(){
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

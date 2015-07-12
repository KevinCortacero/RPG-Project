package interface_Graphique_Créateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ButtonEnregistrer extends JButton implements ActionListener {

	private Map map;
	
	public ButtonEnregistrer(Map map){
		super("Enregistrer");
		this.map = map;
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			System.out.println(this.map.mapFile.currentFile.getAbsoluteFile() + "*******");
			this.map.mapFile.sauvegarder();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
}

package level_editor.desktop.PanelValidation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import level_editor.desktop.level_designer.LevelDesigner;

@SuppressWarnings("serial")
public class ButtonEnregistrer extends JButton implements ActionListener {
	
	public ButtonEnregistrer(){
		super("Sauvegarder la carte");
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			LevelDesigner.getPanel().getLevelContainer().getLevel().sauvegarder();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
	
}

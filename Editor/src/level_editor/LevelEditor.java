package level_editor;

import java.io.IOException;

import level_editor.desktop.FrameCr�ateur;

public class LevelEditor {

	public static void main(String[] args) throws IOException {
		FrameCr�ateur f = FrameCr�ateur.getFrame();
		f.setVisible(true);
		
		while ( f.isEnabled() ){
			f.raffraichir();		
			try {
			      Thread.sleep(100);
			    } catch (InterruptedException e) {
			      e.printStackTrace();
			    }
		}
	}
}
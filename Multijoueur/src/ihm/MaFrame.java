package ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MaFrame extends JFrame{


	private static final long serialVersionUID = 1L;
	private JTextArea textArea ;
	private JPanel jp;

	public MaFrame() {
		this.setTitle("Serveur");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setVisible(true);
		this.setLayout(new BorderLayout());

		this.jp = new JPanel();
		this.jp.setSize(this.getWidth()-20,this.getHeight()-20);
		
		this.textArea = new JTextArea();
		this.textArea.setText("[SERVEUR] initialisation\n");
		this.textArea.setVisible(true);
		this.textArea.setPreferredSize(this.jp.getSize());
		
		this.jp.add(this.textArea);
		
		this.setContentPane(this.jp);
	}

	public void sysout(String message){
		message = this.textArea.getText() + message +"\n";
		this.textArea.setText(message);
	}
	
	
	public void sysoutErreur(String message){
		message = this.textArea.getText() +"---> ERREUR "+ message +"\n";
		this.textArea.setText(message);
	}
}

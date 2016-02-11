package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextArea;

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
		this.textArea.setText("\tOUVERTURE DE LA CONSOLE SERVEUR\n\n");
		this.textArea.setVisible(true);
		this.textArea.setPreferredSize(this.jp.getSize());
		this.textArea.setBackground(Color.BLACK);
		this.textArea.setForeground(Color.WHITE);
		
		this.jp.add(this.textArea);
		
		this.setContentPane(this.jp);
	}

	public void sysout(String message){
		message = this.textArea.getText() + message +"\n";
		this.textArea.setText(message);
	}
	
	
	public void sysoutErreur(String message){
		TextArea erreur = new TextArea("---> ERREUR "+ message +"\n");
		message = this.textArea.getText() + erreur;
		this.textArea.setText(message);
	}
}

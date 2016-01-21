import javax.swing.JFrame;


public class Interface extends JFrame{

	private Panel panel;
	
	public Interface(){
		
	    this.setTitle("Ma première fenêtre Java");
	    this.setSize(600,600);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
	    this.setVisible(true);
	    this.getContentPane().add(new Panel());
	}
	
}

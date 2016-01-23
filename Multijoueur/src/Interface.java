import javax.swing.JFrame;


public class Interface extends JFrame{

	private static Panel panel;
	
	public Interface(){
		super("1 vs 1");
	    this.setVisible(true);
	    this.setSize(600,600);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      	
	    panel = new Panel();
	    this.addKeyListener(panel);
	    this.add(panel);
	    panel.revalidate();		
	}
}

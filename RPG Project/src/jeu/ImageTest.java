package jeu;


import javax.swing.ImageIcon;


public class ImageTest {

	private ImageIcon image;
	public ImageTest(String nomImage){
		this.image = new ImageIcon("images/" + nomImage +".jpg");
		
	}
	
	public ImageIcon getImageIcon(){
		return this.image;
	}
}

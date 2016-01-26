package tests;

import static org.junit.Assert.assertEquals;
import interface_Graphique_Créateur.FrameCréateur;

import org.junit.Before;
import org.junit.Test;

public class TestFrame {

	private FrameCréateur f;
	
	@Before
	public void setUp(){
		this.f = FrameCréateur.getFrame();
	}
	
	@Test
	public void testTitle() {
		assertEquals(this.f.getTitle(), "Création de niveau");
	}
}

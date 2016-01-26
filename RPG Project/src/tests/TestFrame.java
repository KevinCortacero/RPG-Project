package tests;

import static org.junit.Assert.assertEquals;
import interface_Graphique_Cr�ateur.FrameCr�ateur;

import org.junit.Before;
import org.junit.Test;

public class TestFrame {

	private FrameCr�ateur f;
	
	@Before
	public void setUp(){
		this.f = FrameCr�ateur.getFrame();
	}
	
	@Test
	public void testTitle() {
		assertEquals(this.f.getTitle(), "Cr�ation de niveau");
	}
}

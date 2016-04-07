package jeu;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import personnage.GameObject;
import personnage.H�ros;

public class MainTest {

	private Frame frame;
	private GameObject h;
	@Before
	public void setUp() throws Exception {
		this.frame = new Frame();
		this.h = new H�ros(200,200,50,50,3,3);
	}

	@After
	public void tearDown() throws Exception {
		this.frame = null;
	}

	@Test
	public void test() {
		assertTrue(this.h.sprite.coordonn�e2D.getX().getComposante() > 0);
	}

}

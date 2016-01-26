import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class JTestDistributeur {

	private Distributeur d1;
	private List<Couple> listeTest;
	private final int RECHARGE = 10;

	@Parameter(value = 0)
	public int montant;
	@Parameter(value = 1)
	public int result;
	@Parameter(value = 2)
	public Couple couple1;
	@Parameter(value = 3)
	public Couple couple2;
	@Parameter(value = 4)
	public Couple couple3;

	@Before
	public void setUp(){
		this.d1 = new Distributeur(0,0,0);
		this.d1.recharger(this.RECHARGE,this.RECHARGE,this.RECHARGE);
		this.listeTest = this.d1.donnerBillets(this.montant);
	}

	// creates the test data
	@Parameters(name = "Test {index} : Demandé : {0}; Dû : {1}")
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
				{10,0,new Couple(10,1),null,null},
				{20,0,new Couple(10,2),null,null},
				{30,0,new Couple(20,1),new Couple(10,1),null},
				{40,0,new Couple(20,1),new Couple(10,2),null},
				{50,0,new Couple(20,2),new Couple(10,1),null},
				{60,0,new Couple(20,2),new Couple(10,2),null},
				{70,0,new Couple(20,3),new Couple(10,1),null},
				{100,0,new Couple(50,1),new Couple(20,2),new Couple(10,1)},
				{110,0,new Couple(50,1),new Couple(20,2),new Couple(10,2)},
				{210,0,new Couple(50,2),new Couple(20,5),new Couple(10,1)},
				{310,0,new Couple(50,3),new Couple(20,7),new Couple(10,2)},
				{3000,2200,new Couple(50,10),new Couple(20,10),new Couple(10,10)}
		};
		return Arrays.asList(data);
	}

	@Test
	public void testMontant(){
		assertEquals(this.d1.montantRestantDû(this.listeTest ,this.montant), this.result);
	}

	@Test
	public void testBillets(){
		assertEquals(this.listeTest.get(0).toString(),this.couple1.toString());
		if (this.couple2 != null)
			assertEquals(this.couple2.toString(), this.listeTest.get(1).toString());

		if (this.couple3 != null)
			assertEquals(this.listeTest.get(2).toString(),this.couple3.toString());
	}

	@Test
	public void testDistributeur(){		
		assertEquals( this.RECHARGE - this.listeTest.get(0).nombreBilletsDélivrés,this.d1.getEtat().getNbDisponible(this.listeTest.get(0).valeurBillet));	
		if (this.couple2 != null)
			assertEquals( this.RECHARGE - this.listeTest.get(1).nombreBilletsDélivrés,this.d1.getEtat().getNbDisponible(this.listeTest.get(1).valeurBillet));
		if (this.couple3 != null)
			assertEquals( this.RECHARGE - this.listeTest.get(2).nombreBilletsDélivrés,this.d1.getEtat().getNbDisponible(this.listeTest.get(2).valeurBillet));
	}
}
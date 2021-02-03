package question1;


public class ChocolateTest extends junit.framework.TestCase{
	private Chocolate choco;



	public void setUp(){
	    choco = new Chocolate();
	}

	
	
 	public void testChocolate(){
 	    assertEquals(2.1, choco.cost(), 0.1);
 	}
 
	public void testChocolateWhip(){
	    Beverage chocoSimpleWhip = new Whip(choco);
 
	    assertEquals(2.2, chocoSimpleWhip.cost(), 0.01);
 	}
 	
 	public void testChocolateWhipWhip(){
 	    Beverage chocoDoubleWhip = new Whip(new Whip(choco));
 
 	    assertEquals(2.3, chocoDoubleWhip.cost(), 0.01);
 	}
	
 	public void testChocolateWhipSoyMocha(){
 	    Beverage chocoWhipSoyMocha = new Mocha(new Soy(new Whip(choco)));
 
 	    assertEquals(2.55, chocoWhipSoyMocha.cost(), 0.01);
	}
	
	public void testDecafMilk(){
	    Beverage decafMilk = new Milk(new Decaf());
	    
	    assertEquals(1.15, decafMilk.cost(), 0.01);
	}
}


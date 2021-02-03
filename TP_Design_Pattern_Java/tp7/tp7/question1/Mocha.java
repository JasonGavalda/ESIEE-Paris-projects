package question1;
/**
 * extrait de http://www.oreilly.com/catalog/hfdesignpat/
 */
public class Mocha extends CondimentDecorator {
 
	public Mocha(Beverage beverage) {
		super(beverage);
	}
 
	public String getDescription() {
		return super.getDescription() + ", Mocha";
	}
 
	public double cost() {
		return .20 + super.cost();
	}
}

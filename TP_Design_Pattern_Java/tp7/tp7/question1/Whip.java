package question1;
/**
 * extrait de http://www.oreilly.com/catalog/hfdesignpat/
 */
public class Whip extends CondimentDecorator {
 
	public Whip(Beverage beverage) {
		super(beverage);
	}
 
	public String getDescription() {
		return super.getDescription() + ", Whip";
	}
 
	public double cost() {
		return .10 + super.cost();
	}
}

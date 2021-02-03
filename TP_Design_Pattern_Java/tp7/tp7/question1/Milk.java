package question1;
/**
 * extrait de http://www.oreilly.com/catalog/hfdesignpat/
 */
public class Milk extends CondimentDecorator {


	public Milk(Beverage beverage) {
		super(beverage);
	}

	public String getDescription() {
		return super.getDescription() + ", Milk";
	}

	public double cost() {
		return .10 + super.cost();
	}
}

package question1;
/**
 * extrait de http://www.oreilly.com/catalog/hfdesignpat/
 */
public class Soy extends CondimentDecorator {
	

	public Soy(Beverage beverage) {
		super(beverage);
	}

	public String getDescription() {
		return super.getDescription() + ", Soy";
	}

	public double cost() {
		return .15 + super.cost();
	}
}

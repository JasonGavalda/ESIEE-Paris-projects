package question1;
/**
 * extrait de http://www.oreilly.com/catalog/hfdesignpat/
 */
public abstract class CondimentDecorator extends Beverage {
  protected Beverage beverage;
  
  public CondimentDecorator(Beverage beverage){
    this.beverage = beverage;
  }
  
	public String getDescription(){
	  return beverage.getDescription();
	 }
	 
	public double cost(){
	  return beverage.cost();
  }
}

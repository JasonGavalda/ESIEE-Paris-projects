package question1;
/**
 * extrait de http://www.oreilly.com/catalog/hfdesignpat/
 */
import java.text.DecimalFormat;
public abstract class Beverage {
	String description = "Unknown Beverage";
	
	private static DecimalFormat df = new DecimalFormat("#0.##"); // format d'affichage
  
	public String getDescription() {
		return description;
	}
 
	public abstract double cost();
	
	public String toString(){
	  return getDescription() + " $" + df.format(cost()); // + df.format(cost());
	}
	
}


/**
 * Décrivez votre classe Number ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Number extends Expression
{
    private int aValue;
    public Number (int pValue)
    {
        this.aType = Type.T_Int;
        this.aValue = pValue;
    }
    
    public int getValue()
    {
        return this.aValue;
    }
    
    public void accept(Visitor v)   {
        v.visit(this);
    }
}

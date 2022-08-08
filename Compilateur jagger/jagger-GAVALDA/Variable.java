
/**
 * Décrivez votre classe Variable ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Variable extends Expression
{
    private int aValue;
    private String aName;
    
    public Variable(String pName)   {
        this.aValue = 0;
        this.aName = pName;
        this.aType = Type.T_Int;
    }
    
    public int getValue()   {
        return this.aValue;
    }
    
    public void setValue(int pValue)    {
        this.aValue = pValue;
    }
    
    public String getName() {
        return aName;
    }
    
    @Override
    public void accept(Visitor v)   {
        v.visit(this);
    }
}

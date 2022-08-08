
/**
 * Décrivez votre classe Strings ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Strings extends Expression
{
    String aS;
    
    public Strings(String pS)    {
        this.aType = Type.T_String;
        this.aS = pS;
    }
    
    public String getValue()    {
        return this.aS;
    }
    
    public void accept(Visitor v)   {
        v.visit(this);
    }
    
}


/**
 * Décrivez votre classe Expression ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public abstract class Expression extends Statement
{  
    protected Type aType;
    
    public Type getType()   {
        return this.aType;
    }
    
    public void setType(Type pType) {
        this.aType = pType;
    }
}

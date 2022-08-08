
/**
 * Décrivez votre classe Initialization ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Initialization extends Statement
{
    private ID aID;
    private Expression aExp;
    private Scope aScope;
    
    public Initialization(ID pVar, Expression pExp, Scope pS)   {
        this.aID = pVar;
        this.aExp = pExp;
        this.aScope = pS;
    }
    
    public ID getID()   {
        return this.aID;
    }
    
    public Expression getExpression()   {
        return this.aExp;
    }
    
    public Scope getScope()    {
        return this.aScope;   
    }
    
    @Override
    public void accept(Visitor v)   {
        v.visit(this);
    }
}

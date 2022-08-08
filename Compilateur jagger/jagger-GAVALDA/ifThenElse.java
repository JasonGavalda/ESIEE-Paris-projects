
/**
 * Décrivez votre classe ifThenElse ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class ifThenElse extends Statement
{
    private Expression aCond;
    private Statement aThen;
    private Statement aElse;
    
    public ifThenElse (Expression pCond, Statement pThen, Statement pElse)    {
        this.aCond = pCond;
        this.aThen = pThen;
        this.aElse = pElse;
    }
    
    public Expression getCond() {
        return this.aCond;
    }
    
    public Statement getThen() {
        return this.aThen;
    }
    
    public Statement getElse() {
        return this.aElse;
    }
    
    @Override
    public void accept(Visitor v)   {
        v.visit(this);
    }
    
}

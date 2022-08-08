
/**
 * Décrivez votre classe For ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class For extends Statement
{
    Statement aAssign;
    Expression aTo;
    Statement aDo;
    
    public For (Statement pAssign, Expression pTo, Statement pDo)  {
        this.aAssign = pAssign;
        this.aTo = pTo;
        this.aDo = pDo;
    }
    
    public Statement getAssign()    {
        return this.aAssign;
    }
    
    public Expression getTo()   {
        return this.aTo;
    }
    
    public Statement getDo()    {
        return this.aDo;
    }
    
    public void accept(Visitor v)   {
        v.visit(this);
    }
}
